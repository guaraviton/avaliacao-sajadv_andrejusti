package sajadv.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import sajadv.dao.CrudDAO;
import sajadv.entity.BaseEntity;
import sajadv.service.CrudService;

@Service
public abstract class CrudServiceImpl<T extends BaseEntity> implements CrudService<T>{

	public abstract CrudDAO<T> getDAO();
	
	@Transactional
	public T salvar(T entity) {
		return getDAO().salvar(entity);
	}
	
	@Transactional
	public void excluir(Integer id) {
		T entity = getDAO().get(id);
		getDAO().excluir(entity);
	}
	
	@Override
	public T get(Integer id) {
		return getDAO().get(id);
	}
	
	@Override
	public List<T> list() {
		return getDAO().list();
	}
}
