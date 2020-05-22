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
import co.edu.icesi.fi.tics.tssc.model.TsscTopic;
import co.edu.icesi.fi.tics.tssc.service.TsscTopicService;

@RestController
public class TsscTopicControllerImpl implements TsscTopicController {

	@Autowired
	private TsscTopicService tsscTopicService;

	@GetMapping("/api/topics")
	public Iterable<TsscTopic> getAll() {
		return tsscTopicService.findAll();
	}

	@GetMapping("/api/topics/{id}")
	public TsscTopic findById(@PathVariable long id) {
		return tsscTopicService.findById(id);
	}

	@PostMapping("/api/topics")
	public TsscTopic saveTsscTopic(@RequestBody TsscTopic topic) {
		return tsscTopicService.save(topic);
	}

	@PostMapping("/api/topics/update")
	public TsscTopic updateTsscTopic(@RequestBody TsscTopic topic) {
		return tsscTopicService.update(topic);
	}

	@DeleteMapping("/api/delete/{id}")
	public TsscTopic deleteTsscTopic(@PathVariable long id) {
		return tsscTopicService.delete(tsscTopicService.findById(id));
	}

	@GetMapping("/api/topicstb")
	public TransactionBody<Iterable<TsscTopic>> getAllTb() {
		TransactionBody<Iterable<TsscTopic>> tb = new TransactionBody<Iterable<TsscTopic>>();
		tb.setBody(tsscTopicService.findAll());
		return tb;
	}

	@GetMapping("/api/topicstb/{id}")
	public ResponseEntity<TransactionBody<TsscTopic>> findByIdTb(@PathVariable long id) {
		TsscTopic topic = tsscTopicService.findById(id);
		TransactionBody<TsscTopic> transaction = new TransactionBody<TsscTopic>("NewTsscTopic", topic);
		ResponseEntity<TransactionBody<TsscTopic>> response = new ResponseEntity<TransactionBody<TsscTopic>>(transaction,
				HttpStatus.OK);
		return response;
	}

	@PostMapping("/api/topicstb")
	public ResponseEntity<TransactionBody<TsscTopic>> saveTsscTopicTb(@RequestBody TransactionBody<TsscTopic> topic) {
		TsscTopic NewTsscTopic = topic.getBody();
		tsscTopicService.save(NewTsscTopic);
		TransactionBody<TsscTopic> transaction = new TransactionBody<TsscTopic>("NewTsscTopic", NewTsscTopic);
		ResponseEntity<TransactionBody<TsscTopic>> response = new ResponseEntity<TransactionBody<TsscTopic>>(transaction,
				HttpStatus.OK);
		return response;
	}

	@PostMapping("/api/topicstb/update")
	public ResponseEntity<TransactionBody<TsscTopic>> updateTsscTopicTb(@RequestBody TransactionBody<TsscTopic> topic) {
		TsscTopic NewTsscTopic = topic.getBody();
		tsscTopicService.update(NewTsscTopic);
		TransactionBody<TsscTopic> transaction = new TransactionBody<TsscTopic>("NewTsscTopic", NewTsscTopic);
		ResponseEntity<TransactionBody<TsscTopic>> response = new ResponseEntity<TransactionBody<TsscTopic>>(transaction,
				HttpStatus.OK);
		return response;
	}

	@DeleteMapping("/api/topicstb/{id}")
	public ResponseEntity<TransactionBody<TsscTopic>> deleteTsscTopicTb(@PathVariable long id) {
		TsscTopic topic = tsscTopicService.findById(id);
		if (topic==null)
			return new ResponseEntity<TransactionBody<TsscTopic>>(new TransactionBody<TsscTopic>(),HttpStatus.BAD_REQUEST);
		tsscTopicService.delete(topic);
		TransactionBody<TsscTopic> transaction = new TransactionBody<TsscTopic>("NewTsscTopic", topic);
		ResponseEntity<TransactionBody<TsscTopic>> response = new ResponseEntity<TransactionBody<TsscTopic>>(transaction,
				HttpStatus.OK);
		return response;
	}
	
}
