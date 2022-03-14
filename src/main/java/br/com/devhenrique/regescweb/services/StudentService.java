package br.com.devhenrique.regescweb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.devhenrique.regescweb.models.Student;
import br.com.devhenrique.regescweb.repositories.StudentRepository;

@Service
public class StudentService {

	@Autowired
	StudentRepository repository;
	
	public List<Student> findAll(){
		return repository.findAll();
	}
	
	public void save(Student student) {
		repository.save(student);
	}
	
	public Optional<Student> findById(Long id){
		return repository.findById(id);
	}
	
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
	
}
