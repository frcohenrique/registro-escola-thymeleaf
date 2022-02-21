package br.com.devhenrique.regescweb.services;

import java.util.List;

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
	
}
