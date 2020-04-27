package co.edu.icesi.fi.tics.tssc.service;

import java.util.Optional;

import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.model.TsscTopic;

public interface TsscGameService {

	TsscGame saveGame(TsscGame game, Iterable<TsscTopic> topics);
	
	TsscGame saveGame2(TsscGame game, Iterable<TsscTopic> topics);
	
	Optional<TsscGame> findById(long id);
	
	TsscGame editGame(TsscGame game);
	
	Iterable<TsscGame> findAll();
	
	void delete(TsscGame game);
	
}
