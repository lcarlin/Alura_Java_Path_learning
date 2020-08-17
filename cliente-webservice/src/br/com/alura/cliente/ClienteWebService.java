package br.com.alura.cliente;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;

public class ClienteWebService {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		// TODO Auto-generated method stub
		
		// The fluent API relieves the user from having to deal with manual deallocation of system
		// resources at the cost of having to buffer response content in memory in some cases.

		String conteudo = Request
			.Post("http://localhost:8080/gerenciador/empresas")
			.addHeader("Accept", "application/json")
		    .execute()
		    .returnContent()
		    .asString();
		
		System.out.println(conteudo);


	}

}
