package br.com.devhenrique.regescweb.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.devhenrique.regescweb.dto.StudentDTO;
import br.com.devhenrique.regescweb.models.Student;
import br.com.devhenrique.regescweb.models.enums.EnumCourseType;
import br.com.devhenrique.regescweb.services.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {

	@Autowired
	StudentService studentService;
	
	@GetMapping("")
	public ModelAndView index() {
		List<Student> students = studentService.findAll();
		ModelAndView mv = new ModelAndView("students/index.html");
		mv.addObject("students", students);
		return mv;
		
	}
	
	@GetMapping("/new")
	public ModelAndView newStudent(StudentDTO studentDto) {
		ModelAndView mv = new ModelAndView("students/new.html");
		mv.addObject("courseTypeList", EnumCourseType.values());
		return mv;
	}
	
	@PostMapping("")
	public ModelAndView create(@Valid StudentDTO studentDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			ModelAndView mv = new ModelAndView("students/new");
			mv.addObject("courseTypeList", EnumCourseType.values());
			return mv;
		}
		else {
			Student student = studentDto.toStudent();
			studentService.save(student);
			
			return new ModelAndView("redirect:/students/");
		}
		
	}
	
}