package sajadv.service;

import java.util.List;

import sajadv.entity.Responsavel;

public interface ResponsavelService extends CrudService<Responsavel>{

	List<Responsavel> query(String nome, String cpf, String numeroProcessoUnificado);

}
