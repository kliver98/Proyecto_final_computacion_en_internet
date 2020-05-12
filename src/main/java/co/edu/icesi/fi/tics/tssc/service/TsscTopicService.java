package co.edu.icesi.fi.tics.tssc.service;

import co.edu.icesi.fi.tics.tssc.model.TsscTopic;

public interface TsscTopicService {

	TsscTopic save(TsscTopic topic);
	
	TsscTopic update(TsscTopic topic);
	
	Iterable<TsscTopic> findAll();
	
	TsscTopic findById(long id);
	
	void delete(TsscTopic topic);
	
}
