package com.fullstack.training;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TrainingRepository extends Repository<Training, Integer> {

    List<Training> findAll();
    
    void delete(Training training);
    
    Training findById(int id);
    
    Training save(Training training);
    
    @Query(value = "select * from training t where t.description like %?1%", nativeQuery = true )
	List<Training> findByDescription(String description);
    
    @Query(value = "select * from training t where t.user_id=?1 and t.progress = 0", nativeQuery = true)
    List<Training> getCompletedTrainingsOfUser(int id);
    
    @Query(value = "select * from training t where t.mentor_id=?1 and t.progress = 0", nativeQuery = true)
    List<Training> getCompletedTrainingsOfMentor(int id);
    
    @Query(value = "select * from training t where t.user_id=?1 and t.progress = 1", nativeQuery = true)
    List<Training> getProgressTrainingsOfUser(int id);
    
    @Query(value = "select * from training t where t.mentor_id=?1 and t.progress = 1", nativeQuery = true)
    List<Training> getProgressTrainingsOfMentor(int id);
    
    
    

}
