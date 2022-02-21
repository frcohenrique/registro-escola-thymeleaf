package br.com.devhenrique.regescweb.controllers;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.devhenrique.regescweb.models.Teacher;
import br.com.devhenrique.regescweb.models.enums.TeacherStatus;

@Controller
public class TeacherController {

	@GetMapping("/teachers")
	public ModelAndView index() {
		Teacher batman = new Teacher("Batman", new BigDecimal(2500.00), TeacherStatus.ACTIVE);
		batman.setId(1L);
		Teacher joker = new Teacher("Joker", new BigDecimal(3000.00), TeacherStatus.RETIRED);
		joker.setId(2L);
		Teacher wonderWoman = new Teacher("Wonder Woman", new BigDecimal(10000.00), TeacherStatus.AWAY);
		wonderWoman.setId(3L);
		List<Teacher> teachers = Arrays.asList(batman, joker, wonderWoman);
		ModelAndView mv = new ModelAndView("teachers/index.html");
		mv.addObject("teachers", teachers);
		return mv;
		
	}
	
}
