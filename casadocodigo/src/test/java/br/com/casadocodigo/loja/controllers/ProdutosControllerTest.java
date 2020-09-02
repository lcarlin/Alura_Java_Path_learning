package br.com.casadocodigo.loja.controllers;

import javax.servlet.Filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.casadocodigo.loja.conf.AppWebConfiguration;
import br.com.casadocodigo.loja.conf.DataSourceConfigurationTest;
import br.com.casadocodigo.loja.conf.JPAConfiguration;
import br.com.casadocodigo.loja.conf.SecurityConfiguration;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JPAConfiguration.class, AppWebConfiguration.class,
		DataSourceConfigurationTest.class, SecurityConfiguration.class })
@ActiveProfiles("test")
public class ProdutosControllerTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;
	
    @Autowired
    private Filter springSecurityFilterChain;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac)
				.addFilter(springSecurityFilterChain)
				.build();

	}

	
	//https://cursos.alura.com.br/forum/topico-dica-se-continuar-a-aparecer-failed-to-load-applicationcontext-80779
	/*
	 * Se mesmo depois de todos os passos ainda ficar aparecendo Failed to load ApplicationContext é justamente por que na classe "~AppWebconfiguration~" 
	 * nós utilizamos a anotação @EnableCaching, e justamente essa anotação vai fazer com que o teste possua uma CacheResolver e na classe AppWebconfiguration 
	 * nós não implementamos esse método.  
	 *	A solução foi comentar a anotação para efeito de teste no JUnit.
	 *	Eu cheguei até olhar a solução no StackOverFlow, porém não entendi muito bem as credenciais que eles estavam utilizando.
	 *	Fica a dica!
	 * 
	 */
	@Test
	public void deveRetornarParaHomeComOsLivros() throws Exception{
	    mockMvc.perform(MockMvcRequestBuilders.get("/"))
	            .andExpect(MockMvcResultMatchers.model().attributeExists("produtos"))
	            .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/home.jsp"));
	}
	
	@Test
	public void somenteAdminDeveAcessarProdutosForm() throws Exception {
	    mockMvc.perform(MockMvcRequestBuilders.get("/produtos/form")
	            .with(SecurityMockMvcRequestPostProcessors
	                .user("user@casadocodigo.com.br").password("123456")
	                .roles("USUARIO")))
	            .andExpect(MockMvcResultMatchers.status().is(403));
	}
	
	
}
