package br.com.devhenrique.regescweb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.devhenrique.regescweb.models.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

}
