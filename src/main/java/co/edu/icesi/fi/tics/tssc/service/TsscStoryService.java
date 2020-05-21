package co.edu.icesi.fi.tics.tssc.service;

import co.edu.icesi.fi.tics.tssc.model.*;

public interface TsscStoryService {

	TsscStory save(TsscStory story, Iterable<TsscGame> games);
	
	TsscStory update(TsscStory story);
	
	Iterable<TsscStory> findAll();
	
	TsscStory findById(long id);
	
	TsscStory delete(TsscStory story);
	
}
