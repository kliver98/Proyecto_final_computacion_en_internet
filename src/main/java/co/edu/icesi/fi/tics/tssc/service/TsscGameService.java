package co.edu.icesi.fi.tics.tssc.service;

import java.time.LocalDate;
import java.util.List;

import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.model.TsscTopic;

public interface TsscGameService {

	TsscGame save(TsscGame game, Iterable<TsscTopic> topics);
	
	TsscGame save2(TsscGame game, Iterable<TsscTopic> topics);
	
	TsscGame findById(long id);
	
	TsscGame update(TsscGame game);
	
	Iterable<TsscGame> findAll();
	
	TsscGame delete(TsscGame game);
	
	
	List<TsscTopic[]> findTopicByDate(LocalDate localDate);
	
}
