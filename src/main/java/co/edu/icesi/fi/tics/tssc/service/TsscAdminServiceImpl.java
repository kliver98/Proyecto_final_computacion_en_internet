package co.edu.icesi.fi.tics.tssc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.fi.tics.tssc.dao.TsscAdminDAO;
import co.edu.icesi.fi.tics.tssc.model.AdminType;
import co.edu.icesi.fi.tics.tssc.model.TsscAdmin;

@Service
@Transactional
public class TsscAdminServiceImpl implements TsscAdminService {

	@Autowired
	private TsscAdminDAO tsscAdminDAO;
	
	@Override
	public void save(TsscAdmin user) {
		tsscAdminDAO.save(user);
	}

	@Override
	public TsscAdmin findById(long id) {
		return tsscAdminDAO.findById(id);
	}

	@Override
	public Iterable<TsscAdmin> findAll() {
		return tsscAdminDAO.findAll();
	}

	@Override
	public void delete(TsscAdmin user) {
		tsscAdminDAO.delete(user);
	}

	@Override
	public AdminType[] getTypes() {
		return AdminType.values();
	}

	@Override
	public List<TsscAdmin> findByUser(String username) {
		return tsscAdminDAO.findByUser(username);
	}

}
