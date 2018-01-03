package sajadv.integracao;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Test;

public class ResponsavelControllerTest extends BaseTest{

	static final String URL = "/responsavel";
	
	@Test
	public void verificar_carregamento_controller() {
	    Assert.assertNotNull(wac.getBean("responsavelController"));
	}
	
	@Test
	public void verificar_busca() throws Exception {
		this.mockMvc
	      .perform(get(URL))
	      .andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	public void verificar_get() throws Exception {
		this.mockMvc.perform(get("/responsavel/" + inserir_responsavel().getId()))		    
		.andDo(print()).andExpect(status().isOk())
		.andExpect(content().contentType("application/json;charset=UTF-8"))
		.andExpect(jsonPath("$.nome").value("nome"));
	}
	
	@Test
	public void verificar_inclusao() throws Exception {
		this.mockMvc.perform(post(URL)
		.param("data", "{'nome':'nome','email':'email@gmail.com','cpf':'012.345.678-90'}"))		    
		.andDo(print()).andExpect(status().isCreated())
		.andExpect(content().contentType("application/json;charset=UTF-8"));
	}
	
	@Test
	public void verificar_exclusao() throws Exception {
		this.mockMvc.perform(delete(URL + "/" + inserir_responsavel().getId()))		    
				.andDo(print()).andExpect(status().isNoContent())
				.andExpect(content().contentType("application/json;charset=UTF-8"));
	}
	
	@Test
	public void verificar_nome_mais_100_caracteres() throws Exception {
		this.mockMvc.perform(post(URL)
		.param("data", "{'nome':'lorem ipsum dolor lorem ipsum dolor lorem ipsum dolor lorem ipsum dolor lorem ipsum dolor lorem ipsum dolor lorem','email':'email@gmail.com','cpf':'012.345.678-90'}"))		    
		.andDo(print()).andExpect(status().isBadRequest())
		.andExpect(content().contentType("application/json;charset=UTF-8"))
		.andExpect(jsonPath("$.errors").isArray())
		.andExpect(jsonPath("$.errors[0].message").value("Tamanho deve estar entre 0 e 100"));
	}
	
	@Test
	public void verificar_cpf_invalido() throws Exception {
		this.mockMvc.perform(post(URL)
		.param("data", "{'nome':'nome','email':'email@gmail.com','cpf':'111.111.111-11'}"))		    
		.andDo(print()).andExpect(status().isBadRequest())
		.andExpect(content().contentType("application/json;charset=UTF-8"))
		.andExpect(jsonPath("$.errors").isArray())
		.andExpect(jsonPath("$.errors[0].message").value("CPF inválido"));
	}
	
	@Test
	public void verificar_email_invalido() throws Exception {
		this.mockMvc.perform(post(URL)
		.param("data", "{'nome':'nome','email':'email','cpf':'012.345.678-90'}"))		    
		.andDo(print()).andExpect(status().isBadRequest())
		.andExpect(content().contentType("application/json;charset=UTF-8"))
		.andExpect(jsonPath("$.errors").isArray())
		.andExpect(jsonPath("$.errors[0].message").value("Não é um endereço de e-mail"));
	}
}
