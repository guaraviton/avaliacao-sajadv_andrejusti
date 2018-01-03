package sajadv.integracao;

import javax.servlet.ServletContext;
import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import sajadv.entity.Processo;
import sajadv.entity.ProcessoResponsavel;
import sajadv.entity.Responsavel;
import sajadv.entity.Situacao;
import sajadv.service.ProcessoService;
import sajadv.service.ResponsavelService;
import sajadv.spring.config.mvc.MvcConfig;
import sajadv.spring.config.persistence.PersistenceConfig;

@ContextConfiguration(classes = { MvcConfig.class, PersistenceConfig.class })
@WebAppConfiguration
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
public class BaseTest {

	@Autowired
	ResponsavelService responsavelService;
	
	@Autowired
	ProcessoService processoService;
	
	@Autowired
	WebApplicationContext wac;
	
	MockMvc mockMvc;
	
	@Before
	public void setup() throws Exception {
	    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	@Test
	public void verificar_carregamento_controller() {
	    ServletContext servletContext = wac.getServletContext();
	    Assert.assertNotNull(servletContext);
	}
	
	public Responsavel inserir_responsavel(){
		Responsavel responsavel = new Responsavel();
		responsavel.setNome("nome");
		responsavel.setCpf("012.345.678-90");
		responsavel.setEmail("email@gmail.com");
		return responsavelService.salvar(responsavel);
	}
	
	public Processo inserir_processo(){
		Responsavel resposanvel = inserir_responsavel();
		Processo processo = new Processo();
		ProcessoResponsavel processoResponsavel = new ProcessoResponsavel();
		processoResponsavel.setProcesso(processo);
		processoResponsavel.setResponsavel(resposanvel);
		processo.getResponsaveis().add(processoResponsavel);
		processo.setSituacao(get_situacao_andamento());
		processo.setNumeroProcessoUnificado("12345678901234");
		return processoService.salvar(processo);
	}
	
	public Situacao get_situacao_andamento(){
		Situacao situacao = new Situacao();
		situacao.setId(1);
		return situacao;
	}
	
}
