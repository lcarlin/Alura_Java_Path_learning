package br.com.casadocodigo.loja.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandlerControler {

	
	@ExceptionHandler(Exception.class)
	public ModelAndView trataExceptionGenerica (Exception exception ) {
		
		System.out.println("=============================================");
		System.out.println("Erro generico ocorrendo ");
		
		exception.printStackTrace();
		ModelAndView modelAndView = new ModelAndView("error");
		modelAndView.addObject("exception", exception );
		
		return modelAndView ;
	}
	
}
