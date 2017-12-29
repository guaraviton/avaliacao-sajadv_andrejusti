package sajadv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import sajadv.entity.Processo;
import sajadv.service.CrudService;
import sajadv.service.ProcessoService;

@RestController
@RequestMapping("/processo")
public class ProcessoController extends CrudController<Processo>{

	@Autowired
	ProcessoService processoService;
	
	@Override
	public CrudService<Processo> getService() {
		return processoService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
    public List<Processo> query() {
		return processoService.list();
    }
}