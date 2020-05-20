package co.edu.icesi.fi.tics.tssc.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.service.TsscGameService;
import co.edu.icesi.fi.tics.tssc.service.TsscTopicService;

@RestController
public class TsscGameControllerImpl implements TsscGameController {

	@Autowired
	private TsscGameService tsscGameService;
	@Autowired
	private TsscTopicService tsscTopicService;

	@GetMapping("/games")
	public Iterable<TsscGame> getAll() {
		return tsscGameService.findAll();
	}

	@GetMapping("/games/{id}")
	public TsscGame findById(@PathVariable long id) {
		return tsscGameService.findById(id);
	}

	@PostMapping("/games")
	public TsscGame saveTsscGame(@RequestBody TsscGame game) {
		return tsscGameService.save(game, tsscTopicService.findAll());
	}

	@GetMapping("/games/update/{game}")
	public TsscGame updateTsscGame(@RequestBody TsscGame game) {
		return tsscGameService.update(game);
	}

	@GetMapping("/games/delete/{id}")
	public void deleteTsscGame(@PathVariable long id) {
		tsscGameService.delete(tsscGameService.findById(id));
	}

}
