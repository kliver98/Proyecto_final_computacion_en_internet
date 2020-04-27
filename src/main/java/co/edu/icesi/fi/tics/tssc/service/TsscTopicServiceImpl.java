package co.edu.icesi.fi.tics.tssc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.fi.tics.tssc.model.TsscTopic;
import co.edu.icesi.fi.tics.tssc.repository.TsscTopicRepository;

@Service
public class TsscTopicServiceImpl implements TsscTopicService {

	@Autowired
	private TsscTopicRepository tsscTopicRepository;
	
	@Override
	public TsscTopic saveTopic(TsscTopic topic) {
		long springs = topic.getDefaultSprints(), groups = topic.getDefaultGroups();
		if (springs<1 || groups<1)
			throw new RuntimeException("There's an error in minimum number of Springs and/or Groups");
		tsscTopicRepository.save(topic);
		return tsscTopicRepository.findById(topic.getId()).get();
	}

	@Override
	public TsscTopic editTopic(TsscTopic topic) {
		if (topic==null)
			throw new NullPointerException("Can not edit the topic because it's null the topic to edit");
		long springs = topic.getDefaultSprints(), groups = topic.getDefaultSprints();
		if (springs<1 || groups<1)
			throw new RuntimeException("There's an error in minimum number of Springs and/or Groups");
		tsscTopicRepository.save(topic);
		return tsscTopicRepository.findById(topic.getId()).get();
	}

	@Override
	public Iterable<TsscTopic> findAll() {
		return tsscTopicRepository.findAll();
	}
	
	@Override
	public Optional<TsscTopic> findById(long id) {
		return tsscTopicRepository.findById(id);
	}
	
}
