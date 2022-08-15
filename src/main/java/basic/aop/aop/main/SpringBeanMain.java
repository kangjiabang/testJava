package basic.aop.aop.main;

import basic.aop.aop.Dao;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author：zeqi
 * @Date: Created in 11:23 30/12/17.
 * @Description:
 */
public class SpringBeanMain {


    public static void main(String[] args) {
        Logger log = Logger.getLogger(SpringBeanMain.class.getName());
        ApplicationContext context =  new ClassPathXmlApplicationContext("spring/spring-service.xml");
        log.info("start to invoke");
        Dao dao = (Dao)context.getBean("daoImpl");
        dao.insert();
    }
}
