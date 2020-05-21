package co.edu.icesi.fi.tics.tssc.delegate;

import co.edu.icesi.fi.tics.tssc.model.TsscGame;

public interface TsscGameDelegateTB {

	Iterable<TsscGame> getAll();
	
	TsscGame findById(long id);
	
	TsscGame saveTsscGame(TsscGame game);
	
	TsscGame updateTsscGame(TsscGame game);
	
	TsscGame deleteTsscGame(long id);
	
}
