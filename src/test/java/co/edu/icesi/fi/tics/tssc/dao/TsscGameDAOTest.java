package co.edu.icesi.fi.tics.tssc.dao;


import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.fi.tics.tssc.Taller1KliverApplication;
import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.model.TsscTopic;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Taller1KliverApplication.class)
@Rollback(false)
public class TsscGameDAOTest {
	
	@Autowired
	private TsscGameDAO tsscGameDAO;
	@Autowired
	private TsscTopicDAO tsscTopicDAO;
	
	private TsscGame g1, g2;
	private TsscTopic t1, t2;
	
	public void setUp() {
		LocalDate d1 = LocalDate.of(2020, 10, 20);
		LocalDate d2 = LocalDate.of(2020, 12, 3);
		t1 = new TsscTopic(); 
		t1.setName("Topic 1");
		t2 = new TsscTopic();
		t2.setName("Topic 2");
		g1 = new TsscGame();
		g1.setTsscTopic(t1);
		g1.setName("Game 1");
		g2 = new TsscGame(); 
		g2.setTsscTopic(t1);
		g2.setName("Game 2");
		g1.setScheduledDate(d1);
		g1.setScheduledTime(LocalTime.of(10, 20));
		g2.setScheduledDate(d2);
		g2.setScheduledTime(LocalTime.of(11, 20));
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testSave() {
		setUp();
		tsscTopicDAO.save(t1);
		tsscTopicDAO.save(t2);
		
		int size = tsscGameDAO.findAll().size();
		tsscGameDAO.save(g1);
		tsscGameDAO.save(g2);

		assertEquals(size+2,tsscGameDAO.findAll().size());
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testUpdate() {
		TsscGame g = tsscGameDAO.findAll().get(0);
		String name = "New game";
		g.setName(name);
		tsscGameDAO.update(g);
		assertEquals(name, tsscGameDAO.findAll().get(0).getName());
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindById() {
		List<TsscGame> games = tsscGameDAO.findAll();
		assertEquals(games.get(0), tsscGameDAO.findById(1));
	}

	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByDateRange() {
		LocalDate minor = LocalDate.of(2020,10,01);
		LocalDate max = LocalDate.of(2020,10,30);
		List<TsscGame> resp = tsscGameDAO.findByDateRange(minor,max);
		for (TsscGame g : resp) {
			if ( g.getScheduledDate().compareTo(minor)>0 && g.getScheduledDate().compareTo(max)<0 ) {}
			else {assertTrue(false);}
		}
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByDateAndTimeRange() {
		List<TsscGame> resp = tsscGameDAO.findByDateAndTimeRange(LocalDate.of(2020, 10, 21),LocalTime.of(7,10), LocalTime.of(9,20));
		assertEquals(0, resp.size());
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindTopicsByDate() {
		List<TsscTopic[]> result = tsscGameDAO.findTopicsByDate(LocalDate.of(2020, 10, 20));
		assertEquals(1,result.size());
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByNoStoriesNoTimeControls() {
		TsscGame g = tsscGameDAO.findAll().get(0);
		g.setName("Game 2");
		tsscGameDAO.update(g);
		assertEquals("Game 2",tsscGameDAO.findByNoStoriesNoTimeControls(LocalDate.of(2020, 12,3)).get(0).getName());
	
	}

	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testDelete() {
		int size = tsscGameDAO.findAll().size();
		tsscGameDAO.delete(tsscGameDAO.findAll().get(0));
		assertEquals(size-1,tsscGameDAO.findAll().size());
	}	
	
}

