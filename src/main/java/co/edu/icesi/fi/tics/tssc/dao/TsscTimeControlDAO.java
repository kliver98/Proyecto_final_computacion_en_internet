package co.edu.icesi.fi.tics.tssc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.fi.tics.tssc.model.TsscTimecontrol;

@Repository
@Scope("singleton")
public class TsscTimeControlDAO implements ITsscTimecontrolDAO {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(TsscTimecontrol tsscTimeControl) {
		entityManager.persist(tsscTimeControl);
	}

	@Override
	public void update(TsscTimecontrol tsscTimeControl) {
		entityManager.merge(tsscTimeControl);
		
	}

	@Override
	public TsscTimecontrol delete(TsscTimecontrol tsscTimeControl) {
		entityManager.remove(tsscTimeControl);
		return tsscTimeControl;
	}

	@Override
	public List<TsscTimecontrol> findAll() {
		String q = "SELECT a FROM TsscTimecontrol a";
		return entityManager.createQuery(q,TsscTimecontrol.class).getResultList();
	}

	@Override
	public TsscTimecontrol findById(long id) {
		return entityManager.find(TsscTimecontrol.class, id);
	}
	
	
	

}
