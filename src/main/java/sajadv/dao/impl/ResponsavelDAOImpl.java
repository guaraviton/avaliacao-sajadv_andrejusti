package sajadv.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import sajadv.common.util.StringUtils;
import sajadv.dao.ResponsavelDAO;
import sajadv.entity.Responsavel;

@Repository
public class ResponsavelDAOImpl extends CrudDAOImpl<Responsavel> implements ResponsavelDAO{

	@SuppressWarnings("unchecked") 
	@Override
	public List<Responsavel> query(String nome, String cpf, String numeroProcessoUnificado) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Responsavel.class);
		if(StringUtils.isNotBlank(nome)){
			criteria.add(Restrictions.like("nome", nome, MatchMode.ANYWHERE));
		}
		if(StringUtils.isNotBlank(cpf)){
			criteria.add(Restrictions.like("cpf", cpf, MatchMode.ANYWHERE));
		}
		if(StringUtils.isNotBlank(numeroProcessoUnificado)){
			criteria.add(Restrictions.like("numeroProcessoUnificado", cpf, MatchMode.ANYWHERE));
		}
		criteria.addOrder(Order.asc("nome"));
        return (List<Responsavel>) template.findByCriteria(criteria);
	}
}