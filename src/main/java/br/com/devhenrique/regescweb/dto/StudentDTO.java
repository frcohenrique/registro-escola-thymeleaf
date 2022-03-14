package br.com.devhenrique.regescweb.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.devhenrique.regescweb.models.Student;
import br.com.devhenrique.regescweb.models.enums.EnumCourseType;

public class StudentDTO {

	@NotBlank
	@NotNull
	private String name;
	@NotBlank
	@NotNull
	private String course;
	private EnumCourseType enumCourseType;
	
	public StudentDTO() {
		
	}

	public StudentDTO(String name, String course, EnumCourseType enumCourseType) {
		super();
		this.name = name;
		this.course = course;
		this.enumCourseType = enumCourseType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public EnumCourseType getEnumCourseType() {
		return enumCourseType;
	}

	public void setEnumCourseType(EnumCourseType enumCourseType) {
		this.enumCourseType = enumCourseType;
	}
	
	public Student toStudent() {
		Student student = new Student();
		student.setName(name);
		student.setCourse(course);
		student.setEnumCourseType(enumCourseType);
		return student;
	}
	public Student toStudent(Student student) {
		student.setName(name);
		student.setCourse(course);
		student.setEnumCourseType(enumCourseType);
		return student;
	}
	
	public void fromTeacher(Student student) {
		name = student.getName();
		course = student.getCourse();
		enumCourseType = student.getEnumCourseType();
	}
	
	
}
