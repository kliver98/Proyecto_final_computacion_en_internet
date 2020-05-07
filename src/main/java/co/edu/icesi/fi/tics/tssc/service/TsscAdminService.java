package co.edu.icesi.fi.tics.tssc.service;

import co.edu.icesi.fi.tics.tssc.model.AdminType;
import co.edu.icesi.fi.tics.tssc.model.TsscAdmin;

public interface TsscAdminService {
	public void save(TsscAdmin user);

	public TsscAdmin findById(long id);

	public Iterable<TsscAdmin> findAll();

	public void delete(TsscAdmin user);

	public AdminType[] getTypes();
}
