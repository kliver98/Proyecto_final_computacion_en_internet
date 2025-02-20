package co.edu.icesi.fi.tics.tssc.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import co.edu.icesi.fi.tics.tssc.model.TsscTopic;

public interface TsscTopicService {

	TsscTopic save(TsscTopic topic);
	
	TsscTopic update(TsscTopic topic);
	
	Iterable<TsscTopic> findAll();
	
	TsscTopic findById(long id);
	
	TsscTopic delete(TsscTopic topic);

	
	
}
