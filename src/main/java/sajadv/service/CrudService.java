package sajadv.service;

import java.util.List;

import javax.transaction.Transactional;

import sajadv.entity.BaseEntity;

public interface CrudService<T extends BaseEntity> {

	@Transactional
	T salvar(T entity);
	
	@Transactional
	void excluir(Integer id);
	
	@Transactional
	void inativar(Integer id);
	
	@Transactional
	void ativar(Integer id);
	
	List<T> query();
	
	T get(Integer id);
}