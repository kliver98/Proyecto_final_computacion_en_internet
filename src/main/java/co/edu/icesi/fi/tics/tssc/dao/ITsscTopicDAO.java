package co.edu.icesi.fi.tics.tssc.dao;

import java.util.List;

import co.edu.icesi.fi.tics.tssc.model.TsscTopic;

public interface ITsscTopicDAO {

	void save(TsscTopic topic);
	
	void update(TsscTopic topic);
	
	TsscTopic delete(TsscTopic topic);
	
	List<TsscTopic> findAll();
	
	TsscTopic findById(long id);
	
	List<TsscTopic> findByName(String name);
	
	List<TsscTopic> findByDescription(String description);
	
}
