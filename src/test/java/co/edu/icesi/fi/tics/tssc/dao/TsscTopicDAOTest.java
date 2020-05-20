package co.edu.icesi.fi.tics.tssc.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.fi.tics.tssc.Taller1KliverApplication;
import co.edu.icesi.fi.tics.tssc.model.TsscTopic;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Taller1KliverApplication.class)
@Rollback(false)
public class TsscTopicDAOTest {
	
	@Autowired
	private TsscTopicDAO tsscTopicDAO;
	private TsscTopic tsscTopic1,tsscTopic2;
	
	@BeforeEach
	public void setUp() {
		tsscTopic1 = new TsscTopic();
		tsscTopic1.setName("Topic 1");
		tsscTopic1.setDescription("First topic");	
		tsscTopic2 = new TsscTopic();
		tsscTopic2.setName("Topic 2");
		tsscTopic2.setDefaultGroups(4);
		tsscTopic2.setDescription("Second topic");
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testSaveTopic() {
		int size = tsscTopicDAO.findAll().size();
		tsscTopicDAO.save(tsscTopic1);
		tsscTopicDAO.save(tsscTopic2);
		assertEquals(size+2,tsscTopicDAO.findAll().size());
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testUpdateTopic() {
		tsscTopicDAO.save(tsscTopic1);
		TsscTopic t = tsscTopicDAO.findAll().get(0);
		String name = "Topic Updated";
		t.setName(name);
		tsscTopicDAO.update(t);
		assertEquals(name, tsscTopicDAO.findAll().get(0).getName());
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testDeleteTopic() {
		tsscTopicDAO.save(tsscTopic1);
		List<TsscTopic> topics = tsscTopicDAO.findAll();
		int size = topics.size();
		tsscTopicDAO.delete(tsscTopic1);
		assertEquals(size-1,tsscTopicDAO.findAll().size());
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindById() {
		assertEquals(null, tsscTopicDAO.findById(1000));
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByName() {
		tsscTopicDAO.save(tsscTopic2);
		assertEquals("Topic 2", tsscTopicDAO.findByName("Topic 2").get(0).getName());
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByDescription() {
		String description = "Second topic";
		assertEquals(description, tsscTopicDAO.findByDescription(description).get(0).getDescription());
	}
	
	
}

