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

import co.edu.icesi.fi.tics.tssc.model.TsscTopic;


@SpringBootTest
public class TsscTopicDelegateTest {

	private TsscTopicDelegate tsscTopicDelegate;
	private List<TsscTopic> list;
	private TsscTopic topicTest;
	
	public void configure() {
		tsscTopicDelegate = mock(TsscTopicDelegate.class);
		list = new ArrayList<TsscTopic>();
		topicTest = new TsscTopic();
		topicTest.setId(1);
		topicTest.setDefaultGroups(4);
		topicTest.setDefaultSprints(4);
		topicTest.setName("One");
		topicTest.setDescription("Description of one");
		list.add(topicTest);
		
		//This method is supposing it's fine, so... any error, checkout this method first
		when(tsscTopicDelegate.findById(any(Long.class))).thenAnswer(i -> {
		    Long id = i.getArgumentAt(0, Long.class);
		    for (TsscTopic tc : list) {
				if (tc.getId()==id)
					return tc;
			}
		    return null;
		});
	}
	
	@Test
	public void getAll() {
		configure();
		when(tsscTopicDelegate.getAll()).thenReturn(list);
		assertEquals(StreamSupport.stream(tsscTopicDelegate.getAll().spliterator(), false).count(),list.size());
	}
	
	@Test
	public void findById() {
		configure();
		when(tsscTopicDelegate.findById(list.get(0).getId())).thenReturn(list.get(0));
		assertTrue(tsscTopicDelegate.findById(list.get(0).getId()).getName().equals(list.get(0).getName()));
		assertNull("The index is minus one of the first, it shouldn't find it",tsscTopicDelegate.findById(list.get(0).getId()-1));
	}
	
	@Test
	public void saveTsscTopic() {
		configure();
		TsscTopic topic = new TsscTopic();
		topic.setDefaultGroups(4);topic.setDefaultSprints(4);topic.setDescription("MyDescription");topic.setGroupPrefix("Prefix");topic.setId(5);
		topic.setName("Second");
		when(tsscTopicDelegate.saveTsscTopic(any(TsscTopic.class))).thenAnswer(i -> {
			TsscTopic tmp = i.getArgumentAt(0, TsscTopic.class);
		    list.add(tmp);
		    return tmp;
		});
		TsscTopic returned = tsscTopicDelegate.saveTsscTopic(topic);
		assertTrue(returned.getId()==tsscTopicDelegate.findById(returned.getId()).getId());
		assertTrue(returned.getName().equals(tsscTopicDelegate.findById(returned.getId()).getName()));
	}
	
	@Test
	public void updateTsscTopic() {
		configure();
		when(tsscTopicDelegate.updateTsscTopic(any(TsscTopic.class))).thenAnswer(i -> {
			TsscTopic tmp = i.getArgumentAt(0, TsscTopic.class);
			int index = -1;
			for (int j=0; j<list.size(); j++) {
				TsscTopic tc = list.get(j);
				if (tc.getId()==tmp.getId()) {
					index = j;
					break;
				}
			}
		    list.remove(index);
		    list.add(tmp);
		    return tmp;
		});
		String newName = "NewName"+(new Random().nextInt());
		TsscTopic newTopic = new TsscTopic();
		newTopic.setId(topicTest.getId());
		newTopic.setName(newName);
		TsscTopic returned = tsscTopicDelegate.updateTsscTopic(newTopic);
		assertEquals(newName,returned.getName());
	}

	@Test
	public void deleteTsscTopic() {
		configure();
		doAnswer(
				i -> {
					Long id = i.getArgumentAt(0, Long.class);
					int index = -1;
					for (int j=0; j<list.size(); j++) {
						TsscTopic tc = list.get(j);
						if (tc.getId()==id) {
							index = j;
							break;
						}
					}
					list.remove(index);
					return null;
				}
				).when(tsscTopicDelegate).deleteTsscTopic(any(Long.class));
		long id = list.get(0).getId();
		tsscTopicDelegate.deleteTsscTopic(id);
		assertNull(tsscTopicDelegate.findById(id));
	}
	
}
