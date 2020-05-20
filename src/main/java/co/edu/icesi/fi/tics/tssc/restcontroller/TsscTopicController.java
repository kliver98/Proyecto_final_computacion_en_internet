package co.edu.icesi.fi.tics.tssc.restcontroller;

import co.edu.icesi.fi.tics.tssc.model.TsscTopic;

public interface TsscTopicController {

	Iterable<TsscTopic> getAll();
	
	TsscTopic findById(long id);
	
	TsscTopic saveTsscTopic(TsscTopic topic);
	
	TsscTopic updateTsscTopic(TsscTopic topic);

	void deleteTsscTopic(long id);
	
}
