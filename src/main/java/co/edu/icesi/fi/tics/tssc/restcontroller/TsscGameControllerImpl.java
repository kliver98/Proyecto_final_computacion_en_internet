package co.edu.icesi.fi.tics.tssc.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	@GetMapping("/api/games")
	public Iterable<TsscGame> getAll() {
		return tsscGameService.findAll();
	}

	@GetMapping("/api/games/{id}")
	public TsscGame findById(@PathVariable long id) {
		return tsscGameService.findById(id);
	}

	@PostMapping("/api/games")
	public TsscGame saveTsscGame(@RequestBody TsscGame game) {
		return tsscGameService.save(game, tsscTopicService.findAll());
	}

	@PutMapping("/api/games/update/{game}")
	public TsscGame updateTsscGame(@RequestBody TsscGame game) {
		return tsscGameService.update(game);
	}

	@DeleteMapping("/api/games/delete/{id}")
	public TsscGame deleteTsscGame(@PathVariable long id) {
		return tsscGameService.delete(tsscGameService.findById(id));
	}
	
}
