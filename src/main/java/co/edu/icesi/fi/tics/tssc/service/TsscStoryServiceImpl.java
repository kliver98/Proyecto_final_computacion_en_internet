package co.edu.icesi.fi.tics.tssc.service;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.fi.tics.tssc.dao.TsscStoryDAO;
import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.model.TsscStory;

@Service
@Transactional
public class TsscStoryServiceImpl implements TsscStoryService {

	@Autowired
	private TsscStoryDAO tsscStoryDAO;
	
	@Override
	public TsscStory save(TsscStory story, Iterable<TsscGame> games) {
		if (story==null || story.getTsscGame()==null)
			throw new NullPointerException("Neither story or game of story is null");
		BigDecimal bValue = story.getBusinessValue(), iSprint = story.getInitialSprint(), priority = story.getPriority();
		if (bValue.compareTo(BigDecimal.ZERO)<1 || iSprint.compareTo(BigDecimal.ZERO)<1 || priority.compareTo(BigDecimal.ZERO)<1)
			throw new RuntimeException("There's an error in minimum number of bussinesValue and/or initialSprint and/or priority");
		TsscGame game = story.getTsscGame();
		long id = game.getId();
		for (TsscGame item : games) {
			if (item.getId() == id) {
				id = -1;
				break;
			}
		}
		if (id != -1)
			throw new RuntimeException("The game doesn't exist");// NullPointer for not put throws in signature
		tsscStoryDAO.save(story);
		return tsscStoryDAO.findById(story.getId());
	}

	@Override
	public TsscStory update(TsscStory story) {
		if (story == null)
			new NullPointerException("Can not edit the story because it's null the story to edit");
		BigDecimal bValue = story.getBusinessValue(), iSprint = story.getInitialSprint(), priority = story.getPriority();
		if (bValue.compareTo(BigDecimal.ZERO)<1 || iSprint.compareTo(BigDecimal.ZERO)<1 || priority.compareTo(BigDecimal.ZERO)<1)
			throw new RuntimeException("There's an error in minimum number of bussinesValue and/or initialSprint and/or priority");
		tsscStoryDAO.update(story);
		return tsscStoryDAO.findById(story.getId());
	}

	@Override
	public TsscStory findById(long id) {
		return tsscStoryDAO.findById(id);
	}

	@Override
	public Iterable<TsscStory> findAll() {
		return tsscStoryDAO.findAll();
	}

	@Override
	public TsscStory delete(TsscStory story) {
		return tsscStoryDAO.delete(story);
	}
	
}
