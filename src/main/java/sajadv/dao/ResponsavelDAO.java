package sajadv.dao;

import java.util.List;

import sajadv.entity.Responsavel;

public interface ResponsavelDAO extends CrudDAO<Responsavel>{
	
	List<Responsavel> query(String nome, String cpf, String nomeEmailCpfLike);

}
