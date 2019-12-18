package com.fullstack.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository repository;

    @Override
    public Admin create(Admin admin) {
        return repository.save(admin);
    }

    @Override
    public Admin delete(int id) {
        Admin admin = findById(id);
        if(admin != null){
            repository.delete(admin);
        }
        return admin;
    }

    @Override
    public List<Admin> findAll() {
        return repository.findAll();
    }

    @Override
    public Admin findById(int id) {
        return repository.findById(id);
    }

    @Override
    public Admin update(Admin admin) {
        return repository.save(admin);
    }

	@Override
	public Admin authenticate(AdminLoginObject adminLoginObject) {
		String adminname = adminLoginObject.getUsername();
		String password = adminLoginObject.getPassword();
		List<Admin> admins = this.repository.findAll();
		for (Admin admin : admins) {
			if (admin.getUsername().equals(adminname) && admin.getPassword().equals(password)) {
				return admin;
			}
		}
		return null;
		
	}
}
