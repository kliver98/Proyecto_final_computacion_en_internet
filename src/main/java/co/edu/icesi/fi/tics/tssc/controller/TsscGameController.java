package co.edu.icesi.fi.tics.tssc.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.service.TsscGameService;

@Controller
public class TsscGameController {

	private TsscGameService tsscGameService;
	
	@Autowired
	public TsscGameController(TsscGameService tsscGameService) {
		super();
		this.tsscGameService = tsscGameService;
	}

	@GetMapping("/game/")
	public String index(Model model) {
		model.addAttribute("games", tsscGameService.findAll());
		return "game/index";
	}
	
	@GetMapping("/game/edit/{id}")
	public String edit(@PathVariable("id") long id, Model model) {
		Optional<TsscGame> game = tsscGameService.findById(id);
		if (game == null)
			throw new IllegalArgumentException("Invalid game Id:" + id);
		model.addAttribute("game", game.get());
		return "game/edit";
	}
	
	@PostMapping("/game/edit/{id}")
	public String edit(@Validated @PathVariable("id") long id,
			@RequestParam(value = "action", required = true) String action, TsscGame game, BindingResult bindingResult) {
		if (action != null && !action.equals("Cancelar")) {
			if (bindingResult.hasErrors()) {
				return "/game/edit/";
			} else {
				TsscGame g = tsscGameService.findById(id).get();
				game.setStartTime(g.getStartTime());
				game.setScheduledDate(g.getScheduledDate());
				game.setScheduledTime(g.getScheduledTime());
				tsscGameService.editGame(game);
			}
		}
		return "redirect:/game/";
	}
	
	@GetMapping("/game/del/{id}")
	public String deleteUser(@PathVariable("id") long id) {
		TsscGame game = tsscGameService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		tsscGameService.delete(game);
		return "redirect:/game/";
	}
	
}
