package com.bde.flix.service;

import com.bde.flix.security.Account.Role;
import com.bde.flix.model.entity.userman.Admin;
import com.bde.flix.model.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService
{
    AdminRepository adminRepo;
    @Autowired
    public AdminService(AdminRepository adminRepo)
    {
        this.adminRepo = adminRepo;
    }

    public Admin createadmin(
            String name,
            String surname,
            String mail,
            String passwd,
            String workid)
    {
        Admin instance = new Admin();
        instance.setName(name);
        instance.setRole(Role.ROLE_ADMIN);
        instance.setSurname(surname);
        instance.setEmail(mail);
        instance.setHash(passwd);
        instance.setWork_id(Integer.parseInt(workid));
        return adminRepo.save(instance);
    }
}

