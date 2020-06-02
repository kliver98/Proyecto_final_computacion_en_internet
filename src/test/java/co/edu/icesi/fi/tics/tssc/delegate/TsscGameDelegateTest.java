package co.edu.icesi.fi.tics.tssc.delegate;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.StreamSupport;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import co.edu.icesi.fi.tics.tssc.model.TsscGame;

@SpringBootTest
public class TsscGameDelegateTest {

	private TsscGameDelegate tsscGameDelegate;
	private List<TsscGame> list;
	private TsscGame gameTest;
	
	public void configure() {
		tsscGameDelegate = mock(TsscGameDelegate.class);
		list = new ArrayList<TsscGame>();
		gameTest = new TsscGame();
		gameTest.setId(1);
		gameTest.setName("Game random#"+(new Random().nextInt()));
		list.add(gameTest);
		
		//This method is supposing it's fine, so... any error, checkout this method first
		when(tsscGameDelegate.findById(any(Long.class))).thenAnswer(i -> {
		    Long id = i.getArgumentAt(0, Long.class);
		    for (TsscGame tc : list) {
				if (tc.getId()==id)
					return tc;
			}
		    return null;
		});
	}
	
	@Test
	public void getAll() {
		configure();
		when(tsscGameDelegate.getAll()).thenReturn(list);
		assertEquals(StreamSupport.stream(tsscGameDelegate.getAll().spliterator(), false).count(),list.size());
	}
	
	@Test
	public void findById() {
		configure();
		when(tsscGameDelegate.findById(list.get(0).getId())).thenReturn(list.get(0));
		assertTrue(tsscGameDelegate.findById(list.get(0).getId()).getName().equals(list.get(0).getName()));
		assertNull("The index is minus one of the first, it shouldn't find it",tsscGameDelegate.findById(list.get(0).getId()-1));
	}
	
	@Test
	public void saveTsscGame() {
		configure();
		TsscGame game = new TsscGame();
		game.setId(2);game.setName("NewName"+(new Random().nextInt()));
		when(tsscGameDelegate.saveTsscGame(any(TsscGame.class))).thenAnswer(i -> {
			TsscGame tmp = i.getArgumentAt(0, TsscGame.class);
		    list.add(tmp);
		    return tmp;
		});
		TsscGame returned = tsscGameDelegate.saveTsscGame(game);
		assertTrue(returned.getId()==tsscGameDelegate.findById(returned.getId()).getId());
		assertTrue(returned.getName().equals(tsscGameDelegate.findById(returned.getId()).getName()));
	}
	
	@Test
	public void updateTsscGame() {
		configure();
		when(tsscGameDelegate.updateTsscGame(any(TsscGame.class))).thenAnswer(i -> {
			TsscGame tmp = i.getArgumentAt(0, TsscGame.class);
			int index = -1;
			for (int j=0; j<list.size(); j++) {
				TsscGame tc = list.get(j);
				if (tc.getId()==tmp.getId()) {
					index = j;
					break;
				}
			}
		    list.remove(index);
		    list.add(tmp);
		    return tmp;
		});
		String newName = "NewDescription"+(new Random().nextInt());
		TsscGame newGame = new TsscGame();
		newGame.setId(gameTest.getId());
		newGame.setName(newName);
		TsscGame returned = tsscGameDelegate.updateTsscGame(newGame);
		assertEquals(newName,returned.getName());
	}
	
	@Test
	public void deleteTsscGame() {
		configure();
		doAnswer(
				i -> {
					Long id = i.getArgumentAt(0, Long.class);
					int index = -1;
					for (int j=0; j<list.size(); j++) {
						TsscGame tc = list.get(j);
						if (tc.getId()==id) {
							index = j;
							break;
						}
					}
					list.remove(index);
					return null;
				}
				).when(tsscGameDelegate).deleteTsscGame(any(Long.class));
		long id = list.get(0).getId();
		tsscGameDelegate.deleteTsscGame(id);
		assertNull(tsscGameDelegate.findById(id));
	}
	
}
