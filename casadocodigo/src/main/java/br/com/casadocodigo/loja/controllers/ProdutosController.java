package br.com.casadocodigo.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.casadocodigo.loja.daos.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;

@Controller
public class ProdutosController {
	
	@Autowired
	private ProdutoDAO produtoDao ;
	
	@RequestMapping("/produtos/form")
	public String form () {
		return "produtos/form";
	}	
	@RequestMapping("/produtos")
	//public String grava(String titulo , String descricao, int paginas) {
	public String grava(Produto produto) {
		System.out.println(".=+=+.=+.=+.=+.=+.=+.=+.=+.=+.=+.=+.=+.=+.=+.");
//		System.out.println("Titulo......:-> " + titulo);
//		System.out.println("Descrição...:-> " + descricao);
//		System.out.println("Páginas.....:-> " + paginas);		
		System.out.println(produto);
		
		produtoDao.gravar(produto);
		return "produtos/ok";		
	}
}
