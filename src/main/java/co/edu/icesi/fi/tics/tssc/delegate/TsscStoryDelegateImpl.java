package co.edu.icesi.fi.tics.tssc.delegate;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.model.TsscStory;

@Component
public class TsscStoryDelegateImpl implements TsscStoryDelegate{

	private RestTemplate restTemplate;
	final String SERVER = "http://localhost:8080/api/";
	
	public TsscStoryDelegateImpl() {
		this.restTemplate = new RestTemplate();
	}

	@Override
	public Iterable<TsscStory> getAll() {
		TsscStory[] stories = restTemplate.getForObject(SERVER + "stories", TsscStory[].class);
		List<TsscStory> at;
		try {
			at = Arrays.asList(stories);
			return at;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public TsscStory findById(long id) {
		TsscStory story = restTemplate.getForObject(SERVER + "games/" + id, TsscStory.class);
		return story;
	}

	@Override
	public TsscStory saveTsscStory(TsscStory story) {
		TsscStory found = restTemplate.postForEntity(SERVER + "story/", story, TsscStory.class).getBody();
		return found;
	}

	@Override
	public TsscStory updateTsscStory(TsscStory story) {
		restTemplate.put(SERVER + "games/", story, TsscGame.class);
		return findById(story.getId());
	}

	@Override
	public void deleteTsscStory(long id) {
		restTemplate.delete(SERVER + "story/delete/"+id);
	}
	
}
