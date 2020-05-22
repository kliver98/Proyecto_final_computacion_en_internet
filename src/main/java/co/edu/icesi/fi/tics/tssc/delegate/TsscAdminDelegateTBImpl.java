package co.edu.icesi.fi.tics.tssc.delegate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.fi.tics.tssc.model.TransactionBody;
import co.edu.icesi.fi.tics.tssc.model.TsscAdmin;

@Component
public class TsscAdminDelegateTBImpl implements TsscAdminDelegateTB {

	private RestTemplate restTemplate;
	final String SERVER = "http://localhost:8080/api/";
	
	public TsscAdminDelegateTBImpl() {
		this.restTemplate = new RestTemplate();
	}

	@Override
	public List<TsscAdmin> getAdmins() {
		TransactionBody<List<TsscAdmin>> transaction = new TransactionBody<>("adminList", new ArrayList<TsscAdmin>());
		HttpEntity<TransactionBody<List<TsscAdmin>>> request = new HttpEntity<>(transaction);
		ResponseEntity<TransactionBody<List<TsscAdmin>>> response = null;

		response = restTemplate.exchange(SERVER + "/adminstb", HttpMethod.GET, request,
				new ParameterizedTypeReference<TransactionBody<List<TsscAdmin>>>() {
				});
		try {

			List<TsscAdmin> at = response.getBody().getBody();
			return at;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<TsscAdmin> getByUsername(String username) {
		TransactionBody<String> transaction = new TransactionBody<>("adminusername", username);
		HttpEntity<TransactionBody<String>> request = new HttpEntity<>(transaction);
		ResponseEntity<TransactionBody<TsscAdmin>> response = null;

		response = restTemplate.exchange(SERVER + "/adminstb/"+username, HttpMethod.GET, request,
				new ParameterizedTypeReference<TransactionBody<TsscAdmin>>() {
				});
		try {

			List<TsscAdmin> at = new ArrayList<TsscAdmin>();
			at.add(response.getBody().getBody());
			return at;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
