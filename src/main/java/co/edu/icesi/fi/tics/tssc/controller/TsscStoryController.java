package co.edu.icesi.fi.tics.tssc.controller;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.fi.tics.tssc.model.TsscStory;
import co.edu.icesi.fi.tics.tssc.service.TsscGameService;
import co.edu.icesi.fi.tics.tssc.service.TsscStoryService;

@Controller
public class TsscStoryController {

	@Autowired
	private TsscStoryService tsscStoryService;
	@Autowired
	private TsscGameService tsscGameService;

	@GetMapping("/story/game/{id}")
	public String index(@PathVariable("id") long id, Model model) {
		ArrayList<TsscStory> stories = new ArrayList<TsscStory>();
		for (TsscStory story : tsscStoryService.findAll()) {
			if (story.getTsscGame().getId()==id) {
				stories.add(story);
			}
		}
		model.addAttribute("stories", stories);
		return "story/index";
	}
	
	@GetMapping("/story/add/")
	public String add(Model model) {
		model.addAttribute("story", new TsscStory());
		model.addAttribute("games", tsscGameService.findAll());
		return "story/save";
	}
	
	@PostMapping("/story/add/")
	public String add(@RequestParam(value = "action", required = true) String action,
			TsscStory story, BindingResult bindingResult, Model model) {
		if (!action.equals("Cancelar")) {
			if (bindingResult.hasErrors()) {
				model.addAttribute("story", story);
				model.addAttribute("games", tsscGameService.findAll());
				return "/story/save";
			} else {
				TsscStory f = tsscStoryService.save(story, tsscGameService.findAll());
				return "redirect:/story/game/"+f.getTsscGame().getId();
			}
		}
		return "redirect:/game/";
	}
	
	@GetMapping("/story/edit/{id}")
	public String edit(@PathVariable("id") long id, Model model) {
		TsscStory story = tsscStoryService.findById(id);
		if (story == null)
			throw new IllegalArgumentException("Invalid story Id:" + id);
		model.addAttribute("story", story);
		model.addAttribute("games", tsscGameService.findAll());
		return "story/edit";
	}
	
	@PostMapping("/story/edit/{id}")
	public String edit(@Validated @PathVariable("id") long id,
			@RequestParam(value = "action", required = true) String action,
			@RequestParam(value = "businessValue", required = true) BigDecimal businessValue,
			TsscStory story, BindingResult bindingResult) {
		if (action != null && !action.equals("Cancelar")) {
			if (bindingResult.hasErrors()) {
				return "/story/edit/";
			} else {
				story.setBusinessValue(businessValue);
				tsscStoryService.update(story);
			}
		}
		return "redirect:/story/game/"+story.getTsscGame().getId();
	}
	
	@GetMapping("/story/del/{id}")
	public String deleteUser(@PathVariable("id") long id) {
		TsscStory story = tsscStoryService.findById(id);
		if (story==null)
			throw new IllegalArgumentException("Invalid story Id:" + id);
		tsscStoryService.delete(story);
		return "redirect:/game/";
	}
	
}