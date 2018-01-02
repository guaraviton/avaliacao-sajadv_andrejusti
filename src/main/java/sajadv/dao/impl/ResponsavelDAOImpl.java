package sajadv.dao.impl;

import java.util.List;

import org.hibernate.criterion.Criterion;
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
	public List<Responsavel> query(String nome, String cpf, String numeroProcessoUnificado, String nomeEmailCpfLike) {
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
		if(StringUtils.isNotBlank(nomeEmailCpfLike)){
			Criterion cpfLike= Restrictions.like("cpf", nomeEmailCpfLike, MatchMode.START);
			Criterion nomeLike= Restrictions.like("nome", nomeEmailCpfLike, MatchMode.ANYWHERE);
			Criterion emailLike= Restrictions.like("email", nomeEmailCpfLike, MatchMode.ANYWHERE);
			criteria.add(Restrictions.or(cpfLike, nomeLike, emailLike));
		}
		criteria.addOrder(Order.asc("nome"));
        return (List<Responsavel>) template.findByCriteria(criteria);
	}

}