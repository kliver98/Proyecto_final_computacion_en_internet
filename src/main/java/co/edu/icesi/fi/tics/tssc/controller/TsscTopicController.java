package co.edu.icesi.fi.tics.tssc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.fi.tics.tssc.model.TsscTopic;
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
	public String index(Model model) {
		model.addAttribute("topics", tsscTopicService.findAll());
		return "topic/index";
	}
	
	@GetMapping("/topic/add")
	public String add(Model model) {
		model.addAttribute("topic", new TsscTopic());
		return "topic/save";
	}
	
	@PostMapping("/topic/add")
	public String saveUser1(@RequestParam(value = "action", required = true) String action, TsscTopic topic,
			BindingResult bindingResult, Model model) {
		if (topic == null)
			throw new IllegalArgumentException("Invalid Topic");
		if(!action.equals("Cancelar")) {
			if(bindingResult.hasErrors()) {
				return "topic/add";
			} else {
				tsscTopicService.save(topic);
			}
		}
		return "redirect:/topic/";	
	}
	
	@GetMapping("/topic/edit/{id}")
	public String edit(@PathVariable("id") long id, Model model) {
		TsscTopic topic = tsscTopicService.findById(id);
		if (topic == null)
			throw new IllegalArgumentException("Invalid topic Id:" + id);
		model.addAttribute("topic", topic);
		return "topic/edit";
	}
	
	@PostMapping("/topic/edit/{id}")
	public String edit(@Validated @PathVariable("id") long id,
			@RequestParam(value = "action", required = true) String action, TsscTopic topic, BindingResult bindingResult) {
		if (action != null && !action.equals("Cancelar")) {
			if (bindingResult.hasErrors()) {
				return "/story/edit/";
			} else {
				tsscTopicService.update(topic);
			}
		}
		return "redirect:/topic/";
	}
	
	@GetMapping("/topic/del/{id}")
	public String deleteUser(@PathVariable("id") long id) {
		TsscTopic topic = tsscTopicService.findById(id);
		if (topic == null)
			throw new IllegalArgumentException("Invalid topic Id:" + id);
		tsscTopicService.delete(topic);
		return "redirect:/game/";
	}
	
}