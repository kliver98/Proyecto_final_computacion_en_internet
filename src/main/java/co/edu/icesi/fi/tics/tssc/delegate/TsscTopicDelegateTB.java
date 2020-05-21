package co.edu.icesi.fi.tics.tssc.delegate;

import co.edu.icesi.fi.tics.tssc.model.TsscTopic;

public interface TsscTopicDelegateTB {

	Iterable<TsscTopic> getAll();
	
	TsscTopic findById(long id);
	
	TsscTopic saveTsscTopic(TsscTopic topic);
	
	TsscTopic updateTsscTopic(TsscTopic topic);

	TsscTopic deleteTsscTopic(long id);
	
}
