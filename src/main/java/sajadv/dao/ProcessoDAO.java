package sajadv.dao;

import java.util.Date;
import java.util.List;

import sajadv.entity.Processo;

public interface ProcessoDAO extends CrudDAO<Processo>{

	Processo getVinculado(Integer id);

	List<Processo> query(String numeroProcessoUnificado, Date dataDistribuicaoInicio, Date dataDistribuicaoFim,
			Integer idSituacao, String segredoJustica, String pastaFisicaCliente, Integer idResponsavel);
	
}
