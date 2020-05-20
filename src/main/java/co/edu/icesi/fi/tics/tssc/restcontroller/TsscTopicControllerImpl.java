package co.edu.icesi.fi.tics.tssc.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.fi.tics.tssc.model.TsscTopic;
import co.edu.icesi.fi.tics.tssc.service.TsscTopicService;

@RestController
public class TsscTopicControllerImpl implements TsscTopicController {

	@Autowired
	private TsscTopicService tsscTopicService;

	@GetMapping("/topics")
	public Iterable<TsscTopic> getAll() {
		return tsscTopicService.findAll();
	}

	@GetMapping("/topics/{id}")
	public TsscTopic findById(@PathVariable long id) {
		return tsscTopicService.findById(id);
	}

	@PostMapping("/topics")
	public TsscTopic saveTsscTopic(@RequestBody TsscTopic topic) {
		return tsscTopicService.save(topic);
	}

	@GetMapping("/topics/update")
	public TsscTopic updateTsscTopic(@RequestBody TsscTopic topic) {
		return tsscTopicService.update(topic);
	}

	@GetMapping("/delete/{id}")
	public void deleteTsscTopic(@PathVariable long id) {
		tsscTopicService.delete(tsscTopicService.findById(id));
	}
	
	

}
