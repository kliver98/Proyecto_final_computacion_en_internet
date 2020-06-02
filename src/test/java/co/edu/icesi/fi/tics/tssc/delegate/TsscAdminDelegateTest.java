package co.edu.icesi.fi.tics.tssc.delegate;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import co.edu.icesi.fi.tics.tssc.model.TsscAdmin;

@SpringBootTest
public class TsscAdminDelegateTest {

	private TsscAdminDelegate tsscAdminDelegate;
	private ArrayList<TsscAdmin> list;
	
	public void configure() {
		tsscAdminDelegate = mock(TsscAdminDelegate.class);
		list = new ArrayList<TsscAdmin>();
		TsscAdmin a1 = new TsscAdmin();
		a1.setUser("Prueba");
		list.add(a1);
	}
	
	@Test
	public void getAdmins() {
		configure();
		when(tsscAdminDelegate.getAdmins()).thenReturn(list);
		assertEquals(tsscAdminDelegate.getAdmins().size(),list.size());
	}
	
	@Test
	public void getByUsername() {
		configure();
		ArrayList<TsscAdmin> l = new ArrayList<TsscAdmin>();
		l.add(list.get(0));
		when(tsscAdminDelegate.getByUsername(list.get(0).getUser())).thenReturn(l);
		assertEquals(tsscAdminDelegate.getByUsername(list.get(0).getUser()).get(0).getUser(),list.get(0).getUser());
		assertEquals(tsscAdminDelegate.getByUsername("NOT_EXIST").size(),0);
	}
	
}
