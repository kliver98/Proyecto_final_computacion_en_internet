package co.edu.icesi.fi.tics.tssc.service;

import co.edu.icesi.fi.tics.tssc.model.TsscTopic;

public interface TsscTopicService {

	TsscTopic saveTopic(TsscTopic topic);
	
	TsscTopic editTopic(TsscTopic topic);
	
	Iterable<TsscTopic> findAll();
	
}
