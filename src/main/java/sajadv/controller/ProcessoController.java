package sajadv.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import sajadv.entity.Processo;
import sajadv.entity.Responsavel;
import sajadv.service.CrudService;
import sajadv.service.ProcessoService;
import sajadv.service.ResponsavelService;

@RestController
@RequestMapping("/processo")
public class ProcessoController extends CrudController<Processo>{

	@Autowired
	ProcessoService processoService;
	
	@Autowired
	ResponsavelService responsavelService;
	
	@Override
	public CrudService<Processo> getService() {
		return processoService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
    public List<Processo> query(
    		@RequestParam(required=false) String numeroProcessoUnificado,
    		@RequestParam(required=false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataDistribuicaoInicio,
    		@RequestParam(required=false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataDistribuicaoFim,
    		@RequestParam(required=false) Integer idSituacao,
    		@RequestParam(required=false) String segredoJustica,
    		@RequestParam(required=false) String pastaFisicaCliente,
    		@RequestParam(required=false) Integer idResponsavel
    	) {
		return processoService.query(numeroProcessoUnificado, dataDistribuicaoInicio, dataDistribuicaoFim, idSituacao, segredoJustica, pastaFisicaCliente, idResponsavel);
    }
	
	@RequestMapping(value="/{id}/vinculados", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
    public List<Processo> getVinculados(@PathVariable Integer id) {
		return processoService.getVinculados(id);
    }
	
	@RequestMapping(value="/{id}/responsaveis", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
    public List<Responsavel> getResponsaveis(@PathVariable Integer id) {
		return responsavelService.query(null, null, null, null, id);
    }
}