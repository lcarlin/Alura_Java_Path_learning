package br.com.casadocodigo.loja.conf;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ServletSpringMVC extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		// return null;
		return new Class[] {SecurityConfiguration.class, AppWebConfiguration.class, JPAConfiguration.class } ; 
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] {}; 
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] {"/"} ;
	}
	
	@Override
	protected Filter[] getServletFilters() {
		// TODO Auto-generated method stub
		//return super.getServletFilters();
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8");
		return new Filter[] {encodingFilter, new OpenEntityManagerInViewFilter() };
	}
	
	//veja tbm https://cursos.alura.com.br/forum/topico-atualizacao-resources-nao-sao-carregados-na-aula-10-58813
	@Override
	protected void customizeRegistration(Dynamic registration) {
		// TODO Auto-generated method stub
	//super.customizeRegistration(registration);
		registration.setMultipartConfig(new MultipartConfigElement(""));
	}
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);
		servletContext.addListener(RequestContextListener.class);
		servletContext.setInitParameter("spring.profiles.active", "dev");
	}	
}
