package co.edu.icesi.fi.tics.tssc.restcontroller;

import org.springframework.http.ResponseEntity;

import co.edu.icesi.fi.tics.tssc.model.TransactionBody;
import co.edu.icesi.fi.tics.tssc.model.TsscGame;

public interface TsscGameController {
	
	Iterable<TsscGame> getAll();
	
	TsscGame findById(long id);
	
	TsscGame saveTsscGame(TsscGame game);
	
	TsscGame updateTsscGame(TsscGame game);
	
	TsscGame deleteTsscGame(long id);
	
	TransactionBody<Iterable<TsscGame>> getAllTb();
	
	ResponseEntity<TransactionBody<TsscGame>> findByIdTb(long id);
	
	ResponseEntity<TransactionBody<TsscGame>> saveTsscGameTb(TransactionBody<TsscGame> game);
	
	ResponseEntity<TransactionBody<TsscGame>> updateTsscGameTb(TransactionBody<TsscGame> game);

	ResponseEntity<TransactionBody<TsscGame>> deleteTsscGameTb(long id);
	
}
