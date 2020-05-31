package co.edu.icesi.fi.tics.tssc.service;

import co.edu.icesi.fi.tics.tssc.model.TsscTimecontrol;

public interface TsscTimeControlService {
	
	
	TsscTimecontrol save(TsscTimecontrol tsscTimecontro);
	
	TsscTimecontrol update(TsscTimecontrol tsscTimecontro);
	
	Iterable<TsscTimecontrol> findAll();
	
	TsscTimecontrol findById(long id);
	
	TsscTimecontrol delete(TsscTimecontrol tsscTimecontro);

}
