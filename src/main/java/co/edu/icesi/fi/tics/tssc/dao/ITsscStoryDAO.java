package co.edu.icesi.fi.tics.tssc.dao;

import java.util.List;

import co.edu.icesi.fi.tics.tssc.model.TsscStory;

public interface ITsscStoryDAO {

	TsscStory save(TsscStory story);
	
	TsscStory edit(TsscStory story);
	
	List<TsscStory> findAll();
	
	TsscStory findById(long id);
	
	boolean delete(TsscStory story);
	
}
