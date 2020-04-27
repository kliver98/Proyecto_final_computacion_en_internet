package co.edu.icesi.fi.tics.tssc;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import co.edu.icesi.fi.tics.tssc.model.AdminType;
import co.edu.icesi.fi.tics.tssc.model.TsscAdmin;
import co.edu.icesi.fi.tics.tssc.model.TsscGame;
import co.edu.icesi.fi.tics.tssc.model.TsscTopic;
import co.edu.icesi.fi.tics.tssc.service.TsscAdminServiceImpl;
import co.edu.icesi.fi.tics.tssc.service.TsscGameServiceImpl;
import co.edu.icesi.fi.tics.tssc.service.TsscTopicServiceImpl;

@SpringBootApplication
public class Taller1KliverApplication {
	//Kliver Daniel Giron - A00345647
	
	@Bean
	public Java8TimeDialect java8TimeDialect() {
		return new Java8TimeDialect();
	}
	
	public static void main(String[] args) {
		ConfigurableApplicationContext c = SpringApplication.run(Taller1KliverApplication.class, args);
		TsscAdminServiceImpl s = c.getBean(TsscAdminServiceImpl.class);
		TsscAdmin u = new TsscAdmin();
		u.setPassword("{noop}super");
		u.setUser("super");
		u.setType(AdminType.superAdmin);
		TsscAdmin v = new TsscAdmin();
		v.setPassword("{noop}admin");
		v.setUser("admin");
		v.setType(AdminType.admin);
		s.save(u);
		s.save(v);
		TsscTopicServiceImpl tImpl = c.getBean(TsscTopicServiceImpl.class);
		TsscTopic[] ts = new TsscTopic[1];
		TsscTopic t = new TsscTopic();
		t.setDefaultGroups(5);
		t.setDefaultSprints(2);
		t.setDescription("My topic description");
		t.setName("My name of topic");
		t.setGroupPrefix("Prefix");
		ts[0] = t;
		tImpl.saveTopic(t);
		TsscGameServiceImpl gImpl = c.getBean(TsscGameServiceImpl.class);
		TsscGame g = new TsscGame();
		g.setTsscTopic(t);
		g.setAdminPassword("admin");
		g.setGuestPassword("guest");
		g.setName("Mi nombre");
		g.setNGroups(10);
		g.setNSprints(5);
		g.setScheduledDate(LocalDate.now());
		g.setStartTime(LocalTime.now());
		g.setUserPassword("user");
		gImpl.saveGame(g,tImpl.findAll());
	}

}
