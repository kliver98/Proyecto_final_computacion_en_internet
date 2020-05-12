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
import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.model.TsscStory;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Taller1KliverApplication.class)
@Rollback(false)
public class TsscStoryDAOTest {
	
	@Autowired
	private TsscStoryDAO tsscStoryDao;
	
	@Autowired
	private TsscGameDAO tsscGameDao;
	
	private TsscStory story1;
	private TsscGame game;
	
	@BeforeEach
	public void setUp() {
		game = new TsscGame();
		game.setName("Juego");
		story1 = new TsscStory();
		story1.setDescription("Story test 1");
		story1.setTsscGame(game);
		tsscGameDao.save(game);
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindAll() {
		int size = tsscStoryDao.findAll().size();
		tsscStoryDao.save(story1);
		assertEquals((size+1),tsscStoryDao.findAll().size());
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testUpdate() {
		tsscStoryDao.save(story1);
		TsscStory t = tsscStoryDao.findAll().get(0);
		String description = "Historia vieja";
		t.setDescription(description);
		tsscStoryDao.update(t);
		assertEquals(description, tsscStoryDao.findById(t.getId()).getDescription());
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindById() {
		List<TsscStory> stories = tsscStoryDao.findAll();
		assertEquals(stories.get(0), tsscStoryDao.findById(1));
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testDelete() {
		int size = tsscStoryDao.findAll().size();
		tsscStoryDao.delete(tsscStoryDao.findAll().get(0));
		assertEquals(size-1,tsscStoryDao.findAll().size());
	}
	
}
