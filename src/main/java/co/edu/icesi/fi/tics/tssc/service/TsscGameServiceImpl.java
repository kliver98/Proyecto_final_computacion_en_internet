package co.edu.icesi.fi.tics.tssc.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;

import co.edu.icesi.fi.tics.tssc.dao.TsscGameDAO;
import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.model.TsscStory;
import co.edu.icesi.fi.tics.tssc.model.TsscTopic;

@Service
@Transactional
public class TsscGameServiceImpl implements TsscGameService {

	@Autowired
	private TsscGameDAO tsscGameDAO;

	@Override
	public TsscGame save(TsscGame game, Iterable<TsscTopic> topics) {
		if (game == null)
			throw new NullPointerException("Neither the game or topic in the game is null");
		TsscTopic topic = game.getTsscTopic();
		if (topic!=null) {
			long id = topic.getId();
			for (TsscTopic item : topics) {
				if (item.getId() == id) {
					id = -1;
					break;
				}
			}
			if (id != -1)
				throw new RuntimeException("The topic doesn't exist");// NullPointer for not put throws in signature
		}
		long springs = game.getNSprints(), groups = game.getNGroups();
		if (springs < 1 || groups < 1)
			throw new RuntimeException("There's an error in minimum number of Springs and/or Groups");
		tsscGameDAO.save(game);
		return tsscGameDAO.findById(game.getId());
	}
	
	@Override
	public TsscGame save2(TsscGame game, Iterable<TsscTopic> topics) {
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
			copyStories.add(gson.fromJson(gson.toJson(tsscStory),TsscStory.class));
		}
		game.setTsscStories(copyStories);
		//NO SE CUALES SEAN LOS CRONOGRMAS DEL TOPIC, BUSQUE BUSQUE Y NADA AL FIN....POR ESO SOLO HAGO COPIA A LAS STORIES
		//Finish creating copies
		tsscGameDAO.save(game);
		return tsscGameDAO.findById(game.getId());
	}

	@Override
	public TsscGame update(TsscGame game) {
		if (game == null)
			new NullPointerException("Can not edit the game because it's null the game to edit");
		long springs = game.getNSprints(), groups = game.getNGroups();
		if (springs < 1 || groups < 1)
			new RuntimeException("There's an error in minimum number of Springs and/or Groups");
		tsscGameDAO.update(game);
		return tsscGameDAO.findById(game.getId());
	}
	
	@Override
	public Iterable<TsscGame> findAll() {
		return tsscGameDAO.findAll();
	}

	@Override
	public TsscGame findById(long id) {
		return tsscGameDAO.findById(id);
	}

	public TsscGame delete(TsscGame game) {
		return tsscGameDAO.delete(game);
	}

	@Override
	public List<TsscTopic[]> findTopicByDate(LocalDate localDate) {
		return tsscGameDAO.findTopicsByDate(localDate);
	}
	
	
	
}
