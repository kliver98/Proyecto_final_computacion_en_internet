package co.edu.icesi.fi.tics.tssc.delegate;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.model.TsscTopic;

@Component
public class TsscTopicDelegateImpl implements TsscTopicDelegate {

	private RestTemplate restTemplate;
	final String SERVER = "http://localhost:8080/api/";
	
	public TsscTopicDelegateImpl() {
		this.restTemplate = new RestTemplate();
	}

	@Override
	public Iterable<TsscTopic> getAll() {
		TsscTopic[] topics = restTemplate.getForObject(SERVER + "topics/", TsscTopic[].class);
		List<TsscTopic> at;
		try {
			at = Arrays.asList(topics);
			return at;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public TsscTopic findById(long id) {
		TsscTopic topic = restTemplate.getForObject(SERVER + "topics/" + id, TsscTopic.class);
		return topic;
	}

	@Override
	public TsscTopic saveTsscTopic(TsscTopic topic) {
		TsscTopic found = restTemplate.postForEntity(SERVER + "topics/", topic, TsscTopic.class).getBody();
		return found;
	}

	@Override
	public TsscTopic updateTsscTopic(TsscTopic topic) {
		restTemplate.put(SERVER + "topics/", topic, TsscGame.class);
		return findById(topic.getId());
	}

	@Override
	public void deleteTsscTopic(long id) {
		restTemplate.delete(SERVER + "topics/delete/"+id);
	}
	
}
