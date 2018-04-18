package com.test.dap;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpringDao {
    private static  ApplicationContext applicationContext;
    public static  void  main(String args[]){
        applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext-dao.xml");
        Object obj = applicationContext.getBean("sqlSessionFactory");
        System.out.println(obj);
    }


}
