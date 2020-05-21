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
import co.edu.icesi.fi.tics.tssc.model.TsscStory;
import co.edu.icesi.fi.tics.tssc.service.TsscGameService;
import co.edu.icesi.fi.tics.tssc.service.TsscStoryService;

@RestController
public class TsscStoryControllerImpl implements TsscStoryController {

	@Autowired
	private TsscStoryService tsscStoryService;
	@Autowired
	private TsscGameService tsscGameService;
	
	@GetMapping("/stories")
	public Iterable<TsscStory> getAll() {
		return tsscStoryService.findAll();
	}
	
	@GetMapping("/stories/{id}")
	public TsscStory findById(@PathVariable long id) {
		return tsscStoryService.findById(id);
	}
	
	@PostMapping("/stories")
	public TsscStory saveTsscStory(@RequestBody TsscStory story) {
		return tsscStoryService.save(story, tsscGameService.findAll());
	}
	
	@PostMapping("/stories/update/story")
	public TsscStory updateTsscStory(@RequestBody TsscStory story) {
		return tsscStoryService.update(story);
	}
	
	@GetMapping("/stories/delete/{id}")
	public TsscStory deleteTsscStory(@PathVariable long id) {
		return tsscStoryService.delete(tsscStoryService.findById(id));
	}
	
	@GetMapping("/storiestb")
	public TransactionBody<Iterable<TsscStory>> getAllTb() {
		TransactionBody<Iterable<TsscStory>> tb = new TransactionBody<Iterable<TsscStory>>();
		tb.setBody(tsscStoryService.findAll());
		return tb;
	}

	@GetMapping("/storiestb/{id}")
	public ResponseEntity<TransactionBody<TsscStory>> findByIdTb(@PathVariable long id) {
		TsscStory story = tsscStoryService.findById(id);
		TransactionBody<TsscStory> transaction = new TransactionBody<TsscStory>("NewTsscStory", story);
		ResponseEntity<TransactionBody<TsscStory>> response = new ResponseEntity<TransactionBody<TsscStory>>(transaction,
				HttpStatus.OK);
		return response;
	}

	@PostMapping("/storiestb")
	public ResponseEntity<TransactionBody<TsscStory>> saveTsscStoryTb(@RequestBody TransactionBody<TsscStory> story) {
		TsscStory newstory = story.getBody();
		tsscStoryService.save(newstory,tsscGameService.findAll());
		TransactionBody<TsscStory> transaction = new TransactionBody<TsscStory>("NewTsscStory", newstory);
		ResponseEntity<TransactionBody<TsscStory>> response = new ResponseEntity<TransactionBody<TsscStory>>(transaction,
				HttpStatus.OK);
		return response;
	}

	@PostMapping("/storiestb/update")
	public ResponseEntity<TransactionBody<TsscStory>> updateTsscStoryTb(@RequestBody TransactionBody<TsscStory> story) {
		TsscStory newstory = story.getBody();
		tsscStoryService.update(newstory);
		TransactionBody<TsscStory> transaction = new TransactionBody<TsscStory>("NewTsscStory", newstory);
		ResponseEntity<TransactionBody<TsscStory>> response = new ResponseEntity<TransactionBody<TsscStory>>(transaction,
				HttpStatus.OK);
		return response;
	}

	@DeleteMapping("/storiestb/{id}")
	public ResponseEntity<TransactionBody<TsscStory>> deleteTsscStoryTb(@PathVariable long id) {
		TsscStory story = tsscStoryService.findById(id);
		if (story==null)
			return new ResponseEntity<TransactionBody<TsscStory>>(new TransactionBody<TsscStory>(),HttpStatus.BAD_REQUEST);
		tsscStoryService.delete(story);
		TransactionBody<TsscStory> transaction = new TransactionBody<TsscStory>("NewTsscStory", story);
		ResponseEntity<TransactionBody<TsscStory>> response = new ResponseEntity<TransactionBody<TsscStory>>(transaction,
				HttpStatus.OK);
		return response;
	}
	
}
