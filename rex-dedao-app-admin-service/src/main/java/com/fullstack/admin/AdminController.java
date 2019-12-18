package com.fullstack.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
//@RequestMapping({"/api"})
@RequestMapping({"/admin"})
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping
    public Admin create(@RequestBody Admin admin){
        return adminService.create(admin);
    }

    @GetMapping(path = {"/{id}"})
    public Admin findOne(@PathVariable("id") int id){
        return adminService.findById(id);
    }

    @PutMapping(path = {"/{id}"})
    public Admin update(@PathVariable("id") int id, @RequestBody Admin admin){
        admin.setId(id);
        return adminService.update(admin);
    }

    @DeleteMapping(path ={"/{id}"})
    public Admin delete(@PathVariable("id") int id) {
        return adminService.delete(id);
    }

    @GetMapping
    public List<Admin> findAll(){
        return adminService.findAll();
    }
    
    @PostMapping(path = "/authenticate")
    public Admin authenticate(@RequestBody AdminLoginObject adminLoginObject){
        return adminService.authenticate(adminLoginObject);
    }
    
    
}
