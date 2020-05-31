package co.edu.icesi.fi.tics.tssc.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import co.edu.icesi.fi.tics.tssc.model.TsscAdmin;
import co.edu.icesi.fi.tics.tssc.service.TsscAdminServiceImpl;

@RestController
public class TsscAdminControllerImpl implements TsscAdminController {

	@Autowired
	private TsscAdminServiceImpl tsscAdminService;

	@GetMapping("/api/admins")
	public List<TsscAdmin> getAdmins() {
		return (List<TsscAdmin>) tsscAdminService.findAll();
	}

	@GetMapping("/api/admins/{username}")
	public List<TsscAdmin> getByUsername(@PathVariable String username) {
		return tsscAdminService.findByUser(username);
	}

}
