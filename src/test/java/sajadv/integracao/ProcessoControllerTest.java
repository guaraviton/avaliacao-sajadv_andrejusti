package sajadv.integracao;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Test;

public class ProcessoControllerTest extends BaseTest{

	static final String URL = "/processo";
	
	@Test
	public void verificar_carregamento_controller() {
	    Assert.assertNotNull(wac.getBean("processoController"));
	}
	
	@Test
	public void verificar_busca() throws Exception {
		this.mockMvc
	      .perform(get(URL))
	      .andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	public void verificar_get() throws Exception {
		this.mockMvc.perform(get(URL + "/" + inserir_processo().getId()))		    
		.andDo(print()).andExpect(status().isOk())
		.andExpect(content().contentType("application/json;charset=UTF-8"))
		.andExpect(jsonPath("$.numeroProcessoUnificado").value("12345678901234"));
	}
}