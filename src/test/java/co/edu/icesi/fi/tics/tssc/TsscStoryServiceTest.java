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

	public static final BigDecimal uno = BigDecimal.ONE, cero = BigDecimal.ZERO;
	private TsscStory story;
	private TsscGame game;
	private TsscTopic topic;

	@Autowired
	public TsscStoryServiceTest(TsscStoryService tsscStoryService, TsscGameService tsscGameService,
			TsscTopicService tsscTopicService) {
		this.tsscStoryService = tsscStoryService;
		this.tsscGameService = tsscGameService;
		this.tsscTopicService = tsscTopicService;
	}

	private void setup() {
		story = new TsscStory();
		story.setBusinessValue(uno);
		story.setInitialSprint(uno);
		story.setPriority(uno);
		game = generateRandomGame();
		topic = generateRandomTopic();
	}

	private TsscGame generateRandomGame() {
		TsscGame generated = new TsscGame();
		int l1 = new Random().nextInt(), l2 = new Random().nextInt();
		generated.setNGroups(l1 == 0 ? 1 : l1 >= 1 ? l1 : -l1);
		generated.setNSprints(l2 == 0 ? 1 : l2 >= 1 ? l2 : -l2);
		generated.setName("MyName" + Math.random());
		generated.setUserPassword("123456");
		return generated;
	}

	private TsscTopic generateRandomTopic() {
		TsscTopic generated = new TsscTopic();
		long l1 = new Random().nextLong(), l2 = new Random().nextLong();
		generated.setDefaultGroups(l1 == 0 ? 1 : l1 >= 1 ? l1 : -l1); // If try to save with number=0 an Exception it's
																		// thrown
		generated.setDefaultSprints(l2 == 0 ? 1 : l2 >= 1 ? l2 : -l2);
		generated.setDescription("My description#" + Math.random());
		generated.setGroupPrefix("Prefix#" + Math.random());
		generated.setName("Test#" + Math.random());
		generated.setTsscStories(null);
		return generated;
	}

	// Caso 15
	@Test
	void testSaveStoryCase15() {
		setup();
		tsscTopicService.saveTopic(topic);
		game.setTsscTopic(topic);
		tsscGameService.saveGame(game, tsscTopicService.findAll());
		story.setTsscGame(game);
		TsscStory newStory = tsscStoryService.saveStory(story, tsscGameService.findAll());
		assertTrue(story.getId() == newStory.getId());
	}

	// Caso 16
	@Test
	void testSaveStoryCase16() {
		setup();
		story.setBusinessValue(cero);
		tsscTopicService.saveTopic(topic);
		game.setTsscTopic(topic);
		tsscGameService.saveGame(game, tsscTopicService.findAll());
		story.setTsscGame(game);
		try {
			TsscStory newStory = tsscStoryService.saveStory(story, tsscGameService.findAll());
			assertTrue(story.getId() == newStory.getId());
			assertTrue(false);
		} catch (RuntimeException e) {
			System.out.println("Case 16: "+e);
			assertTrue(true);
		}
	}

	// Caso 17
	@Test
	void testSaveStoryCase17() {
		setup();
		story.setInitialSprint(cero);
		tsscTopicService.saveTopic(topic);
		game.setTsscTopic(topic);
		tsscGameService.saveGame(game, tsscTopicService.findAll());
		story.setTsscGame(game);
		try {
			TsscStory newStory = tsscStoryService.saveStory(story, tsscGameService.findAll());
			assertTrue(story.getId() == newStory.getId());
			assertTrue(false);
		} catch (RuntimeException e) {
			System.out.println("Case 17: "+e);
			assertTrue(true);
		}
	}

	// Caso 18
	@Test
	void testSaveStoryCase18() {
		setup();
		story.setPriority(cero);
		tsscTopicService.saveTopic(topic);
		game.setTsscTopic(topic);
		tsscGameService.saveGame(game, tsscTopicService.findAll());
		story.setTsscGame(game);
		try {
			TsscStory newStory = tsscStoryService.saveStory(story, tsscGameService.findAll());
			assertTrue(story.getId() == newStory.getId());
			assertTrue(false);
		} catch (RuntimeException e) {
			System.out.println("Case 18: "+e);
			assertTrue(true);
		}
	}

	// Caso 19
	@Test
	void testSaveStoryCase19() {
		setup();
		tsscTopicService.saveTopic(topic);
		game.setTsscTopic(topic);
//		tsscGameService.saveGame(game, tsscTopicService.findAll());
		story.setTsscGame(game);
		try {
			TsscStory newStory = tsscStoryService.saveStory(story, tsscGameService.findAll());
			assertTrue(story.getId() == newStory.getId());
			assertTrue(false);
		} catch (RuntimeException e) {
			System.out.println("Case 19: "+e);
			assertTrue(true);
		}
	}

	// Caso 20
	@Test
	void testEditStoryCase20() {
		setup();
		story.setBusinessValue(uno);
		story.setPriority(uno);
		story.setInitialSprint(uno);
		String oldDesc = "This is my first description #" + Math.random(), newDesc = "NewDesc#" + Math.random();
		story.setDescription(oldDesc);
		tsscTopicService.saveTopic(topic);
		game.setTsscTopic(topic);
		tsscGameService.saveGame(game, tsscTopicService.findAll());
		story.setTsscGame(game);
		TsscStory newStory = tsscStoryService.saveStory(story, tsscGameService.findAll());
		newStory.setDescription(newDesc);
		TsscStory newStory2 = tsscStoryService.editStory(newStory);
		assertEquals(newDesc, newStory2.getDescription());
		assertTrue("The id of the modified story doesn't match with first old one", story.getId() == newStory2.getId());
	}

	// Caso 21
	@Test
	void testEditStoryCase21() {
		setup();
		story.setBusinessValue(cero);
		story.setPriority(uno);
		story.setInitialSprint(uno);
		String oldDesc = "This is my first description #" + Math.random(), newDesc = "NewDesc#" + Math.random();
		story.setDescription(oldDesc);
		tsscTopicService.saveTopic(topic);
		game.setTsscTopic(topic);
		tsscGameService.saveGame(game, tsscTopicService.findAll());
		story.setTsscGame(game);
		try {			
			TsscStory newStory = tsscStoryService.saveStory(story, tsscGameService.findAll());
			newStory.setDescription(newDesc);
			TsscStory newStory2 = tsscStoryService.editStory(newStory);
			assertEquals(newDesc, newStory2.getDescription());
			assertTrue("The id of the modified story doesn't match with first old one", story.getId() == newStory2.getId());
			assertTrue(false);
		} catch (Exception e) {
			System.out.println("Case 21: "+e);
			assertTrue(true);
		}
	}

	// Caso 22
	@Test
	void testEditStoryCase22() {
		setup();
		story.setBusinessValue(uno);
		story.setPriority(uno);
		story.setInitialSprint(cero);
		String oldDesc = "This is my first description #" + Math.random(), newDesc = "NewDesc#" + Math.random();
		story.setDescription(oldDesc);
		tsscTopicService.saveTopic(topic);
		game.setTsscTopic(topic);
		tsscGameService.saveGame(game, tsscTopicService.findAll());
		story.setTsscGame(game);
		try {			
			TsscStory newStory = tsscStoryService.saveStory(story, tsscGameService.findAll());
			newStory.setDescription(newDesc);
			TsscStory newStory2 = tsscStoryService.editStory(newStory);
			assertEquals(newDesc, newStory2.getDescription());
			assertTrue("The id of the modified story doesn't match with first old one", story.getId() == newStory2.getId());
			assertTrue(false);
		} catch (Exception e) {
			System.out.println("Case 22: "+e);
			assertTrue(true);
		}
	}

	// Caso 23
	@Test
	void testEditStoryCase23() {
		setup();
		story.setBusinessValue(uno);
		story.setPriority(cero);
		story.setInitialSprint(uno);
		String oldDesc = "This is my first description #" + Math.random(), newDesc = "NewDesc#" + Math.random();
		story.setDescription(oldDesc);
		tsscTopicService.saveTopic(topic);
		game.setTsscTopic(topic);
		tsscGameService.saveGame(game, tsscTopicService.findAll());
		story.setTsscGame(game);
		try {			
			TsscStory newStory = tsscStoryService.saveStory(story, tsscGameService.findAll());
			newStory.setDescription(newDesc);
			TsscStory newStory2 = tsscStoryService.editStory(newStory);
			assertEquals(newDesc, newStory2.getDescription());
			assertTrue("The id of the modified story doesn't match with first old one", story.getId() == newStory2.getId());
			assertTrue(false);
		} catch (Exception e) {
			System.out.println("Case 23: "+e);
			assertTrue(true);
		}
	}

	// Caso 24
	@Test
	void testEditStoryCase24() {
		setup();
		story.setBusinessValue(uno);
		story.setPriority(uno);
		story.setInitialSprint(uno);
		String oldDesc = "This is my first description #" + Math.random(), newDesc = "NewDesc#" + Math.random();
		story.setDescription(oldDesc);
		tsscTopicService.saveTopic(topic);
		game.setTsscTopic(topic);
//		tsscGameService.saveGame(game, tsscTopicService.findAll());
		story.setTsscGame(game);
		try {			
			TsscStory newStory = tsscStoryService.saveStory(story, tsscGameService.findAll());
			newStory.setDescription(newDesc);
			TsscStory newStory2 = tsscStoryService.editStory(newStory);
			assertEquals(newDesc, newStory2.getDescription());
			assertTrue("The id of the modified story doesn't match with first old one", story.getId() == newStory2.getId());
			assertTrue(false);
		} catch (Exception e) {
			System.out.println("Case 24: "+e);
			assertTrue(true);
		}
	}

}
