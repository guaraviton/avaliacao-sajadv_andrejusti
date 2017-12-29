package sajadv.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sajadv.dao.CrudDAO;
import sajadv.dao.SituacaoDAO;
import sajadv.entity.Situacao;
import sajadv.service.SituacaoService;

@Service
public class SituacaoServiceImpl extends CrudServiceImpl<Situacao> implements SituacaoService {
	
	@Autowired
	SituacaoDAO situacaoDAO;

	@Override
	public CrudDAO<Situacao> getDAO() {
		return situacaoDAO;
	}
}
