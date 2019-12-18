package com.fullstack.admin;

import java.util.List;

public interface AdminService {

    Admin create(Admin admin);

    Admin delete(int id);

    List<Admin> findAll();

    Admin findById(int id);

    Admin update(Admin admin);
    
    Admin authenticate(AdminLoginObject adminLoginObject);
}
