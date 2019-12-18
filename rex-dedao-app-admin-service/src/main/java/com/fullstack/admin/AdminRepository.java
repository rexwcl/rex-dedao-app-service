package com.fullstack.admin;

import org.springframework.data.repository.Repository;

import java.util.List;

public interface AdminRepository extends Repository<Admin, Integer> {

    void delete(Admin admin);

    List<Admin> findAll();

    Admin findById(int id);

    Admin save(Admin admin);
}
