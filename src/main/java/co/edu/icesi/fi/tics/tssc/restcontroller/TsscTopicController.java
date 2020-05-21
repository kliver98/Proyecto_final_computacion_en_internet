package co.edu.icesi.fi.tics.tssc.restcontroller;

import org.springframework.http.ResponseEntity;

import co.edu.icesi.fi.tics.tssc.model.TransactionBody;
import co.edu.icesi.fi.tics.tssc.model.TsscTopic;

public interface TsscTopicController {

	Iterable<TsscTopic> getAll();
	
	TsscTopic findById(long id);
	
	TsscTopic saveTsscTopic(TsscTopic topic);
	
	TsscTopic updateTsscTopic(TsscTopic topic);

	TsscTopic deleteTsscTopic(long id);
	
	TransactionBody<Iterable<TsscTopic>> getAllTb();
	
	ResponseEntity<TransactionBody<TsscTopic>> findByIdTb(long id);
	
	ResponseEntity<TransactionBody<TsscTopic>> saveTsscTopicTb(TransactionBody<TsscTopic> topic);
	
	ResponseEntity<TransactionBody<TsscTopic>> updateTsscTopicTb(TransactionBody<TsscTopic> topic);

	ResponseEntity<TransactionBody<TsscTopic>> deleteTsscTopicTb(long id);
	
}
