package com.demo;

/**
 * Created by 迹_Jason on 2017/9/28.
 */
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DubboInit extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 8279515033200832L;
    private Logger logger = (Logger) LoggerFactory.getLogger(DubboInit.class);
    /**
     * Æô¶¯dubboÈÝÆ÷
     */
    public void init() throws ServletException {
        try {
            startApplicationContext();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(ExceptionUtils.getFullStackTrace(e));
        }
    }

    public static ApplicationContext applicationContext = null;

    /**
     * Æô¶¯springÈÝÆ÷
     * @return
     */
    public static ApplicationContext startApplicationContext() {
        if (applicationContext == null) {
            applicationContext = new ClassPathXmlApplicationContext("classpath*:applicationContext.xml");
        }
        return applicationContext;
    };

}
