package es.in2.dsdibaapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import es.in2.dsdibaapi.model.Professional;
import es.in2.dsdibaapi.model.Rol;
import es.in2.dsdibaapi.repository.ProfessionalRepository;

@Component
public class CustomUserDetailsService  implements UserDetailsService {
	
	@Autowired
	private ProfessionalRepository professionalRepository;
	
	 public CustomUserDetailsService(ProfessionalRepository professionalRepository) {
	        this.professionalRepository = professionalRepository;
	    }
	 
	  //  @Override
	  //  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	   //     return this.professionalRepository.findByUsername(username)
	    //        .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " not found"));
	   // }
	 
	 @Override
	  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

	   
		 Professional user = this.professionalRepository.findByUsername(username)
		            .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " not found"));

	    UserBuilder builder = null;
	    String[] roles;
	    
	    if (user != null) {
	      builder = org.springframework.security.core.userdetails.User.withUsername(username);
	      builder.password(new BCryptPasswordEncoder().encode(user.getPassword()));
	      roles = new String[user.getRol().size()];
	      
	      for (int n=0;n<user.getRol().size();n++) {
	    	  roles[n]=((Rol)user.getRol().toArray()[n]).getDescripcio();
	      }
	      
	      builder.roles(roles);
	    } else {
	      throw new UsernameNotFoundException("User not found.");
	    }

	    return builder.build();
	  }

	
}
