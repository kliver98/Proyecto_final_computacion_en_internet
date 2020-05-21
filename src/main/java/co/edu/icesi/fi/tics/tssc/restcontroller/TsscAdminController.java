package co.edu.icesi.fi.tics.tssc.restcontroller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import co.edu.icesi.fi.tics.tssc.model.TransactionBody;
import co.edu.icesi.fi.tics.tssc.model.TsscAdmin;

public interface TsscAdminController {

	List<TsscAdmin> getAdmins();
	
	List<TsscAdmin> getByUsername(String username);
	
	TransactionBody<Iterable<TsscAdmin>> getAdminsTb();
	
	ResponseEntity<TransactionBody<List<TsscAdmin>>> getByUsernameTb(String username);
	
}
