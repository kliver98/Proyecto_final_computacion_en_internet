package co.edu.icesi.fi.tics.tssc.restcontroller;

import org.springframework.http.ResponseEntity;

import co.edu.icesi.fi.tics.tssc.model.TransactionBody;
import co.edu.icesi.fi.tics.tssc.model.TsscStory;

public interface TsscStoryController {

	Iterable<TsscStory> getAll();
	
	TsscStory findById(long id);
	
	TsscStory saveTsscStory(TsscStory story);
	
	TsscStory updateTsscStory(TsscStory story);

	TsscStory deleteTsscStory(long id);
	
	TransactionBody<Iterable<TsscStory>> getAllTb();
	
	ResponseEntity<TransactionBody<TsscStory>> findByIdTb(long id);
	
	ResponseEntity<TransactionBody<TsscStory>> saveTsscStoryTb(TransactionBody<TsscStory> story);
	
	ResponseEntity<TransactionBody<TsscStory>> updateTsscStoryTb(TransactionBody<TsscStory> story);

	ResponseEntity<TransactionBody<TsscStory>> deleteTsscStoryTb(long id);
	
}
