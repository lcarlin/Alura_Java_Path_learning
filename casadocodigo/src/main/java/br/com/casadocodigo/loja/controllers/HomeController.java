package br.com.casadocodigo.loja.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@RequestMapping("/")
	public String index () {
		System.out.println("-=--=--=--=--=--=--=--=--=--=--=--=--=--=--=--=--=-");
		System.out.println("Entrei a home da casa do código");
		return "home" ;
		
	}

}
