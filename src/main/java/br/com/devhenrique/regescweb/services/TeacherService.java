package br.com.devhenrique.regescweb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.devhenrique.regescweb.models.Teacher;
import br.com.devhenrique.regescweb.repositories.TeacherRepository;

@Service
public class TeacherService {

	@Autowired
	TeacherRepository repository;
	
	public List<Teacher> findAll(){
		return repository.findAll();
	}
	
	public void save(Teacher teacher) {
		repository.save(teacher);
	}

	public Optional<Teacher> findById(Long id) {
		return repository.findById(id);
	}
	
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
	
}
