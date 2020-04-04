package co.edu.icesi.fi.tics.tssc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.model.TsscStory;
import co.edu.icesi.fi.tics.tssc.model.TsscTopic;
import co.edu.icesi.fi.tics.tssc.service.TsscGameService;
import co.edu.icesi.fi.tics.tssc.service.TsscStoryService;
import co.edu.icesi.fi.tics.tssc.service.TsscTopicService;

@SpringBootTest
class TsscStoryServiceTest {

	private TsscStoryService tsscStoryService;
	private TsscGameService tsscGameService;
	private TsscTopicService tsscTopicService;
	
	@Autowired
	public TsscStoryServiceTest(TsscStoryService tsscStoryService,TsscGameService tsscGameService,TsscTopicService tsscTopicService) {
		this.tsscStoryService = tsscStoryService;
		this.tsscGameService = tsscGameService;
		this.tsscTopicService = tsscTopicService;
	}
	
	private TsscGame generateRandomGame() {
		TsscGame generated = new TsscGame();
		int l1 = new Random().nextInt(), l2 = new Random().nextInt();
		generated.setNGroups(l1==0 ? 1:l1>=1 ? l1:-l1);
		generated.setNSprints(l2==0 ? 1:l2>=1 ? l2:-l2);
		generated.setName("MyName"+Math.random());
		generated.setUserPassword("123456");
		return generated;
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
	void testSaveStory() {
		TsscStory story = new TsscStory();
		TsscGame game = generateRandomGame();
		TsscTopic topic = generateRandomTopic();
		tsscTopicService.saveTopic(topic);
		game.setTsscTopic(topic);
		tsscGameService.saveGame(game, tsscTopicService.findAll());
		story.setTsscGame(game);
		TsscStory newStory = tsscStoryService.saveStory(story, tsscGameService.findAll());
		assertTrue(story.getId()==newStory.getId());
	}
	
	@Test
	void testEditStory() {
		TsscStory story = new TsscStory();
		story.setBusinessValue(BigDecimal.TEN);
		story.setPriority(BigDecimal.ONE);
		story.setInitialSprint(BigDecimal.ONE);
		String oldDesc = "This is my first description #"+Math.random(), newDesc = "NewDesc#"+Math.random();
		story.setDescription(oldDesc);
		TsscGame game = generateRandomGame();
		TsscTopic topic = generateRandomTopic();
		tsscTopicService.saveTopic(topic);
		game.setTsscTopic(topic);
		tsscGameService.saveGame(game, tsscTopicService.findAll());
		story.setTsscGame(game);
		TsscStory newStory = tsscStoryService.saveStory(story, tsscGameService.findAll());
		newStory.setDescription(newDesc);
		TsscStory newStory2 = tsscStoryService.editStory(newStory);
		assertEquals(newDesc,newStory2.getDescription());
		assertTrue("The id of the modified story doesn't match with first old one",story.getId()==newStory2.getId());
	}

}
