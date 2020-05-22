package co.edu.icesi.fi.tics.tssc.delegate;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.fi.tics.tssc.model.TsscAdmin;

@Component
public class TsscAdminDelegateImpl implements TsscAdminDelegate {

	private RestTemplate restTemplate;
	final String SERVER = "http://localhost:8080/api/";
	
	public TsscAdminDelegateImpl() {
		restTemplate = new RestTemplate();
	}

	@Override
	public List<TsscAdmin> getAdmins() {
		TsscAdmin[] admins = restTemplate.getForObject(SERVER + "admins", TsscAdmin[].class);
		try {
			return Arrays.asList(admins);
		} catch(Exception e) {			
			return null;
		}
	}

	@Override
	public List<TsscAdmin> getByUsername(String username) {
		TsscAdmin[] admins = restTemplate.getForObject(SERVER + "admins/" + username, TsscAdmin[].class);
		try {
			return Arrays.asList(admins);
		} catch(Exception e) {			
			return null;
		}
	}

}
