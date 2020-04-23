package co.edu.icesi.fi.tics.tssc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import co.edu.icesi.fi.tics.tssc.model.AdminType;
import co.edu.icesi.fi.tics.tssc.model.TsscAdmin;
import co.edu.icesi.fi.tics.tssc.service.TsscAdminServiceImpl;

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
	}

}
