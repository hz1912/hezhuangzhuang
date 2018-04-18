package com.hz.hz.service.adminservice;

import com.hz.hz.persistence.dao.AdminDao;
import com.hz.hz.persistence.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class AdminServiceImpl implements AdminService {

   @Autowired
    private AdminDao adminDao;

    public Admin getAdminById(Integer id) {
        return adminDao.getAdminByKey(id);
    }
}
