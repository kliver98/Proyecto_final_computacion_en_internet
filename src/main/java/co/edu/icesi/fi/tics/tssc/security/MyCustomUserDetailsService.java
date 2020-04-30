package co.edu.icesi.fi.tics.tssc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.edu.icesi.fi.tics.tssc.model.TsscAdmin;
import co.edu.icesi.fi.tics.tssc.repository.TsscAdminRepository;

@Service
public class MyCustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private TsscAdminRepository tsscAdminRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		TsscAdmin admin = tsscAdminRepository.findByUser(userName).get();
		if (admin != null) {
			User.UserBuilder builder = User.withUsername(userName)
					.password(admin.getPassword())
					.roles(admin.getType().toString());
			
			return builder.build();
		} else {
			throw new UsernameNotFoundException("Admin not found.");
		}
	}
}