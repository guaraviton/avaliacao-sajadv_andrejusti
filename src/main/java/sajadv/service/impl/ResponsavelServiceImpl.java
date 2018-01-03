package sajadv.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import sajadv.common.exception.RegistroReferenciadoException;
import sajadv.common.util.MessageUtils;
import sajadv.dao.CrudDAO;
import sajadv.dao.ResponsavelDAO;
import sajadv.entity.Responsavel;
import sajadv.service.ResponsavelService;

@Service
public class ResponsavelServiceImpl extends CrudServiceImpl<Responsavel> implements ResponsavelService {
	
	@Autowired
	ResponsavelDAO responsavelDAO;

	@Override
	public void excluir(Integer id) {
		try{
			super.excluir(id);
		}catch(DataIntegrityViolationException e){
			throw new RegistroReferenciadoException(MessageUtils.get("registro.responsavel.referenciado.erro.exclusao"));
		}
	}
	
	@Override
	public CrudDAO<Responsavel> getDAO() {
		return responsavelDAO;
	}

	@Override
	public List<Responsavel> query(String nome, String cpf, String numeroProcessoUnificado, String nomeEmailCpfLike, Integer idProcesso) {
		return responsavelDAO.query(nome, cpf, numeroProcessoUnificado, nomeEmailCpfLike, idProcesso);
	}

}
