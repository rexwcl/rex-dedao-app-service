package com.fullstack.mentor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
//@RequestMapping({"/api"})
@RequestMapping({"/mentors"})
public class MentorController {

    @Autowired
    private MentorService mentorService;

    @PostMapping
    public Mentor create(@RequestBody Mentor mentor){
        return mentorService.create(mentor);
    }

    @GetMapping(path = {"/{id}"})
    public Mentor findOne(@PathVariable("id") int id){
        return mentorService.findById(id);
    }

    @PutMapping(path = {"/{id}"})
    public Mentor update(@PathVariable("id") int id, @RequestBody Mentor mentor){
        mentor.setId(id);
        return mentorService.update(mentor);
    }

    @DeleteMapping(path ={"/{id}"})
    public Mentor delete(@PathVariable("id") int id) {
        return mentorService.delete(id);
    }

    @GetMapping
    public List<Mentor> findAll(){
        return mentorService.findAll();
    }
    
    @PostMapping(path = "/authenticate")
    public Mentor authenticate(@RequestBody MentorLoginObject mentorLoginObject){
        return mentorService.authenticate(mentorLoginObject);
    }
    
    
}
