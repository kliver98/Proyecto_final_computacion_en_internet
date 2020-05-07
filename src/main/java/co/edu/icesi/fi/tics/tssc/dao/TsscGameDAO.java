package co.edu.icesi.fi.tics.tssc.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.fi.tics.tssc.model.TsscGame;

@Repository
@Scope("singleton")
public class TsscGameDAO implements ITsscGameDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(TsscGame game) {
		entityManager.persist(game);
	}

	@Override
	public TsscGame findById(long id) {
		return entityManager.find(TsscGame.class, id);
	}

	@Override
	public void update(TsscGame game) {
		save(game);
	}

	@Override
	public List<TsscGame> findAll() {
		String query = "Select a from TsscGame a";
		return entityManager.createQuery(query, TsscGame.class).getResultList();
	}

	@Override
	public void delete(TsscGame game) {
		entityManager.remove(game);
	}

	@Override
	public List<TsscGame> findByIdTopic(long id) {
		List<TsscGame> found = new ArrayList<TsscGame>();
		for (TsscGame game : findAll()) {
			if (game.getTsscTopic()!=null && game.getTsscTopic().getId()==id)
				found.add(game);
		}
		return found;
	}

	@Override
	public List<TsscGame> findByNameTopic(String name) {
		List<TsscGame> found = new ArrayList<TsscGame>();
		for (TsscGame game : findAll()) {
			if (game.getTsscTopic()!=null && game.getTsscTopic().getName().equals(name))
				found.add(game);
		}
		return found;
	}

	@Override
	public List<TsscGame> findByDescriptionTopic(String description) {
		List<TsscGame> found = new ArrayList<TsscGame>();
		for (TsscGame game : findAll()) {
			if (game.getTsscTopic()!=null && game.getTsscTopic().getDescription().equals(description))
				found.add(game);
		}
		return found;
	}

	@Override
	public List<TsscGame> findByDateRange(LocalDate initial, LocalDate end) {
		List<TsscGame> found = new ArrayList<TsscGame>();
		for (TsscGame game : findAll()) {
			LocalDate date = game.getScheduledDate();
			if (date!=null) {
				if (date.compareTo(initial)>=1 && date.compareTo(end)<=0)
					found.add(game);
			}
		}
		return found;
	}

	@Override
	public List<TsscGame> findByDateAndTimeRange(LocalDate date, LocalTime initialTime, LocalTime endTime) {
		List<TsscGame> found = new ArrayList<TsscGame>();
		for (TsscGame game : findAll()) {
			if (game.getScheduledDate()!=null && game.getScheduledDate().equals(date)) {
				LocalTime time = game.getScheduledTime();
				if (time.compareTo(initialTime)>=1 && time.compareTo(endTime)<=0)
					found.add(game);
			}
		}
		return found;
	}

}
