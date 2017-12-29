package sajadv.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sajadv.dao.CrudDAO;
import sajadv.dao.ProcessoDAO;
import sajadv.entity.Processo;
import sajadv.service.ProcessoService;

@Service
public class ProcessoServiceImpl extends CrudServiceImpl<Processo> implements ProcessoService {
	
	@Autowired
	ProcessoDAO processoDAO;

	@Override
	public CrudDAO<Processo> getDAO() {
		return processoDAO;
	}
}
