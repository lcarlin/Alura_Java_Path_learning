package br.com.casadocodigo.loja.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.casadocodigo.loja.daos.UsuarioDAO;

@EnableWebMvcSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private UsuarioDAO usuarioDao;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		// super.configure(auth);
		// primeiro faça os bloqueios
		// depois vai liberando o que precisa
		http.authorizeRequests()
			.antMatchers("/produtos/form").hasRole("ADMIN")
			.antMatchers("/carrinho/**").permitAll()
			.antMatchers(HttpMethod.POST, "/produtos").hasRole("ADMIN")
			.antMatchers(HttpMethod.GET, "/produtos").hasRole("ADMIN")
			.antMatchers("/produtos/**").permitAll()
			.antMatchers("/resources/**").permitAll()
			.antMatchers("/").permitAll()
			.anyRequest().authenticated()			
			.and().formLogin().loginPage("/login").permitAll()
			.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		// super.configure(auth);
		auth.userDetailsService(usuarioDao)
				.passwordEncoder(new BCryptPasswordEncoder());
	}
	
    // Forma recomendada de ignorar no filtro de segurança as requisições para recursos estáticos
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }
}
