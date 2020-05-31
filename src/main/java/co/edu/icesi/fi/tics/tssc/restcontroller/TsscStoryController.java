package co.edu.icesi.fi.tics.tssc.restcontroller;

import co.edu.icesi.fi.tics.tssc.model.TsscStory;

public interface TsscStoryController {

	Iterable<TsscStory> getAll();
	
	TsscStory findById(long id);
	
	TsscStory saveTsscStory(TsscStory story);
	
	TsscStory updateTsscStory(TsscStory story);

	TsscStory deleteTsscStory(long id);
	
}
