package co.edu.icesi.fi.tics.tssc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import co.edu.icesi.fi.tics.tssc.service.TsscTopicService;

@Controller
public class TsscTopicController {

	private TsscTopicService tsscTopicService;
	
	@Autowired
	public TsscTopicController(TsscTopicService tsscTopicService) {
		super();
		this.tsscTopicService = tsscTopicService;
	}

	@GetMapping("/topic/")
	public String indexApp(Model model) {
		model.addAttribute("topics", tsscTopicService.findAll());
		return "topic/index";
	}
	
}
