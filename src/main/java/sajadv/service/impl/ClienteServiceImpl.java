package sajadv.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sajadv.dao.ClienteDAO;
import sajadv.dao.CrudDAO;
import sajadv.entity.Cliente;
import sajadv.service.ClienteService;

@Service
public class ClienteServiceImpl extends CrudServiceImpl<Cliente> implements ClienteService {
	
	@Autowired
	ClienteDAO clienteDAO;

	@Override
	public CrudDAO<Cliente> getDAO() {
		return clienteDAO;
	}

	@Override
	public List<Cliente> query(String nome, String cpf, String nomeEmailCpfLike) {
		return clienteDAO.query(nome, cpf, nomeEmailCpfLike);
	}
}
