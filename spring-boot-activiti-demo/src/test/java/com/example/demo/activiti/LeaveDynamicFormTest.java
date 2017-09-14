package com.example.demo.activiti;

import org.activiti.engine.*;
import org.activiti.engine.history.HistoricDetail;
import org.activiti.engine.history.HistoricFormProperty;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricVariableUpdate;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by 迹_Jason on 2017/8/14.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SpringBootActivitiDemoApplication.class})
public class LeaveDynamicFormTest {
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private IdentityService identityService;
    @Autowired
    private FormService formService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;

    @Autowired
    private RuntimeService runtimeService;


    @Test
    @Deployment(resources = "/leave.bpmn20.xml")
    public void allApproved() throws Exception {

        // 验证是否部署成功
        long count = repositoryService.createProcessDefinitionQuery().count();
//        assertEquals(3, count);
        System.out.println(count);

        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey("leave").singleResult();

        // 设置当前用户
        String currentUserId = "henryyan";
        identityService.setAuthenticatedUserId(currentUserId);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Map<String, String> variables = new HashMap<String, String>();
        Calendar ca = Calendar.getInstance();
        String startDate = sdf.format(ca.getTime());
        ca.add(Calendar.DAY_OF_MONTH, 2); // 当前日期加2天
        String endDate = sdf.format(ca.getTime());

        // 启动流程
        variables.put("startDate", startDate);
        variables.put("endDate", endDate);
        variables.put("reason", "公休");
        ProcessInstance processInstance = formService.submitStartFormData(processDefinition.getId(), variables);
        assertNotNull(processInstance);

        List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstance.getId()).list();

        // 部门领导审批通过
        Task deptLeaderTask = taskService.createTaskQuery().taskCandidateGroup("deptLeader").singleResult();
        variables = new HashMap<String, String>();
        variables.put("deptLeaderApproved", "true");
        formService.submitTaskFormData(deptLeaderTask.getId(), variables);

        // 人事审批通过
        Task hrTask = taskService.createTaskQuery().taskCandidateGroup("hr").singleResult();
        variables = new HashMap<String, String>();
        variables.put("hrApproved", "true");
        formService.submitTaskFormData(hrTask.getId(), variables);

        // 销假（根据申请人的用户ID读取）
        Task reportBackTask = taskService.createTaskQuery().taskAssignee(currentUserId).singleResult();
        variables = new HashMap<String, String>();
        variables.put("reportBackDate", sdf.format(ca.getTime()));
        formService.submitTaskFormData(reportBackTask.getId(), variables);

        // 验证流程是否已经结束
        HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery().finished().singleResult();
        assertNotNull(historicProcessInstance);

        // 读取历史变量
        Map<String, Object> historyVariables = packageVariables(processInstance);

