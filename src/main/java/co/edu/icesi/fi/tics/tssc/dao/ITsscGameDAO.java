package co.edu.icesi.fi.tics.tssc.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import co.edu.icesi.fi.tics.tssc.model.TsscGame;

public interface ITsscGameDAO {

	TsscGame save(TsscGame game);
	
	TsscGame findById(long id);
	
	TsscGame edit(TsscGame game);
	
	List<TsscGame> findAll();
	
	boolean delete(TsscGame game);
	
	List<TsscGame> findByIdTopic(long id);
	
	List<TsscGame> findByNameTopic(String name);
	
	List<TsscGame> findByDescriptionTopic(String description);
	
	List<TsscGame> findByDateRange(LocalDate initial, LocalDate end);
	
	List<TsscGame> findByDateAndTimeRange(LocalDate date, LocalTime initialTime, LocalTime endTime);
	
}
