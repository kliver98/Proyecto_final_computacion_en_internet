package co.edu.icesi.fi.tics.tssc.delegate;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TsscGameDelegateImpl {

	private RestTemplate restTemplate;
	final String SERVER = "http://localhost:8080/";
	
	public TsscGameDelegateImpl() {
		this.restTemplate = new RestTemplate();
	}
	
}
