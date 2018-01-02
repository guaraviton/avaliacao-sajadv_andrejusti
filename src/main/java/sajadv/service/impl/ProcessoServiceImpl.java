package sajadv.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sajadv.dao.CrudDAO;
import sajadv.dao.ProcessoDAO;
import sajadv.entity.Processo;
import sajadv.entity.ProcessoResponsavel;
import sajadv.service.ProcessoService;

@Service
public class ProcessoServiceImpl extends CrudServiceImpl<Processo> implements ProcessoService {
	
	@Autowired
	ProcessoDAO processoDAO;

	@Override
	public CrudDAO<Processo> getDAO() {
		return processoDAO;
	}

	@Override
	public List<Processo> query(Integer idResponsavel) {
		return processoDAO.query(idResponsavel);
	}
	
	@Override
	public Processo salvar(Processo entity) {
		tratarRelacionamentos(entity);
		return super.salvar(entity);
	}

	private void tratarRelacionamentos(Processo processo) {
		for(ProcessoResponsavel processoResponsavel : processo.getResponsaveis()){
			processoResponsavel.setProcesso(processo);
		}
	}
}
