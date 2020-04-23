package co.edu.icesi.fi.tics.tssc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import co.edu.icesi.fi.tics.tssc.model.TsscAdmin;
import co.edu.icesi.fi.tics.tssc.service.TsscAdminServiceImpl;

@Controller
public class UserController {

	@Autowired
	private TsscAdminServiceImpl tsscAdminService;
	
	@GetMapping("/")
	public String home() {
		return "index";	
	}
	
	@GetMapping("/login")
	public String login() {
		System.out.println("     >>> ");
		for (TsscAdmin u : tsscAdminService.findAll()) {
			System.out.println(u.getId()+" - "+u.getUser());
		}
		return "/login";
	}

	@GetMapping("/users/")
	public String indexUser(Model model) {
		model.addAttribute("users", tsscAdminService.findAll());
		return "users/index";
	}
}