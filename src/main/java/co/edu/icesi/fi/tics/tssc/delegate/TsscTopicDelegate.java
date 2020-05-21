package co.edu.icesi.fi.tics.tssc.delegate;

import co.edu.icesi.fi.tics.tssc.model.TsscTopic;

public interface TsscTopicDelegate {

	Iterable<TsscTopic> getAll();
	
	TsscTopic findById(long id);
	
	TsscTopic saveTsscTopic(TsscTopic topic);
	
	TsscTopic updateTsscTopic(TsscTopic topic);

	void deleteTsscTopic(long id);
	
}
