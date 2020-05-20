package co.edu.icesi.fi.tics.tssc.service;

import java.util.List;

import co.edu.icesi.fi.tics.tssc.model.AdminType;
import co.edu.icesi.fi.tics.tssc.model.TsscAdmin;

public interface TsscAdminService {
	
	void save(TsscAdmin user);

	TsscAdmin findById(long id);
	
	List<TsscAdmin> findByUser(String username);

	Iterable<TsscAdmin> findAll();

	void delete(TsscAdmin user);

	AdminType[] getTypes();
	
}
