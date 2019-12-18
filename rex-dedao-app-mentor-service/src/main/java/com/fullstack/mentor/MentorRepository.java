package com.fullstack.mentor;

import org.springframework.data.repository.Repository;

import java.util.List;

public interface MentorRepository extends Repository<Mentor, Integer> {

    void delete(Mentor mentor);

    List<Mentor> findAll();

    Mentor findById(int id);

    Mentor save(Mentor mentor);
}
