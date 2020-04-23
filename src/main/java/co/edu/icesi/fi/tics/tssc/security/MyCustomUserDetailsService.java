package co.edu.icesi.fi.tics.tssc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.edu.icesi.fi.tics.tssc.model.AdminType;
import co.edu.icesi.fi.tics.tssc.model.TsscAdmin;
import co.edu.icesi.fi.tics.tssc.repository.TsscAdminRepository;

@Service
public class MyCustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private TsscAdminRepository tsscAdminRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		TsscAdmin userApp = tsscAdminRepository.findByUser(userName).get();
		if (userApp != null) {
			User.UserBuilder builder = User.withUsername(userName)
					.password(userApp.getPassword())
					.roles(AdminType.superAdmin.toString());
			
			return builder.build();
		} else {
			throw new UsernameNotFoundException("User not found.");
		}
	}
}