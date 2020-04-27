package co.edu.icesi.fi.tics.tssc.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.fi.tics.tssc.model.TsscStory;
import co.edu.icesi.fi.tics.tssc.service.TsscStoryService;

@Controller
public class TsscStoryController {

	private TsscStoryService tsscStoryService;

	@GetMapping("/story/game/{id}")
	public String index(@PathVariable("id") long id, Model model) {
		model.addAttribute("stories", tsscStoryService.findAll());
		return "story/index";
	}
	
	@GetMapping("/story/edit/{id}")
	public String edit(@PathVariable("id") long id, Model model) {
		Optional<TsscStory> story = tsscStoryService.findById(id);
		if (story == null)
			throw new IllegalArgumentException("Invalid game Id:" + id);
		model.addAttribute("story", story.get());
		return "story/edit";
	}
	
	@PostMapping("/story/edit/{id}")
	public String edit(@Validated @PathVariable("id") long id,
			@RequestParam(value = "action", required = true) String action, TsscStory story, BindingResult bindingResult) {
		if (action != null && !action.equals("Cancelar")) {
			if (bindingResult.hasErrors()) {
				return "/story/edit/";
			} else {
				TsscStory s = tsscStoryService.findById(id).get();
				tsscStoryService.editStory(story);
			}
		}
		return "redirect:/story/";
	}
	
}
