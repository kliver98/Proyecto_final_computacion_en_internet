package co.edu.icesi.fi.tics.tssc.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.fi.tics.tssc.model.TransactionBody;
import co.edu.icesi.fi.tics.tssc.model.TsscAdmin;
import co.edu.icesi.fi.tics.tssc.service.TsscAdminServiceImpl;

@RestController
public class TsscAdminControllerImpl implements TsscAdminController {

	@Autowired
	private TsscAdminServiceImpl tsscAdminService;

	@GetMapping("/admins")
	public List<TsscAdmin> getAdmins() {
		return (List<TsscAdmin>) tsscAdminService.findAll();
	}

	@GetMapping("/admins/{username}")
	public List<TsscAdmin> getByUsername(@PathVariable String username) {
		return tsscAdminService.findByUser(username);
	}

	@GetMapping("/adminstb")
	public TransactionBody<Iterable<TsscAdmin>> getAdminsTb() {
		TransactionBody<Iterable<TsscAdmin>> tb = new TransactionBody<Iterable<TsscAdmin>>();
		tb.setBody(tsscAdminService.findAll());
		return tb;
	}

	@GetMapping("/adminstb/{username}")
	public ResponseEntity<TransactionBody<List<TsscAdmin>>> getByUsernameTb(@PathVariable String username) {
		List<TsscAdmin> admins = tsscAdminService.findByUser(username);
		TransactionBody<List<TsscAdmin>> transaction = new TransactionBody<List<TsscAdmin>>("TsscAdminTB_ByUsername", admins);
		ResponseEntity<TransactionBody<List<TsscAdmin>>> response = new ResponseEntity<TransactionBody<List<TsscAdmin>>>(transaction,
				HttpStatus.OK);
		return response;
	}
	
	

}
