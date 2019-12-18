package com.fullstack.training;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
//@RequestMapping({"/api"})
@RequestMapping({"/trainings"})
public class TrainingController {

    @Autowired
    private TrainingService trainingService;
    
    @PostMapping(path = {"/create"})
    public Training create(@RequestBody Training training){
        return trainingService.create(training);
    }
    
    @DeleteMapping(path ={"/{id}"})
    public Training delete(@PathVariable("id") int id) {
        return trainingService.delete(id);
    }
    
    @PutMapping(path = {"/{id}"})
    public Training update(@PathVariable("id") int id, @RequestBody Training training){
        training.setId(id);
        return trainingService.update(training);
    }

	@PostMapping(path = {"/search"})
    public List<Training> search(@RequestBody SearchObject searchObject){
        return trainingService.search(searchObject);
    }

    @GetMapping(path = {"/users/progress/{id}"})
    public List<Training> getProgressTrainingsOfUser(@PathVariable("id") int id){
        return trainingService.getProgressTrainingsOfUser(id);
    }
	
	@GetMapping(path = {"/mentors/progress/{id}"})
    public List<Training> getProgressTrainingsOfMentor(@PathVariable("id") int id){
        return trainingService.getProgressTrainingsOfMentor(id);
    }
	
	@GetMapping(path = {"/users/completed/{id}"})
    public List<Training> getCompletedTrainingsOfUser(@PathVariable("id") int id){
        return trainingService.getCompletedTrainingsOfUser(id);
    }
	
	@GetMapping(path = {"/mentors/completed/{id}"})
    public List<Training> getCompletedTrainingsOfMentor(@PathVariable("id") int id){
        return trainingService.getCompletedTrainingsOfMentor(id);
    }
	
	@GetMapping(path = {"/users"})
    public ResponseEntity<Object> getUsers(){
		RestTemplate client = new RestTemplate();
		String apiUrl = "http://localhost:8081/user-portal/users";
	    ResponseEntity<Object> response = client.getForEntity(apiUrl, Object.class);
        return response;
    }
    
}
