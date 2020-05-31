package co.edu.icesi.fi.tics.tssc.dao;

import java.util.List;

import co.edu.icesi.fi.tics.tssc.model.TsscTimecontrol;

public interface ITsscTimecontrolDAO {
	
	void save(TsscTimecontrol tsscTimeControl);
	
	void update(TsscTimecontrol tsscTimeControl);
	
	TsscTimecontrol delete(TsscTimecontrol tsscTimeControl);
	
	List<TsscTimecontrol> findAll();
	
	TsscTimecontrol findById(long id);
}
