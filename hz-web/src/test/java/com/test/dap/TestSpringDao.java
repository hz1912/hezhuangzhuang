package com.test.dap;

import com.hz.hz.service.adminservice.AdminService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.hz.hz.persistence.model.Admin;

public class TestSpringDao {
    private static ApplicationContext applicationContext;

    public static void main(String args[]) {
        applicationContext = new ClassPathXmlApplicationContext("classpath:applicationcontext-dao.xml");
        AdminService adminService = (AdminService) applicationContext.getBean("adminServiceImpl");
        System.out.println(adminService);
        Admin admin = adminService.getAdminById(1);
        System.out.println(admin.getName());

    }
}
