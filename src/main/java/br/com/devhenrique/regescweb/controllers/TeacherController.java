package br.com.devhenrique.regescweb.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("/teachers/new")
	public ModelAndView newTeacher(TeacherDTO teacherDto) {
		ModelAndView mv = new ModelAndView("teachers/new.html");
		mv.addObject("teacherStatusList", TeacherStatus.values());
		return mv;
	}
	
	@PostMapping("/teachers")
	public ModelAndView create(@Valid TeacherDTO teacherDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			ModelAndView mv = new ModelAndView("teachers/new");
			mv.addObject("teacherStatusList", TeacherStatus.values());
			return mv;
		}
		else {
			Teacher teacher = teacherDto.toTeacher();
			this.teacherService.save(teacher);
			
			return new ModelAndView("redirect:/teachers/" + teacher.getId());
		}
		
	}
	
	@GetMapping("/teachers/{id}")
	public ModelAndView show(@PathVariable Long id) {
		Optional<Teacher> optional = teacherService.findById(id);
		if (optional.isPresent()) {
			Teacher teacher = optional.get();
			ModelAndView mv = new ModelAndView("teachers/show.html");
			mv.addObject("teacher", teacher);
			return mv;
		}
		else {
			return new ModelAndView("redirect:/teachers");
		}
		
	}
	
}
