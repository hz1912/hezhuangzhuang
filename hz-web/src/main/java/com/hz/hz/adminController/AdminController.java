package com.hz.hz.adminController;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.hz.hz.persistence.model.Admin;
import com.hz.hz.service.adminservice.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object getAdmin(@PathVariable("id") Integer id) {
        try {
            Admin admin = adminService.getAdminById(id);
            Map<String, Object> map = new HashMap<>();
            map.put("name", admin.getName());
            map.put("createTime", admin.getCreateTime());
            return new ObjectMapper().writeValueAsString(map);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;

    }
}