package br.com.devhenrique.regescweb.dto;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.devhenrique.regescweb.models.Teacher;
import br.com.devhenrique.regescweb.models.enums.TeacherStatus;

public class TeacherDTO {
	
	@NotBlank
	@NotNull
	private String name;
	@NotNull
	@DecimalMin("0.0")
	private BigDecimal salary;
	private TeacherStatus teacherStatus;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getSalary() {
		return salary;
	}
	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}
	public TeacherStatus getTeacherStatus() {
		return teacherStatus;
	}
	public void setTeacherStatus(TeacherStatus teacherStatus) {
		this.teacherStatus = teacherStatus;
	}
	
	public Teacher toTeacher() {
		Teacher teacher = new Teacher();
		teacher.setName(this.name);
		teacher.setSalary(this.salary);
		teacher.setTeacherStatus(this.teacherStatus);
		return teacher;
	}
	
	@Override
	public String toString() {
		return "TeacherDTO [name=" + name + ", salary=" + salary + ", teacherStatus=" + teacherStatus + "]";
	}
	
	
	

}
