package sajadv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sajadv.entity.Responsavel;
import sajadv.service.ResponsavelService;
import sajadv.service.CrudService;

@RestController
@RequestMapping("/responsavel")
public class ResponsavelController extends CrudController<Responsavel>{
	
	@Autowired
	ResponsavelService service;

	@Override
	public CrudService<Responsavel> getService() {
		return service;
	}
	
	@RequestMapping(method = RequestMethod.GET)
    public List<Responsavel> query(@RequestParam(required=false) String nome, @RequestParam(required=false) String cpf, @RequestParam(required=false) String numeroProcessoUnificado) {
        return service.query(nome, cpf, numeroProcessoUnificado);
    }
}