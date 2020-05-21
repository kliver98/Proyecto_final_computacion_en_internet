package co.edu.icesi.fi.tics.tssc.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.fi.tics.tssc.model.TransactionBody;
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
	public TsscGame deleteTsscGame(@PathVariable long id) {
		return tsscGameService.delete(tsscGameService.findById(id));
	}

	@GetMapping("/gamestb")
	public TransactionBody<Iterable<TsscGame>> getAllTb() {
		TransactionBody<Iterable<TsscGame>> tb = new TransactionBody<Iterable<TsscGame>>();
		tb.setBody(tsscGameService.findAll());
		return tb;
	}

	@GetMapping("/gamestb/{id}")
	public ResponseEntity<TransactionBody<TsscGame>> findByIdTb(@PathVariable long id) {
		TsscGame story = tsscGameService.findById(id);
		TransactionBody<TsscGame> transaction = new TransactionBody<TsscGame>("NewTsscGame", story);
		ResponseEntity<TransactionBody<TsscGame>> response = new ResponseEntity<TransactionBody<TsscGame>>(transaction,
				HttpStatus.OK);
		return response;
	}

	@PostMapping("/gamestb")
	public ResponseEntity<TransactionBody<TsscGame>> saveTsscGameTb(@RequestBody TransactionBody<TsscGame> story) {
		TsscGame newstory = story.getBody();
		tsscGameService.save(newstory,tsscTopicService.findAll());
		TransactionBody<TsscGame> transaction = new TransactionBody<TsscGame>("NewTsscGame", newstory);
		ResponseEntity<TransactionBody<TsscGame>> response = new ResponseEntity<TransactionBody<TsscGame>>(transaction,
				HttpStatus.OK);
		return response;
	}

	@PostMapping("/gamestb/update")
	public ResponseEntity<TransactionBody<TsscGame>> updateTsscGameTb(@RequestBody TransactionBody<TsscGame> story) {
		TsscGame newstory = story.getBody();
		tsscGameService.update(newstory);
		TransactionBody<TsscGame> transaction = new TransactionBody<TsscGame>("NewTsscGame", newstory);
		ResponseEntity<TransactionBody<TsscGame>> response = new ResponseEntity<TransactionBody<TsscGame>>(transaction,
				HttpStatus.OK);
		return response;
	}

	@DeleteMapping("/gamestb/{id}")
	public ResponseEntity<TransactionBody<TsscGame>> deleteTsscGameTb(@PathVariable long id) {
		TsscGame story = tsscGameService.findById(id);
		if (story==null)
			return new ResponseEntity<TransactionBody<TsscGame>>(new TransactionBody<TsscGame>(),HttpStatus.BAD_REQUEST);
		tsscGameService.delete(story);
		TransactionBody<TsscGame> transaction = new TransactionBody<TsscGame>("NewTsscGame", story);
		ResponseEntity<TransactionBody<TsscGame>> response = new ResponseEntity<TransactionBody<TsscGame>>(transaction,
				HttpStatus.OK);
		return response;
	}
	
}
