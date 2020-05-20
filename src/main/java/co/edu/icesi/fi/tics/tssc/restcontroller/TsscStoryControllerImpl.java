package co.edu.icesi.fi.tics.tssc.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
	
	@GetMapping("/stories/update/story")
	public TsscStory updateTsscStory(@RequestBody TsscStory story) {
		return tsscStoryService.update(story);
	}
	
	@GetMapping("/stories/delete/{id}")
	public void deleteTsscStory(@PathVariable long id) {
		tsscStoryService.delete(tsscStoryService.findById(id));
	}
	
}
