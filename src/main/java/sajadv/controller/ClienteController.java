package sajadv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sajadv.entity.Cliente;
import sajadv.service.ClienteService;
import sajadv.service.CrudService;

@RestController
@RequestMapping("/cliente")
public class ClienteController extends CrudController<Cliente>{
	
	@Autowired
	ClienteService service;

	@Override
	public CrudService<Cliente> getService() {
		return service;
	}
	
	@RequestMapping(method = RequestMethod.GET)
    public String query() {
        return "oi";
    }
}