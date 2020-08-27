/*
 * PAra resolver problemas de salvar arquivos 
 * https://www.alura.com.br/artigos/usando-a-pasta-do-tomcat-pelo-eclipse
 * Para servidores baseados em Windows verioficar a necessidade de substuir as barras "/" por "\\" 
*/

package br.com.casadocodigo.loja.infra;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileSaver {
	
	@Autowired
	private HttpServletRequest request ; 
	
	public String write(String baseFolder, MultipartFile file) {
		try {
			String realPath = request.getServletContext().getRealPath("/" + baseFolder);			
			String path = realPath + "/" + file.getOriginalFilename();			
			file.transferTo(new File(path));
			return baseFolder + "/" + file.getOriginalFilename();
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

}
