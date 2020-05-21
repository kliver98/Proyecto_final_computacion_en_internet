package co.edu.icesi.fi.tics.tssc.delegate;

import co.edu.icesi.fi.tics.tssc.model.TsscStory;

public interface TsscStoryDelegate {

	Iterable<TsscStory> getAll();
	
	TsscStory findById(long id);
	
	TsscStory saveTsscStory(TsscStory story);
	
	TsscStory updateTsscStory(TsscStory story);

	TsscStory deleteTsscStory(long id);
	
}
