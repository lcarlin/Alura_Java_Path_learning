package br.com.casadocodigo.loja.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.daos.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;

@Controller
@RequestMapping("produtos")
public class ProdutosController {
	
	@Autowired
	private ProdutoDAO produtoDao ;
	
	@RequestMapping("/form")
	public ModelAndView form () {
		ModelAndView modelAndView = new ModelAndView("produtos/form") ;
		modelAndView.addObject("tipos", TipoPreco.values() );
		
		return modelAndView;
		// return "produtos/form";
	}	
	@RequestMapping( method = RequestMethod.POST )
	//public String grava(String titulo , String descricao, int paginas) {
	public ModelAndView grava(Produto produto, RedirectAttributes redirectAttributes) {
		System.out.println(".=+=+.=+.=+.=+.=+.=+.=+.=+.=+.=+.=+.=+.=+.=+.");
//		System.out.println("Titulo......:-> " + titulo);
//		System.out.println("Descrição...:-> " + descricao);
//		System.out.println("Páginas.....:-> " + paginas);		
		System.out.println(produto);
		
		
		produtoDao.gravar(produto);
		
//		ModelAndView modelAndView = new ModelAndView("redirect:produtos");
		redirectAttributes.addFlashAttribute("sucesso", "Agora SIM, produto \""+produto.getTitulo() + "\" Cadastrado com muito, mas muito sucesso") ;
//		modelAndView.addObject("sucesso", "Agora SIM, produto "+produto.getTitulo() + " Cadastrado com muito, mas muito sucesso");
//		return modelAndView ;
		return new ModelAndView("redirect:produtos");
			
//		return listar () ;
//		return new ModelAndView("redirect:produtos") ;
	}
	
	@RequestMapping( method = RequestMethod.GET )
	public ModelAndView listar() {
		List<Produto> produtos = produtoDao.listar() ;
		ModelAndView modelAndView = new ModelAndView("produtos/lista") ;
		modelAndView.addObject("produtos", produtos);
		return modelAndView; 		
	}
}
