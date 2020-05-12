package co.edu.icesi.fi.tics.tssc;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import co.edu.icesi.fi.tics.tssc.model.TsscTopic;
import co.edu.icesi.fi.tics.tssc.service.TsscTopicService;

@SpringBootTest
class TsscTopicTest {

	@Autowired
	private TsscTopicService tsscTopicService;
	
	public static final long uno = 1,cero = 0;
	private TsscTopic test, retrieved;
	
	private void setup() {
		test = generateRandomTopic();
		retrieved = generateRandomTopic();
	}
	
	private TsscTopic generateRandomTopic() {
		TsscTopic generated = new TsscTopic();
//		long l1 = new Random().nextLong(), l2 = new Random().nextLong();
//		generated.setDefaultGroups(l1==0 ? 1:l1>=1 ? l1:-l1); // If try to save with number=0 an Exception it's thrown
//		generated.setDefaultSprints(l2==0 ? 1:l2>=1 ? l2:-l2);
		generated.setDefaultGroups(uno);
		generated.setDefaultSprints(uno);
		generated.setDescription("My description#"+Math.random());
		generated.setGroupPrefix("Prefix#"+Math.random());
		generated.setName("Test#"+Math.random());
		generated.setTsscStories(null);
		return generated;
	}
	
	//Caso 1
	@Test
	void testsaveCase1() {
		setup();
		retrieved = tsscTopicService.save(test);
		assertSame("Tema NO guardado",test.getName(),retrieved.getName());
	}
	
	//Caso 2
	@Test
	void testsaveCase2() {
		setup();
		test.setDefaultGroups(uno);
		test.setDefaultSprints(cero);
		try {			
			retrieved = tsscTopicService.save(test);
			assertTrue("Error en cantidad mínima",false);
		} catch(RuntimeException e) {
			assertTrue(true);
		}
	}
	
	//Caso 3
	@Test
	void testsaveCase3() {
		setup();
		test = generateRandomTopic();
		test.setDefaultGroups(cero);
		try {			
			retrieved = tsscTopicService.save(test);
			assertTrue("Error en cantidad mínima",false);
		} catch(RuntimeException e) {
			assertTrue(true);
		}
	}
	
	//Caso 4
	@Test
	void testEditTopicCase4() {
		setup();
		retrieved = tsscTopicService.save(test);
		String newName = "New name"+Math.random();
		retrieved.setName(newName);
		TsscTopic retrieved2 = tsscTopicService.update(retrieved);
		assertTrue( "Id's are not equals",(retrieved.getId()==retrieved2.getId()) );
		assertTrue( "The name of topic wasn't update",retrieved.getName().equals(newName));
	}
	
	//Caso 5
	@Test
	void testEditTopicCase5() {
		setup();
		test.setDefaultSprints(cero);
		try {
			tsscTopicService.save(test);
			assertTrue("Error en cantidad mínima",false);
		} catch (RuntimeException e) {
			assertTrue(true);
		}
	}
	
	//Caso 6
	@Test
	void testEditTopicCase6() {
		setup();
		test.setDefaultGroups(cero);
		try {
			tsscTopicService.save(test);
			assertTrue("Error en cantidad mínima",false);
		} catch (RuntimeException e) {
			assertTrue(true);
		}
	}
	
}
