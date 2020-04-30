package co.edu.icesi.fi.tics.tssc.controller;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.service.TsscGameService;
import co.edu.icesi.fi.tics.tssc.service.TsscTopicService;

@Controller
public class TsscGameController {

	private TsscGameService tsscGameService;
	private TsscTopicService tsscTopicService;
	
	@Autowired
	public TsscGameController(TsscGameService tsscGameService, TsscTopicService tsscTopicService) {
		super();
		this.tsscGameService = tsscGameService;
		this.tsscTopicService = tsscTopicService;
	}

	@GetMapping("/game/")
	public String index(Model model) {
		model.addAttribute("games", tsscGameService.findAll());
		return "game/index";
	}

	@GetMapping("/game/add/")
	public String add(Model model) {
		model.addAttribute("game", new TsscGame());
		model.addAttribute("topics", tsscTopicService.findAll());
		return "game/save";
	}
	
	@PostMapping("/game/add/")
	public String add(@RequestParam(value = "action", required = true) String action,
			@RequestParam(value = "scheduledDate", required = false) String sDate, TsscGame game,
			BindingResult bindingResult, Model model) {
		if (!action.equals("Cancelar")) {
			boolean errorNot = false;
			for (ObjectError err : bindingResult.getAllErrors()) { //Tuve que hacerlo por que es un error de conversion de Srping de la fecha, aparentemente por el mes hay que ponerlo con dos numeros y el form pone sin ceros de primero 3->03 ejemplo
				if (bindingResult.getAllErrors().size()==1 && err.getDefaultMessage().contains("Failed to convert property value of type 'java.lang.String' to required type 'java.time.LocalDate'")) {
					errorNot = true;
				}
			}
			if (bindingResult.hasErrors() && !errorNot) {
				model.addAttribute("game", game);
				model.addAttribute("topics", tsscTopicService.findAll());
				return "/game/save";
			} else {
				String[] data = sDate.split("-");
				LocalDate date = LocalDate.of(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2]));
				game.setScheduledDate(date);
				tsscGameService.saveGame(game,tsscTopicService.findAll());
			}
		}
		return "redirect:/game/";
	}
	
	@GetMapping("/game/edit/{id}")
	public String edit(@PathVariable("id") long id, Model model) {
		Optional<TsscGame> game = tsscGameService.findById(id);
		if (game == null)
			throw new IllegalArgumentException("Invalid game Id:" + id);
		model.addAttribute("game", game.get());
		model.addAttribute("topics", tsscTopicService.findAll());
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
