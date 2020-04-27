package co.edu.icesi.fi.tics.tssc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;

import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.model.TsscStory;
import co.edu.icesi.fi.tics.tssc.model.TsscTopic;
import co.edu.icesi.fi.tics.tssc.repository.TsscGameRepository;

@Service
@Transactional
public class TsscGameServiceImpl implements TsscGameService {

	@Autowired
	private TsscGameRepository tsscGameRepository;

	@Override
	public TsscGame saveGame(TsscGame game, Iterable<TsscTopic> topics) {
		if (game == null || game.getTsscTopic() == null)
			throw new NullPointerException("Neither the game or topic in the game is null");
		TsscTopic topic = game.getTsscTopic();
		long id = topic.getId();
		for (TsscTopic item : topics) {
			if (item.getId() == id) {
				id = -1;
				break;
			}
		}
		if (id != -1)
			throw new RuntimeException("The topic doesn't exist");// NullPointer for not put throws in signature
		long springs = game.getNSprints(), groups = game.getNGroups();
		if (springs < 1 || groups < 1)
			throw new RuntimeException("There's an error in minimum number of Springs and/or Groups");
		tsscGameRepository.save(game);
		return tsscGameRepository.findById(game.getId()).get();
	}
	
	@Override
	public TsscGame saveGame2(TsscGame game, Iterable<TsscTopic> topics) {
		if (game == null || game.getTsscTopic() == null)
			throw new NullPointerException("Neither the game or topic in the game is null");
		TsscTopic topic = game.getTsscTopic();
		long id = topic.getId();
		for (TsscTopic item : topics) {
			if (item.getId() == id) {
				id = -1;
				break;
			}
		}
		if (id != -1)
			throw new RuntimeException("The topic doesn't exist");// NullPointer for not put throws in signature
		long springs = game.getNSprints(), groups = game.getNGroups();
		if (springs < 1 || groups < 1)
			throw new RuntimeException("There's an error in minimum number of Springs and/or Groups");
		//Creating copies
		Gson gson = new Gson();
		List<TsscStory> copyStories = new ArrayList<TsscStory>();
		for (TsscStory tsscStory : topic.getTsscStories()) {
			copyStories.add(gson.fromJson(gson.toJson(tsscStory), TsscStory.class));
		}
		game.setTsscStories(copyStories);
		//NO SE CUALES SEAN LOS CRONOGRMAS DEL TOPIC, BUSQUE BUSQUE Y NADA AL FIN....POR ESO SOLO HAGO COPIA A LAS STORIES
		//Finish creating copies
		tsscGameRepository.save(game);
		return tsscGameRepository.findById(game.getId()).get();
	}

	@Override
	public TsscGame editGame(TsscGame game) {
		if (game == null)
			new NullPointerException("Can not edit the game because it's null the game to edit");
		long springs = game.getNSprints(), groups = game.getNGroups();
		if (springs < 1 || groups < 1)
			new RuntimeException("There's an error in minimum number of Springs and/or Groups");
		tsscGameRepository.save(game);
		return tsscGameRepository.findById(game.getId()).get();
	}
	
	@Override
	public Iterable<TsscGame> findAll() {
		return tsscGameRepository.findAll();
	}

	@Override
	public Optional<TsscGame> findById(long id) {
		return tsscGameRepository.findById(id);
	}

	public void delete(TsscGame game) {
		tsscGameRepository.delete(game);
	}
	
}
