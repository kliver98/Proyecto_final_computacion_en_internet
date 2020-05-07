package co.edu.icesi.fi.tics.tssc.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.fi.tics.tssc.model.AdminType;
import co.edu.icesi.fi.tics.tssc.model.TsscAdmin;

@Repository
@Scope("singleton")
public class TsscAdminDAO implements ITsscAdminDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<TsscAdmin> findByUser(String user) {
		List<TsscAdmin> found = new ArrayList<TsscAdmin>();
		for (TsscAdmin admin : findAll()) {
			if (admin.getUser().equals(user))
				found.add(admin);
		}
		return found;
	}

	@Override
	public void save(TsscAdmin user) {
		entityManager.persist(user);
	}

	@Override
	public TsscAdmin findById(long id) {
		return entityManager.find(TsscAdmin.class, id);
	}

	@Override
	public List<TsscAdmin> findAll() {
		String query = "Select a from TsscAdmin a";
		return entityManager.createQuery(query, TsscAdmin.class).getResultList();
	}

	@Override
	public void delete(TsscAdmin user) {
		entityManager.remove(user);
	}

	@Override
	public AdminType[] getTypes() {
		return AdminType.values();
	}

}
