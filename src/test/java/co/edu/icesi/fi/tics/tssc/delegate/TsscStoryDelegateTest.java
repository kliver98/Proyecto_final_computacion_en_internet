package co.edu.icesi.fi.tics.tssc.delegate;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.StreamSupport;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import co.edu.icesi.fi.tics.tssc.model.TsscStory;

@SpringBootTest
public class TsscStoryDelegateTest {

	private TsscStoryDelegate tsscStoryDelegate;
	private List<TsscStory> list;
	private TsscStory storyTest;
	
	public void configure() {
		tsscStoryDelegate = mock(TsscStoryDelegate.class);
		list = new ArrayList<TsscStory>();
		storyTest = new TsscStory();
		storyTest.setId(1);
		storyTest.setPriority(BigDecimal.ONE);
		storyTest.setDescription("Description of one"+(new Random().nextDouble()));
		list.add(storyTest);
		
		//This method is supposing it's fine, so... any error, checkout this method first
		when(tsscStoryDelegate.findById(any(Long.class))).thenAnswer(i -> {
		    Long id = i.getArgumentAt(0, Long.class);
		    for (TsscStory tc : list) {
				if (tc.getId()==id)
					return tc;
			}
		    return null;
		});
	}
	
	@Test
	public void getAll() {
		configure();
		when(tsscStoryDelegate.getAll()).thenReturn(list);
		assertEquals(StreamSupport.stream(tsscStoryDelegate.getAll().spliterator(), false).count(),list.size());
	}
	
	@Test
	public void findById() {
		configure();
		when(tsscStoryDelegate.findById(list.get(0).getId())).thenReturn(list.get(0));
		assertTrue(tsscStoryDelegate.findById(list.get(0).getId()).getDescription().equals(list.get(0).getDescription()));
		assertNull("The index is minus one of the first, it shouldn't find it",tsscStoryDelegate.findById(list.get(0).getId()-1));
	}
	
	@Test
	public void saveTsscStory() {
		configure();
		TsscStory story = new TsscStory();
		story.setId(2);story.setDescription("NewDescription"+(new Random().nextInt()));
		when(tsscStoryDelegate.saveTsscStory(any(TsscStory.class))).thenAnswer(i -> {
			TsscStory tmp = i.getArgumentAt(0, TsscStory.class);
		    list.add(tmp);
		    return tmp;
		});
		TsscStory returned = tsscStoryDelegate.saveTsscStory(story);
		assertTrue(returned.getId()==tsscStoryDelegate.findById(returned.getId()).getId());
		assertTrue(returned.getDescription().equals(tsscStoryDelegate.findById(returned.getId()).getDescription()));
	}
	
	@Test
	public void updateTsscStory() {
		configure();
		when(tsscStoryDelegate.updateTsscStory(any(TsscStory.class))).thenAnswer(i -> {
			TsscStory tmp = i.getArgumentAt(0, TsscStory.class);
			int index = -1;
			for (int j=0; j<list.size(); j++) {
				TsscStory tc = list.get(j);
				if (tc.getId()==tmp.getId()) {
					index = j;
					break;
				}
			}
		    list.remove(index);
		    list.add(tmp);
		    return tmp;
		});
		String newDescription = "NewDescription"+(new Random().nextInt());
		TsscStory newStory = new TsscStory();
		newStory.setId(storyTest.getId());
		newStory.setDescription(newDescription);
		TsscStory returned = tsscStoryDelegate.updateTsscStory(newStory);
		assertEquals(newDescription,returned.getDescription());
	}

	@Test
	public void deleteTsscStory() {
		configure();
		doAnswer(
				i -> {
					Long id = i.getArgumentAt(0, Long.class);
					int index = -1;
					for (int j=0; j<list.size(); j++) {
						TsscStory tc = list.get(j);
						if (tc.getId()==id) {
							index = j;
							break;
						}
					}
					list.remove(index);
					return null;
				}
				).when(tsscStoryDelegate).deleteTsscStory(any(Long.class));
		long id = list.get(0).getId();
		tsscStoryDelegate.deleteTsscStory(id);
		assertNull(tsscStoryDelegate.findById(id));
	}
	
}
