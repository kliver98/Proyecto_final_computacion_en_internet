package co.edu.icesi.fi.tics.tssc.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testng.Assert.assertTrue;

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
import co.edu.icesi.fi.tics.tssc.model.TsscAdmin;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Taller1KliverApplication.class)
@Rollback(false)
public class TsscAdminDAOTest {
	
	@Autowired
	private TsscAdminDAO tsscAdminDAO;
	private TsscAdmin admin1;
	
	@BeforeEach
	public void setUp() {
		admin1 = new TsscAdmin();
		admin1.setUser("Admin 1");
		tsscAdminDAO.save(admin1);
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testSave() {
		int size = tsscAdminDAO.findAll().size();
		TsscAdmin adminTest = new TsscAdmin();
		adminTest.setUser("Random");
		tsscAdminDAO.save(adminTest);
		assertTrue((size+1)==tsscAdminDAO.findAll().size());
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindById() {
		assertEquals(admin1.getUser(),tsscAdminDAO.findById(1).getUser());
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindAll() {
		List<TsscAdmin> admins = tsscAdminDAO.findAll();
		assertEquals(admins.get(0), tsscAdminDAO.findById(1));
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByUser() {
		List<TsscAdmin> founds = tsscAdminDAO.findByUser(admin1.getUser());
		for (TsscAdmin admin : founds) {
			assertEquals(admin.getUser(), admin1.getUser());
		}
	}

}
