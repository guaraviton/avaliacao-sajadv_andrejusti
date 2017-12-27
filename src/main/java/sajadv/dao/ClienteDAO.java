package sajadv.dao;

import java.util.List;

import sajadv.entity.Cliente;

public interface ClienteDAO extends CrudDAO<Cliente>{
	
	List<Cliente> query(String nome, String cpf, String nomeEmailCpfLike);
	
}
