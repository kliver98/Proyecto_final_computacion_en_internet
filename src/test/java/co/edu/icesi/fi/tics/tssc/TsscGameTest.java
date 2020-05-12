package co.edu.icesi.fi.tics.tssc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.model.TsscStory;
import co.edu.icesi.fi.tics.tssc.model.TsscTopic;
import co.edu.icesi.fi.tics.tssc.service.TsscGameService;
import co.edu.icesi.fi.tics.tssc.service.TsscTopicService;

@SpringBootTest
class TsscGameTest {

	private TsscGameService tsscGameService;
	private TsscTopicService tsscTopicService;

	public static final Integer uno = 1, cero = 0;
	private TsscTopic topic;
	private TsscGame game;

	@Autowired
	public TsscGameTest(TsscGameService tsscGameService, TsscTopicService tsscTopicService) {
		this.tsscGameService = tsscGameService;
		this.tsscTopicService = tsscTopicService;
	}

	private void setup() {
		game = new TsscGame();
		topic = generateRandomTopic();
	}

	private List<TsscStory> generateStories(int n) {
		List<TsscStory> stories = new ArrayList<TsscStory>();
		for (int i = 0; i < n; i++) {
			TsscStory tmp = new TsscStory();
			tmp.setId(i + 1);
			tmp.setDescription("#" + (i + 1));
			stories.add(tmp);
		}
		return stories;
	}

	private TsscTopic generateRandomTopic() {
		TsscTopic generated = new TsscTopic();
//		long l1 = new Random().nextLong(), l2 = new Random().nextLong();
//		generated.setDefaultGroups(l1 == 0 ? 1 : l1 >= 1 ? l1 : -l1); // If try to save with number=0 an Exception it's thrown
//		generated.setDefaultSprints(l2 == 0 ? 1 : l2 >= 1 ? l2 : -l2);
		generated.setDefaultGroups(uno); // If try to save with number=0 an Exception it's thrown
		generated.setDefaultSprints(uno);
		generated.setDescription("My description#" + Math.random());
		generated.setGroupPrefix("Prefix#" + Math.random());
		generated.setName("Test#" + Math.random());
		generated.setTsscStories(null);
		return generated;
	}

	// Caso 7
	@Test
	void testSave2Case7() {
		setup();
		tsscTopicService.save(topic);
		game.setTsscTopic(topic);
		TsscGame retrieved = tsscGameService.save(game, tsscTopicService.findAll());
		assertEquals(game.getId(), retrieved.getId());
	}

	// Caso 8
	@Test
	void testSave2Case8() {
		setup();
		try {
			game.setTsscTopic(topic);
			TsscGame retrieved = tsscGameService.save2(game, tsscTopicService.findAll());
			assertEquals(game.getId(), retrieved.getId());
			assertTrue(false);
		} catch (RuntimeException e) {
			assertTrue(true);
		}
	}

	// Caso 9
	@Test
	void testSave2Case9() {
		setup();
		game.setNSprints(cero);
		tsscTopicService.save(topic);
		try {
			game.setTsscTopic(topic);
			TsscGame retrieved = tsscGameService.save2(game, tsscTopicService.findAll());
			assertEquals(game.getId(), retrieved.getId());
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	// Caso 10
	@Test
	void testSave2Case10() {
		setup();
		game.setNGroups(cero);
		tsscTopicService.save(topic);
		try {
			game.setTsscTopic(topic);
			TsscGame retrieved = tsscGameService.save2(game, tsscTopicService.findAll());
			assertEquals(game.getId(), retrieved.getId());
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	void testSave2() {
		setup();
		tsscTopicService.save(topic);
		game.setTsscTopic(topic);
		List<TsscStory> st1 = generateStories(5);
		topic.setTsscStories(st1);
		TsscGame retrieved = tsscGameService.save2(game, tsscTopicService.findAll());
		List<TsscStory> st2 = retrieved.getTsscStories();
		for (int i = 0; i < st1.size(); i++) {
			TsscStory s1 = st1.get(i);
			if (s1 == st2.get(i))
				assertFalse(true);
		}
		assertTrue(true);
	}

	// Caso 11
	@Test
	void testUpdateCase11() {
		setup();
		tsscTopicService.save(topic);
		game.setTsscTopic(topic);
		game.setNGroups(uno);
		game.setNSprints(uno);
		String oldName = "MyName#" + Math.random();
		game.setName(oldName);
		TsscGame retrieved = tsscGameService.save(game, tsscTopicService.findAll());
		String newName = "NewName#" + Math.random();
		retrieved.setName(newName);
		TsscGame newRetrieved = tsscGameService.update(retrieved);
		assertFalse("The name wasn't change", oldName.equals(newRetrieved.getName()));
		assertTrue("The new name its not ok", newName.equals(newRetrieved.getName()));
	}

	// Caso 12
	@Test
	void testUpdateCase12() {
		setup();
//		tsscTopicService.save(topic);
		game.setTsscTopic(topic);
		game.setNGroups(uno);
		game.setNSprints(uno);
		String oldName = "MyName#" + Math.random();
		game.setName(oldName);
		try {			
			TsscGame retrieved = tsscGameService.save2(game, tsscTopicService.findAll());
			String newName = "NewName#" + Math.random();
			retrieved.setName(newName);
			TsscGame newRetrieved = tsscGameService.update(retrieved);
			assertFalse("The name wasn't change", oldName.equals(newRetrieved.getName()));
			assertTrue("The new name its not ok", newName.equals(newRetrieved.getName()));
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	// Caso 13
	@Test
	void testUpdateCase13() {
		setup();
		tsscTopicService.save(topic);
		game.setTsscTopic(topic);
		game.setNGroups(uno);
		game.setNSprints(cero);
		String oldName = "MyName#" + Math.random();
		game.setName(oldName);
		try {			
			TsscGame retrieved = tsscGameService.save2(game, tsscTopicService.findAll());
			String newName = "NewName#" + Math.random();
			retrieved.setName(newName);
			TsscGame newRetrieved = tsscGameService.update(retrieved);
			assertFalse("The name wasn't change", oldName.equals(newRetrieved.getName()));
			assertTrue("The new name its not ok", newName.equals(newRetrieved.getName()));
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	// Caso 14
	@Test
	void testUpdateCase14() {
		setup();
//		tsscTopicService.save(topic);
		game.setTsscTopic(topic);
		game.setNGroups(cero);
		game.setNSprints(uno);
		String oldName = "MyName#" + Math.random();
		game.setName(oldName);
		try {			
			TsscGame retrieved = tsscGameService.save2(game, tsscTopicService.findAll());
			String newName = "NewName#" + Math.random();
			retrieved.setName(newName);
			TsscGame newRetrieved = tsscGameService.update(retrieved);
			assertFalse("The name wasn't change", oldName.equals(newRetrieved.getName()));
			assertTrue("The new name its not ok", newName.equals(newRetrieved.getName()));
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(true);
		}
	}

}
