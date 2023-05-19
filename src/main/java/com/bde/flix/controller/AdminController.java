package com.bde.flix.controller;

import com.bde.flix.service.AdminService;
import com.bde.flix.model.entity.userman.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController
{
    @Autowired
    private AdminService adminService;

    @GetMapping("/api/admin")
    public AdminRecord admin(
            @RequestParam(required = true, defaultValue = "defaultAdminName") String name,
            @RequestParam(required = true, defaultValue = "defaultAdminSurname") String surname,
            @RequestParam(required = true, defaultValue = "defaultAdminmail") String mail,
            @RequestParam(required = true, defaultValue = "defaultAdminPasswd") String passwd,
            @RequestParam(required = true, defaultValue = "2137") String workid)
    {
        Admin entity = adminService.createadmin(name, surname, mail, passwd, workid);

        return new AdminRecord(
                entity.getName(),
                entity.getSurname(),
                entity.getEmail(),
                entity.getHash(),
                String.valueOf(entity.getWork_id()));
    }
}
