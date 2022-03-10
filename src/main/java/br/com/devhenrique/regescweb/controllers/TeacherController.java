package br.com.devhenrique.regescweb.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.devhenrique.regescweb.dto.TeacherDTO;
import br.com.devhenrique.regescweb.models.Teacher;
import br.com.devhenrique.regescweb.models.enums.TeacherStatus;
import br.com.devhenrique.regescweb.services.TeacherService;

@Controller
@RequestMapping("/teachers")
public class TeacherController {
	
	@Autowired
	TeacherService teacherService;
	
	@GetMapping("")
	public ModelAndView index() {
		List<Teacher> teachers = teacherService.findAll();
		ModelAndView mv = new ModelAndView("teachers/index.html");
		mv.addObject("teachers", teachers);
		return mv;
		
	}
	
	@GetMapping("/new")
	public ModelAndView newTeacher(TeacherDTO teacherDto) {
		ModelAndView mv = new ModelAndView("teachers/new.html");
		mv.addObject("teacherStatusList", TeacherStatus.values());
		return mv;
	}
	
	@PostMapping("")
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
	
	@GetMapping("/{id}")
	public ModelAndView show(@PathVariable Long id) {
		Optional<Teacher> optional = teacherService.findById(id);
		if (optional.isPresent()) {
			Teacher teacher = optional.get();
			ModelAndView mv = new ModelAndView("teachers/show.html");
			mv.addObject("teacher", teacher);
			return mv;
		}
		else {
			return errorMessages("SHOW ERROR: Teacher #"+ id + " doesn't exist!");
		}
		
	}
	
	@GetMapping("/{id}/edit")
	public ModelAndView edit(@PathVariable Long id, TeacherDTO teacherDto) {
		
		Optional<Teacher> optional = teacherService.findById(id);
		
		if (optional.isPresent()) {
			Teacher teacher = optional.get();
			teacherDto.fromTeacher(teacher);
			ModelAndView mv = new ModelAndView("teachers/edit.html");
			mv.addObject("teacherId", teacher.getId());
			mv.addObject("teacherStatusList", TeacherStatus.values());
			return mv;
		}
		else {
			ModelAndView mv = errorMessages("EDIT ERROR: Teacher #"+ id + " doesn't exist!");
			return mv;
		}
		
	}
	
	@PostMapping("/{id}")
	 public ModelAndView update(@PathVariable Long id, @Valid TeacherDTO teacherDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			ModelAndView mv = new ModelAndView("teachers/edit");
			mv.addObject("teacherId", id);
			mv.addObject("teacherStatusList", TeacherStatus.values());
			return mv;
		}
		else {
			Optional<Teacher> optional = teacherService.findById(id);
			if (optional.isPresent()) {
				Teacher teacher = teacherDto.toTeacher(optional.get());
				teacherService.save(teacher);
				return new ModelAndView("redirect:/teachers/" + teacher.getId());
			}
			else {
				ModelAndView mv = errorMessages("UPDATE ERROR: Teacher #"+ id + " doesn't exist!");
				return mv;
			}
			
		}
		
	}
	
	@GetMapping("/{id}/delete")
	public ModelAndView delete(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView("redirect:/teachers");
		
		try {
			teacherService.deleteById(id);
			mv.addObject("message", "Teacher #"+ id + " deleted successfully!");
			mv.addObject("error", false);
		}catch(EmptyResultDataAccessException e) {
			System.out.println(e);
			mv = errorMessages("DELETE ERROR: Teacher #"+ id + " doesn't exist!");
		}
		return mv;
	}
	
	private ModelAndView errorMessages(String msg) {
		ModelAndView mv = new ModelAndView("redirect:/teachers");
		mv.addObject("message", msg);
		mv.addObject("error", true);
		return mv;
	}
	
}
