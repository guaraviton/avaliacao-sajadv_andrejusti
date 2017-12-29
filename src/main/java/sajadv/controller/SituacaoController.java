package sajadv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import sajadv.entity.Situacao;
import sajadv.service.SituacaoService;

@RestController
@RequestMapping("/situacao")
public class SituacaoController{

	@Autowired
	SituacaoService situacaoService;
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
    public List<Situacao> query() {
		return situacaoService.list();
    }
	
}