package co.edu.icesi.fi.tics.tssc;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import co.edu.icesi.fi.tics.tssc.model.TsscTopic;
import co.edu.icesi.fi.tics.tssc.service.TsscTopicService;

@SpringBootTest
class TsscTopicServiceTest {

	@Autowired
	private TsscTopicService tsscTopicService;
	
	private TsscTopic generateRandomTopic() {
		TsscTopic generated = new TsscTopic();
		long l1 = new Random().nextLong(), l2 = new Random().nextLong();
		generated.setDefaultGroups(l1==0 ? 1:l1>=1 ? l1:-l1); // If try to save with number=0 an Exception it's thrown
		generated.setDefaultSprints(l2==0 ? 1:l2>=1 ? l2:-l2);
		generated.setDescription("My description#"+Math.random());
		generated.setGroupPrefix("Prefix#"+Math.random());
		generated.setName("Test#"+Math.random());
		generated.setTsscStories(null);
		return generated;
	}
	
	@Test
	void testSaveTopic() {
		TsscTopic test = generateRandomTopic();
		TsscTopic retrieved = tsscTopicService.saveTopic(test);
		assertSame(test.getName(),retrieved.getName());
	}

	@Test
	void testEditTopic() {
		TsscTopic test = generateRandomTopic();
		TsscTopic retrieved = tsscTopicService.saveTopic(test);
		String newName = "New name"+Math.random();
		retrieved.setName(newName);
		TsscTopic retrieved2 = tsscTopicService.editTopic(retrieved);
		assertTrue( "Id's are not equals",(retrieved.getId()==retrieved2.getId()) );
		assertFalse( "The name of topic wasn't update",test.getName().equals(retrieved2.getName()) 
				&& (retrieved2.getName().equals(newName)) );
	}
	
}
