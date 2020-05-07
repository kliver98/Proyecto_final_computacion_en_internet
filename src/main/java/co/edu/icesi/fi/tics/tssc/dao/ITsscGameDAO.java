package co.edu.icesi.fi.tics.tssc.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import co.edu.icesi.fi.tics.tssc.model.TsscGame;

public interface ITsscGameDAO {

	void save(TsscGame game);
	
	void update(TsscGame game);
	
	void delete(TsscGame game);
	
	List<TsscGame> findAll();
	
	TsscGame findById(long id);
	
	List<TsscGame> findByIdTopic(long id);
	
	List<TsscGame> findByNameTopic(String name);
	
	List<TsscGame> findByDescriptionTopic(String description);
	
	List<TsscGame> findByDateRange(LocalDate initial, LocalDate end);
	
	List<TsscGame> findByDateAndTimeRange(LocalDate date, LocalTime initialTime, LocalTime endTime);
	
}
