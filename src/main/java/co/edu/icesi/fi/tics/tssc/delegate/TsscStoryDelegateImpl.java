package co.edu.icesi.fi.tics.tssc.delegate;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TsscStoryDelegateImpl {

	private RestTemplate restTemplate;
	final String SERVER = "http://localhost:8080/";
	
	public TsscStoryDelegateImpl() {
		this.restTemplate = new RestTemplate();
	}
	
}
