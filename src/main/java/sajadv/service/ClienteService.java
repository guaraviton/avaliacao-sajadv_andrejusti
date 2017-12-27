package sajadv.service;

import java.util.List;

import sajadv.entity.Cliente;

public interface ClienteService extends CrudService<Cliente>{

	List<Cliente> query(String nome, String cpf, String nomeEmailCpfLike);

}
