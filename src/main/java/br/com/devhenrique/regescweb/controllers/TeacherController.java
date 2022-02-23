package br.com.devhenrique.regescweb.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.devhenrique.regescweb.dto.TeacherDTO;
import br.com.devhenrique.regescweb.models.Teacher;
import br.com.devhenrique.regescweb.models.enums.TeacherStatus;
import br.com.devhenrique.regescweb.services.TeacherService;

@Controller
public class TeacherController {
	
	@Autowired
	TeacherService teacherService;
	
	@GetMapping("/teachers")
	public ModelAndView index() {
		List<Teacher> teachers = teacherService.findAll();
		ModelAndView mv = new ModelAndView("teachers/index.html");
		mv.addObject("teachers", teachers);
		return mv;
		
	}
	
	@GetMapping("/teacher/new")
	public ModelAndView newTeacher() {
		ModelAndView mv = new ModelAndView("teachers/new.html");
		mv.addObject("teacherStatus", TeacherStatus.values());
		return mv;
	}
	
	@PostMapping("/teachers")
	public String create(@Valid TeacherDTO teacherDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			System.out.println("\n****************\n");
			return "redirect:/teacher/new";
		}
		else {
			Teacher teacher = teacherDto.toTeacher();
			this.teacherService.save(teacher);
			
			return "redirect:/teachers";
		}
		
	}
	
}
