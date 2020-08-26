package br.com.casadocodigo.loja.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@RequestMapping("/")
	public String index () {
		System.out.println("-=--=--=--=--=--=--=--=--=--=--=--=--=--=--=--=--=-");
		System.out.println("Entrei em A Home de A Casa de O Código");
		return "home" ;
		
	}

}
