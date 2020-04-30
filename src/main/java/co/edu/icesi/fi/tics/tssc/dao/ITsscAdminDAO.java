package co.edu.icesi.fi.tics.tssc.dao;

import java.util.List;

import co.edu.icesi.fi.tics.tssc.model.AdminType;
import co.edu.icesi.fi.tics.tssc.model.TsscAdmin;

public interface ITsscAdminDAO {

	List<TsscAdmin> findByUser(String user);
	
	TsscAdmin save(TsscAdmin user);

	TsscAdmin findById(long id);

	List<TsscAdmin> findAll();

	boolean delete(TsscAdmin user);

	AdminType[] getTypes();
	
}