        // 验证执行结果
        assertEquals("ok", historyVariables.get("result"));
    }


    /**
     * 读取历史变量并封装到Map中
     */
    private Map<String, Object> packageVariables(ProcessInstance processInstance) {
        Map<String, Object> historyVariables = new HashMap<String, Object>();
        List<HistoricDetail> list = historyService.createHistoricDetailQuery().processInstanceId(processInstance.getId()).list();
        for (HistoricDetail historicDetail : list) {
            if (historicDetail instanceof HistoricFormProperty) {
                // 表单中的字段
                HistoricFormProperty field = (HistoricFormProperty) historicDetail;
                historyVariables.put(field.getPropertyId(), field.getPropertyValue());
                System.out.println("form field: taskId=" + field.getTaskId() + ", " + field.getPropertyId() + " = " + field.getPropertyValue());
            } else if (historicDetail instanceof HistoricVariableUpdate) {
                HistoricVariableUpdate variable = (HistoricVariableUpdate) historicDetail;
                historyVariables.put(variable.getVariableName(), variable.getValue());
                System.out.println("variable: " + variable.getVariableName() + " = " + variable.getValue());
            }
        }
        return historyVariables;
    }


    @Test
    @Deployment(resources = {"/testMultiInstanceFixedNumbers.bpmn"})
    public void testParallel() throws Exception {
        Map<String, Object> variables = new HashMap<String, Object>();
        long loop = 3;
        variables.put("loop", loop);
        variables.put("counter", 0); // 计数器
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("testMultiInstanceFixedNumbers", variables);

        Object variable = runtimeService.getVariable(processInstance.getId(), "counter");
        assertEquals(loop, variable);
    }
    @Test
    @Deployment(resources = {"/myProcess_1.bpmn"})
    public void testMyLeave() {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess_1");
        List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstance.getId()).list();
        System.out.println(tasks.size());
        ProcessDefinitionEntity def = (ProcessDefinitionEntity) ((RepositoryServiceImpl)repositoryService).getDeployedProcessDefinition(tasks.get(0).getProcessDefinitionId());
        List<ActivityImpl> activitiList = def.getActivities();
        System.out.println(activitiList.size());
        String excId = tasks.get(0).getExecutionId();
        ExecutionEntity execution = (ExecutionEntity) runtimeService.createExecutionQuery().executionId(excId).singleResult();
        String activitiId = execution.getActivityId();

        for(ActivityImpl activityImpl:activitiList){
            String id = activityImpl.getId();
            if(activitiId.equals(id)){
                System.out.println("当前任务："+activityImpl.getProperty("name")); //输出某个节点的某种属性
                List<PvmTransition> outTransitions = activityImpl.getOutgoingTransitions();//获取从某个节点出来的所有线路
                for(PvmTransition tr:outTransitions){
                    PvmActivity ac = tr.getDestination(); //获取线路的终点节点
                    System.out.println("下一步任务任务："+ac.getProperty("name"));
                }
                break;
            }
        }
    }
    @Test
    @Deployment(resources = "/test1.bpmn20.xml")
    public void runTest1(){
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("test1");
        Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
        Map<String, Object> variables = new HashMap<>();
        variables.put("type","1");
        taskService.complete(task.getId(),variables);
        List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstance.getId()).list();
        System.out.println(tasks.size());
        ProcessDefinitionEntity def = (ProcessDefinitionEntity) ((RepositoryServiceImpl)repositoryService).getDeployedProcessDefinition(tasks.get(0).getProcessDefinitionId());
        List<ActivityImpl> activitiList = def.getActivities();
        System.out.println(activitiList.size());
        String excId = tasks.get(0).getExecutionId();
        ExecutionEntity execution = (ExecutionEntity) runtimeService.createExecutionQuery().executionId(excId).singleResult();
        String activitiId = execution.getActivityId();

        for(ActivityImpl activityImpl:activitiList){
            String id = activityImpl.getId();
            if(activitiId.equals(id)){
                System.out.println("当前任务："+activityImpl.getProperty("name")); //输出某个节点的某种属性
                List<PvmTransition> outTransitions = activityImpl.getOutgoingTransitions();//获取从某个节点出来的所有线路
                for(PvmTransition tr:outTransitions){
                    PvmActivity ac = tr.getDestination(); //获取线路的终点节点
                    System.out.println("下一步任务任务："+ac.getProperty("name"));
                }
                break;
            }
        }
    }

    @Test
    @Deployment(resources = "/test2.bpmn20.xml")
    public void runTest2(){
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("test2");
        Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
        Map<String, Object> variables = new HashMap<>();
        variables.put("type","1");
        taskService.complete(task.getId(),variables);
        List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstance.getId()).list();
        System.out.println(tasks.size());
        ProcessDefinitionEntity def = (ProcessDefinitionEntity) ((RepositoryServiceImpl)repositoryService).getDeployedProcessDefinition(tasks.get(0).getProcessDefinitionId());
        List<ActivityImpl> activitiList = def.getActivities();
        System.out.println(activitiList.size());
        String excId = tasks.get(0).getExecutionId();
        ExecutionEntity execution = (ExecutionEntity) runtimeService.createExecutionQuery().executionId(excId).singleResult();
        String activitiId = execution.getActivityId();

        for(ActivityImpl activityImpl:activitiList){
            String id = activityImpl.getId();
            if(activitiId.equals(id)){
                System.out.println("当前任务："+activityImpl.getProperty("name")); //输出某个节点的某种属性
                List<PvmTransition> outTransitions = activityImpl.getOutgoingTransitions();//获取从某个节点出来的所有线路
                for(PvmTransition tr:outTransitions){
                    PvmActivity ac = tr.getDestination(); //获取线路的终点节点
                    System.out.println("下一步任务任务："+ac.getProperty("name"));
                }
                break;
            }
        }
    }
}
