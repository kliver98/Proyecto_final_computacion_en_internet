package co.edu.icesi.fi.tics.tssc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import co.edu.icesi.fi.tics.tssc.model.AdminType;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private LoggingAccessDeniedHandler accessDeniedHandler;

//	@Autowired
//	private MyCustomUserDetailsService userDetailsService;

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.authenticationProvider(authenticationProvider());
//	}

//	@Bean
//	public DaoAuthenticationProvider authenticationProvider() {
//		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//		authProvider.setUserDetailsService(userDetailsService);
//		authProvider.setPasswordEncoder(encoder());
//		return authProvider;
//	}
//
//	@Bean
//	public PasswordEncoder encoder() {
//		return new BCryptPasswordEncoder(11);
//	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {

		httpSecurity
		.authorizeRequests().antMatchers("/api/**").permitAll()
		.and()
		.authorizeRequests().antMatchers("/topic/add/**").hasRole(AdminType.superAdmin.toString())
			/*.antMatchers("/apps/**")
			.hasAnyRole("superAdmin","admin")*/.anyRequest().authenticated()
		.and().formLogin().loginPage("/login").permitAll()
		.and().logout().invalidateHttpSession(true).clearAuthentication(true)
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout")
					.permitAll()
		.and().exceptionHandling().accessDeniedHandler(accessDeniedHandler);
	}
}