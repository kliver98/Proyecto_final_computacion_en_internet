package co.edu.icesi.fi.tics.tssc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.fi.tics.tssc.model.TsscStory;

@Repository
@Scope("singleton")
public class TsscStoryDAO implements ITsscStoryDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public TsscStory save(TsscStory story) {
		entityManager.persist(story);
		return entityManager.find(TsscStory.class, story.getId());
	}

	@Override
	public TsscStory edit(TsscStory story) {
		save(story);
		return findById(story.getId());
	}

	@Override
	public List<TsscStory> findAll() {
		String query = "Select * from TsscStory";
		return entityManager.createQuery(query, TsscStory.class).getResultList();
	}

	@Override
	public TsscStory findById(long id) {
		return entityManager.find(TsscStory.class, id);
	}

	@Override
	public boolean delete(TsscStory story) {
		entityManager.remove(story);
		return findById(story.getId())==null;
	}

}
