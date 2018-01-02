package sajadv.service;

import java.util.List;

import sajadv.entity.Processo;

public interface ProcessoService extends CrudService<Processo>{

	List<Processo> query(String numeroProcessoUnificado, Integer idResponsavel);

	List<Processo> getVinculados(Integer id);

}
