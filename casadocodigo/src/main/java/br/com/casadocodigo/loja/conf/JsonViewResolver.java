package br.com.casadocodigo.loja.conf;

import java.util.Locale;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

public class JsonViewResolver implements ViewResolver {

	@Override
	public View resolveViewName(String viewName, Locale locale) throws Exception {
		// TODO Auto-generated method stub
		
		MappingJackson2JsonView jsonView = new MappingJackson2JsonView ()  ;  
		jsonView.setPrettyPrint(true);
		// return null;
		return jsonView ;
	}

}
