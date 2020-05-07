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
	public void save(TsscStory story) {
		entityManager.persist(story);
	}

	@Override
	public void update(TsscStory story) {
		save(story);
	}

	@Override
	public List<TsscStory> findAll() {
		String query = "Select a from TsscStory a";
		return entityManager.createQuery(query, TsscStory.class).getResultList();
	}

	@Override
	public TsscStory findById(long id) {
		return entityManager.find(TsscStory.class, id);
	}

	@Override
	public void delete(TsscStory story) {
		entityManager.remove(story);
	}

}
