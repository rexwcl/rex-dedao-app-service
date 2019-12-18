package com.fullstack.mentor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MentorServiceImpl implements MentorService {

    @Autowired
    private MentorRepository repository;

    @Override
    public Mentor create(Mentor mentor) {
        return repository.save(mentor);
    }

    @Override
    public Mentor delete(int id) {
        Mentor mentor = findById(id);
        if(mentor != null){
            repository.delete(mentor);
        }
        return mentor;
    }

    @Override
    public List<Mentor> findAll() {
        return repository.findAll();
    }

    @Override
    public Mentor findById(int id) {
        return repository.findById(id);
    }

    @Override
    public Mentor update(Mentor mentor) {
        return repository.save(mentor);
    }

	@Override
	public Mentor authenticate(MentorLoginObject mentorLoginObject) {
		String username = mentorLoginObject.getUsername();
		String password = mentorLoginObject.getPassword();
		List<Mentor> mentors = this.repository.findAll();
		for (Mentor mentor : mentors) {
			if (mentor.getUsername().equals(username) && mentor.getPassword().equals(password)) {
				return mentor;
			}
		}
		return null;
		
	}
}
