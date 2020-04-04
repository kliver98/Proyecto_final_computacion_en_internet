package co.edu.icesi.fi.tics.tssc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.model.TsscStory;
import co.edu.icesi.fi.tics.tssc.model.TsscTopic;
import co.edu.icesi.fi.tics.tssc.service.TsscGameService;
import co.edu.icesi.fi.tics.tssc.service.TsscTopicService;

@SpringBootTest
class TsscGameServiceTest {

	private TsscGameService tsscGameService;
	private TsscTopicService tsscTopicService;
	
	@Autowired
	public TsscGameServiceTest(TsscGameService tsscGameService,TsscTopicService tsscTopicService) {
		this.tsscGameService = tsscGameService;
		this.tsscTopicService = tsscTopicService;
	}
	
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
	void testSaveGame() {
		TsscGame game = new TsscGame();
		TsscTopic topic = generateRandomTopic();
		tsscTopicService.saveTopic(topic);
		game.setTsscTopic(topic);
		TsscGame retrieved = tsscGameService.saveGame(game, tsscTopicService.findAll());
		assertEquals(game.getId(),retrieved.getId());
	}
	
	@Test
	void testSaveGame2() {
		TsscGame game = new TsscGame();
		TsscTopic topic = generateRandomTopic();
		tsscTopicService.saveTopic(topic);
		game.setTsscTopic(topic);
		List<TsscStory> st1 = generateStories(5);
		topic.setTsscStories(st1);
		TsscGame retrieved = tsscGameService.saveGame2(game, tsscTopicService.findAll());
		List<TsscStory> st2 = retrieved.getTsscStories();
		for (int i = 0; i < st1.size(); i++) {
			TsscStory s1 = st1.get(i);
			if (s1==st2.get(i))
				assertFalse(true);
		}
		assertTrue(true);
	}
	
	@Test
	void testEditGame() {
		TsscGame game = new TsscGame();
		TsscTopic topic = generateRandomTopic();
		tsscTopicService.saveTopic(topic);
		game.setTsscTopic(topic);
		game.setNGroups(1);
		game.setNSprints(1);
		String oldName = "MyName#"+Math.random();
		game.setName(oldName);
		TsscGame retrieved = tsscGameService.saveGame(game, tsscTopicService.findAll());
		String newName = "NewName#"+Math.random();
		retrieved.setName(newName);
		TsscGame newRetrieved = tsscGameService.editGame(retrieved);
		assertFalse("The name wasn't change",oldName.equals(newRetrieved.getName()));
		assertTrue("The new name its not ok",newName.equals(newRetrieved.getName()));
	}
	
	private List<TsscStory> generateStories(int n) {
		List<TsscStory> stories = new ArrayList<TsscStory>();
		for (int i = 0; i < n; i++) {
			TsscStory tmp = new TsscStory();
			tmp.setId(i+1);
			tmp.setDescription("#"+(i+1));
			stories.add(tmp);
		}
		return stories;
	}

}
