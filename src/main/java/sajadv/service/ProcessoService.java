package sajadv.service;

import java.util.List;

import sajadv.entity.Processo;

public interface ProcessoService extends CrudService<Processo>{

	List<Processo> query(Integer idResponsavel);

}
