package sajadv.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

import sajadv.common.util.StringUtils;
import sajadv.dao.ProcessoDAO;
import sajadv.entity.Processo;

@Repository
public class ProcessoDAOImpl extends CrudDAOImpl<Processo> implements ProcessoDAO{

	@Override
	public List<Processo> query(String numeroProcessoUnificado, Integer idResponsavel) {
		DetachedCriteria criteria= DetachedCriteria.forClass(Processo.class, "q1");
		
		if(idResponsavel != null){
			criteria.createAlias("responsaveis", "responsaveis", JoinType.LEFT_OUTER_JOIN);
			criteria.createAlias("responsaveis.responsavel", "responsavel", JoinType.LEFT_OUTER_JOIN);
			criteria.add(Restrictions.eq("responsavel.id", idResponsavel));
		}
		
		if(StringUtils.isNotBlank(numeroProcessoUnificado)){
			criteria.add(Restrictions.like("numeroProcessoUnificado", numeroProcessoUnificado, MatchMode.ANYWHERE));
		}
		
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.addOrder(Order.asc("numeroProcessoUnificado"));
        return (List<Processo>) template.findByCriteria(criteria);
	}

}