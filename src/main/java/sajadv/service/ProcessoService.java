package sajadv.service;

import java.util.Date;
import java.util.List;

import sajadv.entity.Processo;

public interface ProcessoService extends CrudService<Processo>{

	List<Processo> getVinculados(Integer id);

	List<Processo> query(String numeroProcessoUnificado, Date dataDistribuicaoInicio, Date dataDistribuicaoFim,
			Integer idSituacao, String segredoJustica, String pastaFisicaCliente, Integer idResponsavel);

}
