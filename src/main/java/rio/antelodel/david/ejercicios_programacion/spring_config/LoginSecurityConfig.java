package rio.antelodel.david.ejercicios_programacion.spring_config;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.session.ConcurrentSessionFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import rio.antelodel.david.ejercicios_programacion.controller.role.Role;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRAdministradorDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRAlumnoDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRPersonaDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRProfesorDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPersona;

@Configuration
@EnableWebSecurity
public class LoginSecurityConfig extends WebSecurityConfigurerAdapter {
	
	// DAOs
	
	@Autowired
	private IRPersonaDAO iRPersonaDAO;
	@Autowired
	private IRAlumnoDAO iRAlumnoDAO;
	@Autowired
	private IRProfesorDAO iRProfesorDAO;
	@Autowired
	private IRAdministradorDAO iRAdministradorDAO;
	
	// Functions
	
	@Bean
    @Override
    public AuthenticationManager authenticationManagerBean () throws Exception {
		
        return super.authenticationManagerBean();
        
    }
	
	@Override
	public void configure (AuthenticationManagerBuilder authenticationMgr) throws Exception {

		authenticationMgr.userDetailsService(inMemoryUserDetailsManager());
		
	}
	
    @Bean
    public SessionRegistry sessionRegistry () {
    	
        return new SessionRegistryImpl();
        
    }
    
    @Bean
    public ConcurrentSessionFilter concurrentSessionFilter() {
    	
    	return new ConcurrentSessionFilter(sessionRegistry(), new SessionInformationExpiredStrategy() {
			
			@Override
			public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
				
				HttpServletRequest request = event.getRequest();
				HttpServletResponse response = event.getResponse();
				
				redirectStrategy().sendRedirect(request, response, "/login.html");
				
			}
			
		});
    	
    }
    
    @Bean
    public RedirectStrategy redirectStrategy () {
    	
    	return new DefaultRedirectStrategy();
    	
    }
    
	@Bean
    public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {	//(5)
        return new ServletListenerRegistrationBean<>(new HttpSessionEventPublisher());
    }
	
	@Override
	protected void configure (HttpSecurity http) throws Exception {

		http.authorizeRequests()
			.antMatchers( "/favicon.ico").permitAll()
			.antMatchers("*.jsp").authenticated()
			.and()
				.formLogin().loginPage("/login.html")
				.defaultSuccessUrl("/")
				.failureUrl("/login.html?failed=1")
				.usernameParameter("email").passwordParameter("password")				
			.and()
				.logout().logoutUrl("/logout.html")
			.and()
			  	.exceptionHandling().accessDeniedPage("/access-denied.html")
			.and()
				.logout().logoutSuccessUrl("/")
			.and()
				.sessionManagement()
				.maximumSessions(100)
				.maxSessionsPreventsLogin(true)
				.expiredUrl("/login.html")
				.sessionRegistry(sessionRegistry());
		
		http.requiresChannel().antMatchers("/*").requiresSecure();
		
		http.addFilterBefore(concurrentSessionFilter(), ConcurrentSessionFilter.class);
		
	}
	
	@Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager () {
		
        final Properties users = new Properties();
        
        for (IRPersona iRP : iRPersonaDAO.getAll()) {
        	
        	String authorities = "";
        	
        	if (!iRAlumnoDAO.find(iRP.getEmail()).isNull()) authorities += "," + Role.ALUMNO;
        	if (!iRProfesorDAO.find(iRP.getEmail()).isNull()) authorities += "," + Role.PROFESOR;
        	if (!iRAdministradorDAO.find(iRP.getEmail()).isNull()) authorities += "," + Role.ADMIN;
        	
        	users.put(iRP.getEmail(), "{noop}" + iRP.getPassword() + authorities + ",enabled");
        	
        }
        
        return new InMemoryUserDetailsManager(users);
        
	}
	
}
