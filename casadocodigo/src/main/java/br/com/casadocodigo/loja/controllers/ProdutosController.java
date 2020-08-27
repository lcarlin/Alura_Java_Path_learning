package br.com.casadocodigo.loja.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.daos.ProdutoDAO;
import br.com.casadocodigo.loja.infra.FileSaver;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;
import br.com.casadocodigo.loja.validation.ProdutoValidation;

@Controller
@RequestMapping("/produtos")
public class ProdutosController {
	
	@Autowired
	private ProdutoDAO produtoDao ;
	
	@Autowired
	private FileSaver fileSaver;
	
	@InitBinder
	public void InitBinder (WebDataBinder binder) {
		binder.addValidators(new ProdutoValidation() );				
	}
	
	@RequestMapping("/form")
	public ModelAndView form (Produto produto) {
		ModelAndView modelAndView = new ModelAndView("produtos/form") ;
		modelAndView.addObject("tipos", TipoPreco.values() );
		
		return modelAndView;
		// return "produtos/form";
	}	
	@RequestMapping( method = RequestMethod.POST )
	//public String grava(String titulo , String descricao, int paginas) {
	public ModelAndView grava(MultipartFile sumario,  @Valid Produto produto, BindingResult result, RedirectAttributes redirectAttributes) {
		System.out.println(".=+=+.=+.=+.=+.=+.=+.=+.=+.=+.=+.=+.=+.=+.=+.");
//		System.out.println("Titulo......:-> " + titulo);
//		System.out.println("Descrição...:-> " + descricao);
//		System.out.println("Páginas.....:-> " + paginas);
		
		System.out.println(sumario.getOriginalFilename());
		

		
		if ( result.hasErrors()) {
			return form (produto) ;
		}
		System.out.println(produto);
		String path = fileSaver.write("arquivos-sumario", sumario);
		
		produto.setSumarioPath(path);
		
		produtoDao.gravar(produto);
		
//		ModelAndView modelAndView = new ModelAndView("redirect:produtos");
		redirectAttributes.addFlashAttribute("sucesso", "Agora SIM, produto \""+produto.getTitulo() + "\" Cadastrado com muito, mas muito sucesso, sob o ID de Numero -> " + produto.getId()) ;
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
