package co.edu.icesi.fi.tics.tssc.dao;

import java.util.List;

import co.edu.icesi.fi.tics.tssc.model.TsscTopic;

public interface ITsscTopicDAO {

	TsscTopic save(TsscTopic topic);
	
	TsscTopic edit(TsscTopic topic);
	
	List<TsscTopic> findAll();
	
	TsscTopic findById(long id);
	
	boolean delete(TsscTopic topic);
	
	TsscTopic findByName(String name);
	
	TsscTopic findByDescription(String description);
	
}
