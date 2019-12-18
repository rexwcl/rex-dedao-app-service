package com.fullstack.training;

import java.util.List;

public interface TrainingService {
    
	Training create(Training training);
    Training delete(int id);
    List<Training> findAll();
    Training findById(int id);
    Training update(Training training);
    List<Training> search(SearchObject searchObject);
	List<Training> getProgressTrainingsOfUser(int id);
	List<Training> getProgressTrainingsOfMentor(int id);
	List<Training> getCompletedTrainingsOfUser(int id);
	List<Training> getCompletedTrainingsOfMentor(int id);
}
