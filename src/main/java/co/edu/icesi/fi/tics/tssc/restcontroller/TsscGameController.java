package co.edu.icesi.fi.tics.tssc.restcontroller;

import co.edu.icesi.fi.tics.tssc.model.TsscGame;

public interface TsscGameController {
	
	Iterable<TsscGame> getAll();
	
	TsscGame findById(long id);
	
	TsscGame saveTsscGame(TsscGame game);
	
	TsscGame updateTsscGame(TsscGame game);
	
	TsscGame deleteTsscGame(long id);
	
}
