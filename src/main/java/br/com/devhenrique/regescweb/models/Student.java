package br.com.devhenrique.regescweb.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.devhenrique.regescweb.models.enums.EnumCourseType;

@Entity
@Table(name = "tb_students")
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String course;
	@Enumerated(EnumType.STRING)
	private EnumCourseType enumCourseType;

	public Student() {
		
	}

	public Student(String name, String course, EnumCourseType enumCourseType) {
		this.name = name;
		this.course = course;
		this.enumCourseType = enumCourseType;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	
}
