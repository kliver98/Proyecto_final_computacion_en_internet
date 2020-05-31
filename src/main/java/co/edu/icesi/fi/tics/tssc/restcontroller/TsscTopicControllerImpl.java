package co.edu.icesi.fi.tics.tssc.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
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

	@PutMapping("/api/topics/update/{topic}")
	public TsscTopic updateTsscTopic(@RequestBody TsscTopic topic) {
		return tsscTopicService.update(topic);
	}

	@DeleteMapping("/api/delete/{id}")
	public TsscTopic deleteTsscTopic(@PathVariable long id) {
		return tsscTopicService.delete(tsscTopicService.findById(id));
	}
	
}
