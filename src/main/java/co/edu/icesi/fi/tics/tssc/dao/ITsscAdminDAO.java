package co.edu.icesi.fi.tics.tssc.dao;

import java.util.List;

import co.edu.icesi.fi.tics.tssc.model.AdminType;
import co.edu.icesi.fi.tics.tssc.model.TsscAdmin;

public interface ITsscAdminDAO {

	void save(TsscAdmin user);
	
	void delete(TsscAdmin user);
	
	List<TsscAdmin> findByUser(String user);

	TsscAdmin findById(long id);

	List<TsscAdmin> findAll();

	AdminType[] getTypes();
	
}
