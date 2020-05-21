package co.edu.icesi.fi.tics.tssc.delegate;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.fi.tics.tssc.model.TsscGame;

@Component
public class TsscGameDelegateImpl implements TsscGameDelegate {

	private RestTemplate restTemplate;
	final String SERVER = "http://localhost:8080/api/";
	
	public TsscGameDelegateImpl() {
		this.restTemplate = new RestTemplate();
	}

	@Override
	public Iterable<TsscGame> getAll() {
		TsscGame[] games = restTemplate.getForObject(SERVER + "games", TsscGame[].class);
		List<TsscGame> at;
		try {
			at = Arrays.asList(games);
			return at;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public TsscGame findById(long id) {
		TsscGame game = restTemplate.getForObject(SERVER + "games/" + id, TsscGame.class);
		return game;
	}

	@Override
	public TsscGame saveTsscGame(TsscGame game) {
		TsscGame found = restTemplate.postForEntity(SERVER + "games/", game, TsscGame.class).getBody();
		return found;
	}

	@Override
	public TsscGame updateTsscGame(TsscGame game) {
		restTemplate.put(SERVER + "games/", game, TsscGame.class);
		return findById(game.getId());
	}

	@Override
	public void deleteTsscGame(long id) {
		restTemplate.delete(SERVER + "games/delete/"+id);
	}
	
}
