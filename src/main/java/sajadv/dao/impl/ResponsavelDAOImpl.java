package sajadv.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

import sajadv.common.util.StringUtils;
import sajadv.dao.ResponsavelDAO;
import sajadv.entity.Responsavel;

@Repository
public class ResponsavelDAOImpl extends CrudDAOImpl<Responsavel> implements ResponsavelDAO{

	@SuppressWarnings("unchecked") 
	@Override
	public List<Responsavel> query(String nome, String cpf, String numeroProcessoUnificado, String nomeEmailCpfLike, Integer idProcesso) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Responsavel.class);
		if(StringUtils.isNotBlank(nome)){
			criteria.add(Restrictions.like("nome", nome, MatchMode.ANYWHERE));
		}
		if(StringUtils.isNotBlank(cpf)){
			criteria.add(Restrictions.like("cpf", cpf, MatchMode.ANYWHERE));
		}
		criteria.createAlias("processos", "processos", JoinType.LEFT_OUTER_JOIN);
		criteria.createAlias("processos.processo", "processo", JoinType.LEFT_OUTER_JOIN);
		if(StringUtils.isNotBlank(numeroProcessoUnificado)){
			criteria.add(Restrictions.eq("processo.numeroProcessoUnificado", numeroProcessoUnificado));	
		}
		if(idProcesso != null){
			criteria.add(Restrictions.eq("processo.id", idProcesso));	
		}
		if(StringUtils.isNotBlank(nomeEmailCpfLike)){
			Criterion cpfLike= Restrictions.like("cpf", nomeEmailCpfLike, MatchMode.START);
			Criterion nomeLike= Restrictions.like("nome", nomeEmailCpfLike, MatchMode.ANYWHERE);
			Criterion emailLike= Restrictions.like("email", nomeEmailCpfLike, MatchMode.ANYWHERE);
			criteria.add(Restrictions.or(cpfLike, nomeLike, emailLike));
		}
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.addOrder(Order.asc("nome"));
        return (List<Responsavel>) template.findByCriteria(criteria);
	}

}