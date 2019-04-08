package es.in2.dsdibaapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class ApiSecurity  extends WebSecurityConfigurerAdapter {
	
	private String LOGIN_URL = "/login";
	
	@Autowired
	JWTProperties jwtProperties;
	
	private UserDetailsService userDetailsService;
	
	public ApiSecurity(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	
	@Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
	   web.ignoring().antMatchers(HttpMethod.OPTIONS);
	}
	
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.sessionAuthenticationStrategy(new NullAuthenticatedSessionStrategy())
			.and()	
			.csrf().disable()
			.formLogin().disable()
			.authorizeRequests()			
			.antMatchers(HttpMethod.POST, LOGIN_URL).permitAll()
			.antMatchers(HttpMethod.OPTIONS, LOGIN_URL).permitAll()
			.antMatchers("/dsdiba/api/swagger-ui.html").permitAll()
        	.antMatchers("/webjars/**", "/swagger-resources/**", "/v2/**").permitAll()
			/*.antMatchers(endpointsPrefix + endpointsLogin + "/token").permitAll()
			.antMatchers(endpointsPrefix + endpointsLogin + "/refresh").permitAll()
			.antMatchers(endpointsPrefix + endpointsLogin).permitAll()*/
			.antMatchers(HttpMethod.OPTIONS).permitAll()
			.and()
			.authorizeRequests()
			.and()
			.addFilter(new JWTAuthenticationFilter(authenticationManager(),jwtProperties))
			.addFilter(new JWTAuthorizationFilter(authenticationManager(),jwtProperties))			
			.addFilterBefore(new CorsFilter(), ChannelProcessingFilter.class)
			/*.anyRequest().authenticated()
			.and()
			.addFilter(new JWTAuthenticationFilter(authenticationManager(),jwtProperties))
			.addFilter(new JWTAuthorizationFilter(authenticationManager(),jwtProperties))			
			.addFilterBefore(new CorsFilter(), ChannelProcessingFilter.class)*/
			 ;
			 
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}
}
