package sajadv.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import sajadv.entity.BaseEntity;
import sajadv.service.CrudService;

public abstract class CrudController<T extends BaseEntity>{
	
	public abstract CrudService<T> getService();
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
    public T incluir(@Valid @RequestBody T entity) {
		return getService().salvar(entity);
    }
	
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
    public T alterar(@Valid @RequestBody T entity) {
		return getService().salvar(entity);
    }
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
    public T get(@PathVariable Integer id) {
		return getService().get(id);
    }
/*	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
    public List<T> query() {
		return getService().query();
    }*/
	
	/*@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
    public Boolean excluir(@PathVariable Integer id) {
		getService().excluir(id);
		return true;
    }*/
	
}
