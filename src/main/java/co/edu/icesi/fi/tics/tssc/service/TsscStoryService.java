package co.edu.icesi.fi.tics.tssc.service;

import java.util.Optional;

import co.edu.icesi.fi.tics.tssc.model.*;

public interface TsscStoryService {

	TsscStory saveStory(TsscStory story, Iterable<TsscGame> games);
	
	TsscStory editStory(TsscStory story);
	
	Iterable<TsscStory> findAll();
	
	Optional<TsscStory> findById(long id);
	
}
