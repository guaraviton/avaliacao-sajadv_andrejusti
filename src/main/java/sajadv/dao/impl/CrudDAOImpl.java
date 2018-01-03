package sajadv.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import sajadv.dao.CrudDAO;
import sajadv.dao.DAO;
import sajadv.entity.IdEntity;

public abstract class CrudDAOImpl<T extends IdEntity> extends DAO implements CrudDAO<T>{

	Class<T> entityClass = null;
	
	@SuppressWarnings("unchecked") 
	public CrudDAOImpl() {
		this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
	
	@Override
	public T salvar(T entidade){
		return template.merge(entidade);
	}

	public void excluir(T entidade){
		template.delete(entidade);
		template.flush();
	}
	
	@SuppressWarnings("unchecked") 
	@Override
	public T get(Integer id) {
		DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);
		criteria.add(Restrictions.eq("id", id));
		List<T> retorno = (List<T>) template.findByCriteria(criteria);
	    return retorno.isEmpty() ? null : retorno.get(0);
	}
	
	@Override
	public List<T> list() {
        return (List<T>) template.loadAll(entityClass);
	}
	
}