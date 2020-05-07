package co.edu.icesi.fi.tics.tssc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import co.edu.icesi.fi.tics.tssc.service.TsscAdminServiceImpl;

@Controller
public class TsscAdminController {

	@Autowired
	private TsscAdminServiceImpl tsscAdminService;

	@GetMapping("/")
	public String home() {
		return "index";
	}

	@GetMapping("/login")
	public String login() {
		return "/login";
	}
	
	@GetMapping("/admin")
	public String index() {
		return "/admin/";
	}
	/*
	@RequestMapping("/error")
	public String handleError(HttpServletRequest request, Model model) {
		model.addAttribute("error", true);
		return "index";
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}
	 */
}