package co.edu.icesi.fi.tics.tssc.dao;

import java.util.List;

import co.edu.icesi.fi.tics.tssc.model.TsscStory;

public interface ITsscStoryDAO {

	void save(TsscStory story);
	
	void update(TsscStory story);
	
	TsscStory delete(TsscStory story);
	
	List<TsscStory> findAll();
	
	TsscStory findById(long id);
	
}
