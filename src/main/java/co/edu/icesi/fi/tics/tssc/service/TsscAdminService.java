package co.edu.icesi.fi.tics.tssc.service;

import co.edu.icesi.fi.tics.tssc.model.AdminType;
import co.edu.icesi.fi.tics.tssc.model.TsscAdmin;

public interface TsscAdminService {
	
	void save(TsscAdmin user);

	TsscAdmin findById(long id);

	Iterable<TsscAdmin> findAll();

	void delete(TsscAdmin user);

	AdminType[] getTypes();
	
}
