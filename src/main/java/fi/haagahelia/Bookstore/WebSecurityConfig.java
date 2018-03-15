package fi.haagahelia.Bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import fi.haagahelia.Bookstore.web.UserDetailServiceImpl;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .authorizeRequests()
        	.antMatchers("/css/**").permitAll() // Enable css when logged out
        	.antMatchers("/signup", "/saveuser").permitAll()
        	.antMatchers("/delete/**").hasAuthority("ADMIN")
        	.anyRequest().authenticated()
        .and()
      .formLogin()
          .loginPage("/login")
          .defaultSuccessUrl("/booklist")
          .permitAll()
          .and()
      .logout()
          .permitAll();
    }
	
	/* in-memory security
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
        	.inMemoryAuthentication()
        		.withUser("user").password("user").roles("USER")
        	.and()
    			.withUser("admin").password("admin").roles("USER", "ADMIN");
        
    }*/
	
	@Autowired
	private UserDetailServiceImpl userDetailsService; 
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
}
