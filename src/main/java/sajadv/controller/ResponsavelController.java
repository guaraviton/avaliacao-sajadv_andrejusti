package sajadv.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.google.gson.Gson;

import sajadv.common.util.ValidationUtil;
import sajadv.entity.Responsavel;
import sajadv.service.ResponsavelService;

@RestController
@RequestMapping("/responsavel")
public class ResponsavelController{
	
	@Autowired
	ResponsavelService responsavelService;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
    public Responsavel get(@PathVariable Integer id) {
		return responsavelService.get(id);
    }
	
	@RequestMapping(value="/{id}/foto", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseStatus(HttpStatus.OK)
    public byte[] getFoto(@PathVariable Integer id, OutputStream os) {
		return responsavelService.get(id).getFoto();
    }
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
    public Boolean excluir(@PathVariable Integer id) {
		responsavelService.excluir(id);
		return true;
    }
	
	@RequestMapping(value="/all", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
    public List<Responsavel> query() {
		return responsavelService.list();
    }
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Responsavel incluir(@RequestParam(required = false) CommonsMultipartFile file, @RequestParam(required = false) String data) throws IOException {
		Gson gson = new Gson();
		Responsavel responsavelEntity = gson.fromJson(data, Responsavel.class);   
		ValidationUtil.validate(responsavelEntity);
		if(file != null){
			responsavelEntity.setFoto(file.getBytes());	
		}else{
			if(responsavelEntity.getId() != null){
				responsavelEntity.setFoto(responsavelService.get(responsavelEntity.getId()).getFoto());	
			}
		}
		return responsavelService.salvar(responsavelEntity);
	}
	
	@RequestMapping(method = RequestMethod.GET)
    public List<Responsavel> query(@RequestParam(required=false) String nome, @RequestParam(required=false) String cpf, @RequestParam(required=false) String numeroProcessoUnificado) {
        return responsavelService.query(nome, cpf, numeroProcessoUnificado);
    }
}