package sajadv.service;

import java.util.List;

import javax.transaction.Transactional;

import sajadv.entity.BaseEntity;

public interface CrudService<T extends BaseEntity> {

	@Transactional
	T salvar(T entity);
	
	@Transactional
	void excluir(Integer id);
	
	List<T> list();
	
	T get(Integer id);
}