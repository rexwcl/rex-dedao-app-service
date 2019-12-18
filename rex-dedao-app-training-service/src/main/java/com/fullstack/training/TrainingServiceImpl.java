package com.fullstack.training;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingServiceImpl implements TrainingService {

    @Autowired
    private TrainingRepository repository;

    @Override
    public Training create(Training training) {
        return repository.save(training);
    }
    
    @Override
    public Training delete(int id) {
        Training training = findById(id);
        if(training != null){
            repository.delete(training);
        }
        return training;
    }

    @Override
    public List<Training> findAll() {
        return repository.findAll();
    }

    @Override
    public Training findById(int id) {
        return repository.findById(id);
    }

    @Override
    public Training update(Training training) {
        return repository.save(training);
    }


	@Override
	public List<Training> search(SearchObject searchObject) {
		String description = searchObject.getSearchText();
		List<Training> trainings = this.repository.findByDescription(description);
		
		return trainings;
		
	}
	
	@Override
	public List<Training> getCompletedTrainingsOfUser(int id) {
		List<Training> trainings = this.repository.getCompletedTrainingsOfUser(id);
		
		return trainings;
	}
	
	@Override
	public List<Training> getCompletedTrainingsOfMentor(int id) {
		
		List<Training> trainings = this.repository.getCompletedTrainingsOfMentor(id);
		
		return trainings;
		
	}
	
	@Override
	public List<Training> getProgressTrainingsOfUser(int id) {
		
		List<Training> trainings = this.repository.getProgressTrainingsOfUser(id);
		
		return trainings;
		
	}
	
	@Override
	public List<Training> getProgressTrainingsOfMentor(int id) {
		
		List<Training> trainings = this.repository.getProgressTrainingsOfMentor(id);
		
		return trainings;
		
	}
	
	
}
