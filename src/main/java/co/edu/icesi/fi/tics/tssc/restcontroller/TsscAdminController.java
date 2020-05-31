package co.edu.icesi.fi.tics.tssc.restcontroller;

import java.util.List;

import co.edu.icesi.fi.tics.tssc.model.TsscAdmin;

public interface TsscAdminController {

	List<TsscAdmin> getAdmins();
	
	List<TsscAdmin> getByUsername(String username);
	
}
