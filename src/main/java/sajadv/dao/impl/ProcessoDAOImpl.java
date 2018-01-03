package sajadv.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

import sajadv.common.util.DateUtils;
import sajadv.common.util.StringUtils;
import sajadv.dao.ProcessoDAO;
import sajadv.entity.Processo;

@Repository
public class ProcessoDAOImpl extends CrudDAOImpl<Processo> implements ProcessoDAO{

	@SuppressWarnings("unchecked")
	@Override
	public Processo getVinculado(Integer id) {
		DetachedCriteria criteria= DetachedCriteria.forClass(Processo.class);
		criteria.add(Restrictions.eq("processoVinculado.id", id));
		List<Processo> result = (List<Processo>) template.findByCriteria(criteria);
		return result.isEmpty() ? null : result.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Processo> query(String numeroProcessoUnificado, Date dataDistribuicaoInicio, Date dataDistribuicaoFim,
			Integer idSituacao, String segredoJustica, String pastaFisicaCliente, Integer idResponsavel) {
		DetachedCriteria criteria= DetachedCriteria.forClass(Processo.class);
		
		if(StringUtils.isNotBlank(numeroProcessoUnificado)){
			criteria.add(Restrictions.like("numeroProcessoUnificado", numeroProcessoUnificado, MatchMode.ANYWHERE));
		}
		
		if(dataDistribuicaoInicio != null){
			DateUtils.trunc(dataDistribuicaoInicio);
			criteria.add(Restrictions.sqlRestriction("DATE_FORMAT(this_.data_distribuicao, '%Y-%m-%d') >= ?", dataDistribuicaoInicio, org.hibernate.type.StandardBasicTypes.DATE));
		}
		
		if(dataDistribuicaoFim != null){
			DateUtils.trunc(dataDistribuicaoFim);
			criteria.add(Restrictions.sqlRestriction("DATE_FORMAT(this_.data_distribuicao, '%Y-%m-%d') <= ?", dataDistribuicaoFim, org.hibernate.type.StandardBasicTypes.DATE));
		}
		
		if(idSituacao != null){
			criteria.add(Restrictions.eq("situacao.id", idSituacao));
		}
		
		if(StringUtils.isNotBlank(segredoJustica)){
			criteria.add(Restrictions.eq("segredoJustica", segredoJustica));
		}
		
		if(StringUtils.isNotBlank(pastaFisicaCliente)){
			criteria.add(Restrictions.like("pastaFisicaCliente", pastaFisicaCliente, MatchMode.ANYWHERE));
		}
		
		if(idResponsavel != null){
			criteria.createAlias("responsaveis", "responsaveis", JoinType.LEFT_OUTER_JOIN);
			criteria.createAlias("responsaveis.responsavel", "responsavel", JoinType.LEFT_OUTER_JOIN);
			criteria.add(Restrictions.eq("responsavel.id", idResponsavel));
		}
		
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.addOrder(Order.asc("numeroProcessoUnificado"));
        return (List<Processo>) template.findByCriteria(criteria);
	}

}