package py.com.bio.apifabc.config.security;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import py.com.bio.apifabc.config.errors.customs.CustomAuthenticationFailureHandler;
import py.com.bio.apifabc.repository.UsuariosRepository;
import py.com.bio.apifabc.utilities.Constants;
import py.com.bio.apifabc.utilities.SecurityUtil;

@Configuration
public class CustomWebSecurityConfigurerAdapter {

	@Autowired
	UsuariosRepository usuariosRepository;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()).authorizeRequests(auth -> auth.anyRequest().authenticated())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.httpBasic(Customizer.withDefaults());

		http.exceptionHandling().authenticationEntryPoint(new CustomAuthenticationFailureHandler());
		return http.build();
	}

	@Bean
	public InMemoryUserDetailsManager users() throws InvalidKeyException, NoSuchAlgorithmException {

		return new InMemoryUserDetailsManager(User
				.withUsername(usuariosRepository
						.findByIdAndPassword(Constants.APIFABC_USER_ID,
								SecurityUtil.getHmac256Signed(Constants.APIFABC_PASSWORD, Constants.API_KEY_SIGNATURE))
						.get().getId()) // usamos el user id que se guardo en la base de datos
				.password("{noop}" + usuariosRepository
						.findByIdAndPassword(Constants.APIFABC_USER_ID,
								SecurityUtil.getHmac256Signed(Constants.APIFABC_PASSWORD, Constants.API_KEY_SIGNATURE))
						.get().getPassword()) // usamos el user password que se guardo en la base de datos
				.authorities("read").build());
	}
}