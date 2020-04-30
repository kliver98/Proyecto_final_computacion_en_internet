package co.edu.icesi.fi.tics.tssc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.fi.tics.tssc.model.TsscTopic;

@Repository
@Scope("singleton")
public class TsscTopicDAO implements ITsscTopicDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public TsscTopic save(TsscTopic topic) {
		entityManager.persist(topic);
		return findById(topic.getId());
	}

	@Override
	public TsscTopic edit(TsscTopic topic) {
		return save(topic);
	}

	@Override
	public List<TsscTopic> findAll() {
		String query = "Select * from TsscTopic";
		return entityManager.createQuery(query, TsscTopic.class).getResultList();
	}

	@Override
	public TsscTopic findById(long id) {
		return entityManager.find(TsscTopic.class, id);
	}

	@Override
	public boolean delete(TsscTopic topic) {
		entityManager.remove(topic);
		return findById(topic.getId())==null;
	}

	@Override
	public TsscTopic findByName(String name) {
		for (TsscTopic topic : findAll()) {
			if (topic.getName().equals(name))
				return topic;
		}
		return null;
	}

	@Override
	public TsscTopic findByDescription(String description) {
		for (TsscTopic topic : findAll()) {
			if (topic.getDescription().equals(description))
				return topic;
		}
		return null;
	}

}
