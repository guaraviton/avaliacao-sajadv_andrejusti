package sajadv.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

import sajadv.dao.ProcessoDAO;
import sajadv.entity.Processo;

@Repository
public class ProcessoDAOImpl extends CrudDAOImpl<Processo> implements ProcessoDAO{

	@Override
	public List<Processo> query(Integer idResponsavel) {
		DetachedCriteria criteria= DetachedCriteria.forClass(Processo.class, "q1");
		
		if(idResponsavel != null){
			criteria.createAlias("responsaveis", "responsaveis", JoinType.LEFT_OUTER_JOIN);
			criteria.createAlias("responsaveis.responsavel", "responsavel", JoinType.LEFT_OUTER_JOIN);
			criteria.add(Restrictions.eq("responsavel.id", idResponsavel));
		}
		
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
        return (List<Processo>) template.findByCriteria(criteria);
	}

}