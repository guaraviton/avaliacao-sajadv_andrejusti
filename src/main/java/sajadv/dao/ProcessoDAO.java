package sajadv.dao;

import java.util.List;

import sajadv.entity.Processo;

public interface ProcessoDAO extends CrudDAO<Processo>{

	List<Processo> query(String numeroProcessoUnificado, Integer idResponsavel);

	Processo getVinculado(Integer id);
	
}
