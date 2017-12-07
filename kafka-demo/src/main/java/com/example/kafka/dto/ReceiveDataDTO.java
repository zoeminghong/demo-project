package com.example.kafka.dto;

/**
 * Created on 2017/12/7.
 *
 * @author 迹_Jason
 */
public class ReceiveDataDTO {

    /**
     * database : ucent
     * table : t_auth_relation
     * type : delete
     * ts : 1512645332
     * xid : 2539
     * commit : true
     * data : {"auth_relation_id":"0075124ce9e149648374685dcee0c5cc","from_id":"1837a0303bae465aaeb7ae38af7ff821","to_id":"e896fa1789c14fd18c5853885f5c0636","relation_type":"GROUP_RESOURCE","resource_type":"MENU","app_id":"uc9be0f04e2842cfbb","enabled":1,"created_by":"mcenter-admin","created_time":"2017-12-01 18:15:20","updated_by":"mcenter-admin","updated_time":"2017-12-01 18:15:20","del_flag":0}
     */

    private String database;
    private String table;
    private String type;
    private Integer ts;
    private Integer xid;
    private Boolean commit;
    private String data;

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getTs() {
        return ts;
    }

    public void setTs(Integer ts) {
        this.ts = ts;
    }

    public Integer getXid() {
        return xid;
    }

    public void setXid(Integer xid) {
        this.xid = xid;
    }

    public void setCommit(Boolean commit) {
        this.commit = commit;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Boolean getCommit() {
        return commit;
    }
}
