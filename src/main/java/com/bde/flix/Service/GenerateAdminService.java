package com.bde.flix.Service;

import com.bde.flix.model.entity.userman.Admin;
import com.bde.flix.model.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenerateAdminService {
    AdminRepository adminrepo;
    @Autowired
    public GenerateAdminService(AdminRepository adminrepo){
        this.adminrepo = adminrepo;
    }

    public Admin createadmin(
            String name, String surname, String mail, String passwd, String workid
    ){
        Admin instance = new Admin();
        instance.setName(name);
        instance.setSurname(surname);
        instance.setEmail(mail);
        instance.setHash(passwd);
        instance.setWork_id(Integer.parseInt(workid));
        adminrepo.save(instance);
        return instance;
    }
}
