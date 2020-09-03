package guru.sfg.brewery.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import guru.sfg.brewery.security.SfgPasswordEncoderFactories;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//	public RestHeaderAuthFilter restHeaderAuthFilter(AuthenticationManager authenticationManager) {
//		RestHeaderAuthFilter filter = new RestHeaderAuthFilter(new AntPathRequestMatcher("/api/**"));
//		filter.setAuthenticationManager(authenticationManager);
//		return filter;
//	}
//
//	public RestUrlAuthFilter restUrlAuthFilter(AuthenticationManager authenticationManager) {
//		RestUrlAuthFilter filter = new RestUrlAuthFilter(new AntPathRequestMatcher("/api/**"));
//		filter.setAuthenticationManager(authenticationManager);
//		return filter;
//	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.addFilterBefore(restHeaderAuthFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class)
//				.csrf().disable();
//
//		http.addFilterBefore(restUrlAuthFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);

		http
				.authorizeRequests(authorize -> {
					authorize
							.antMatchers("/h2-console/**").permitAll() // do not use in production!
							.antMatchers("/", "/webjars/**", "/login", "/resources/**").permitAll();
				})
				.authorizeRequests()
				.anyRequest().authenticated()
				.and()
				.formLogin().and()
				.httpBasic()
				.and().csrf().disable();
		
		// h2 console config
		http.headers().frameOptions().sameOrigin();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return SfgPasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
//	@Autowired
//	JpaUserDetailsService jpaUserDetailsService;

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(this.jpaUserDetailsService).passwordEncoder(passwordEncoder());
		
//		auth.inMemoryAuthentication()
//				.withUser("spring")
//				.password("{bcrypt}$2a$10$b8JkTXMDnhvdZ7mB3zXcmO8VPY8d/ko0a5fsqWA6ZfjORjoXanoOe")
//				.roles("ADMIN")
//				.and()
//				.withUser("user")
//				.password("{sha256}a09076e35b872736bf7a19fcf0e7788c47dc9f913685d22d846e9de65afac09b3b21e3afa52d7260")
//				.roles("USER");
//		
//		auth.inMemoryAuthentication()
//				.withUser("scott")
//				.password("{bcrypt15}$2a$15$RLj6ZtVHYnnukYkT27HmiODtdyd.KUQVdNbWDu/hHzZ3iAdSswYK.")
//				.roles("CUSTOMER");
//	}

	//	@Override
	//	@Bean
	//	protected UserDetailsService userDetailsService() {
	//		UserDetails admin = User.withDefaultPasswordEncoder()
	//				.username("spring")
	//				.password("guru")
	//				.roles("ADMIN")
	//				.build();
	//		
	//		UserDetails user = User.withDefaultPasswordEncoder()
	//				.username("user")
	//				.password("password")
	//				.roles("USER")
	//				.build();
	//		
	//		return new InMemoryUserDetailsManager(admin, user);
	//	}

}
