package co.edu.icesi.fi.tics.tssc.delegate;

import java.util.List;

import co.edu.icesi.fi.tics.tssc.model.TsscAdmin;

public interface TsscAdminDelegateTB {

	List<TsscAdmin> getAdmins();
	
	List<TsscAdmin> getByUsername(String username);
	
}
