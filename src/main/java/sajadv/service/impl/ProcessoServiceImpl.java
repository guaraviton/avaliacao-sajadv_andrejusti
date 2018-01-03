package sajadv.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import sajadv.common.exception.RegistroReferenciadoException;
import sajadv.common.exception.ValidacaoException;
import sajadv.common.util.DateUtils;
import sajadv.common.util.MessageUtils;
import sajadv.dao.CrudDAO;
import sajadv.dao.ProcessoDAO;
import sajadv.entity.Processo;
import sajadv.entity.ProcessoResponsavel;
import sajadv.service.EmailService;
import sajadv.service.ProcessoService;

@Service
public class ProcessoServiceImpl extends CrudServiceImpl<Processo> implements ProcessoService {
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	ProcessoDAO processoDAO;

	@Override
	public CrudDAO<Processo> getDAO() {
		return processoDAO;
	}

	@Override
	public List<Processo> query(String numeroProcessoUnificado, Date dataDistribuicaoInicio, Date dataDistribuicaoFim,
			Integer idSituacao, String segredoJustica, String pastaFisicaCliente, Integer idResponsavel) {
		return processoDAO.query(numeroProcessoUnificado, dataDistribuicaoInicio, dataDistribuicaoFim, idSituacao, segredoJustica, pastaFisicaCliente, idResponsavel);
	}
	
	@Override
	public void excluir(Integer id) {
		try{
			Processo processo = get(id);
			super.excluir(id);
			enviarEmailsResponsaveisExclusao(processo);	
		}catch(DataIntegrityViolationException e){
			throw new RegistroReferenciadoException(MessageUtils.get("registro.referenciado.erro.exclusao"));
		}
	}
	
	private void enviarEmailsResponsaveisExclusao(Processo processo) {
		List<String> responsaveis = new ArrayList<String>();
		for(ProcessoResponsavel processoResponsavel : processo.getResponsaveis()){
			responsaveis.add(processoResponsavel.getResponsavel().getEmail());
		}
		emailService.enviar(null, responsaveis.toArray(new String[0]), MessageUtils.get("titulo.email.processo"), MessageUtils.get("conteudo.email.exclusao.processo", processo.getNumeroProcessoUnificado()));
	}

	@Override
	public Processo salvar(Processo processo) {
		validar(processo);
		boolean isInclusao = processo.getId() == null;
		tratarRelacionamentos(processo);
		List<String> responsaveisEdicao = null;
		if(!isInclusao){
			responsaveisEdicao = getNovosResponsaveisEdicao(processo);
		}
		processo = super.salvar(processo);
		if(isInclusao){
			enviarEmailsResponsaveisCadastro(processo);	
		}else if(!responsaveisEdicao.isEmpty()){
			enviarEmailsResponsaveisEdicao(processo, responsaveisEdicao);
		}
		return processo;
	}
	
	private List<String> getNovosResponsaveisEdicao(Processo processo) {
		List<String> responsaveis = new ArrayList<String>();
		for(ProcessoResponsavel processoResponsavel : processo.getResponsaveis()){
			if(processoResponsavel.getId() == null){
				responsaveis.add(processoResponsavel.getResponsavel().getEmail());	
			}
		}
		return responsaveis;
	}

	private void enviarEmailsResponsaveisEdicao(Processo processo, List<String> responsaveis) {
		emailService.enviar(null, responsaveis.toArray(new String[0]), MessageUtils.get("titulo.email.processo"), MessageUtils.get("conteudo.email.edicao.processo", processo.getNumeroProcessoUnificado()));	
	}

	private void enviarEmailsResponsaveisCadastro(Processo processo) {
		List<String> responsaveis = new ArrayList<String>();
		for(ProcessoResponsavel processoResponsavel : processo.getResponsaveis()){
			responsaveis.add(processoResponsavel.getResponsavel().getEmail());
		}
		emailService.enviar(null, responsaveis.toArray(new String[0]), MessageUtils.get("titulo.email.processo"), MessageUtils.get("conteudo.email.cadastro.processo", processo.getNumeroProcessoUnificado()));
	}

	private void validar(Processo processo) {
		List<String> mensagensValidacao = new ArrayList<String>();
		validarDataDistribuicao(processo, mensagensValidacao);
		validarResponsaveis(processo, mensagensValidacao);
		validarNumeroNiveisProcesso(processo, mensagensValidacao);
		if(!mensagensValidacao.isEmpty()){
			throw new ValidacaoException(mensagensValidacao);
		}
	}

	private void validarResponsaveis(Processo processo, List<String> mensagensValidacao) {
		if(processo.getResponsaveis().isEmpty()){
			mensagensValidacao.add(MessageUtils.get("responsavel.processo.minimo.um"));
		}
		if(processo.getResponsaveis().size() > 3){
			mensagensValidacao.add(MessageUtils.get("responsavel.processo.maximo.tres"));
		}
	}

	private void validarDataDistribuicao(Processo processo, List<String> mensagensValidacao) {
		if(processo.getDataDistribuicao() != null && !DateUtils.isSmallerOrEqualThan(processo.getDataDistribuicao(), new Date(), true)){
			mensagensValidacao.add(MessageUtils.get("data.distribuicao.processo.posterior.data.atual"));
		}
	}

	private void validarNumeroNiveisProcesso(Processo processo, List<String> mensagensValidacao) {
		if(processo.getProcessoVinculado() != null){
			if(getNumeroNiveis(processo.getProcessoVinculado().getId()) > 3){
				mensagensValidacao.add(MessageUtils.get("hierarquia.processo.maximo.quatro.niveis"));
			}
		}
	}

	private int getNumeroNiveis(Integer idProcesso) {
		Processo processoPai = get(idProcesso);
		int numeroNiveis = 1;
		if(processoPai.getProcessoVinculado() != null){
			numeroNiveis = getNumeroNiveis(processoPai.getProcessoVinculado().getId()) + 1;
		}
		return numeroNiveis;
	}

	private void tratarRelacionamentos(Processo processo) {
		for(ProcessoResponsavel processoResponsavel : processo.getResponsaveis()){
			processoResponsavel.setProcesso(processo);
		}
	}

	@Override
	public List<Processo> getVinculados(Integer id){
		List<Processo> vinculados = new ArrayList<>();
		Processo vinculado_nivel_1 = processoDAO.getVinculado(id);
		if(vinculado_nivel_1 != null){
			vinculados.add(vinculado_nivel_1);
			Processo vinculado_nivel_2 = processoDAO.getVinculado(vinculado_nivel_1.getId());
			if(vinculado_nivel_2 != null){
				vinculados.add(vinculado_nivel_2);
				Processo vinculado_nivel_3 = processoDAO.getVinculado(vinculado_nivel_2.getId());
				if(vinculado_nivel_3 != null){
					vinculados.add(vinculado_nivel_3);
				}
			}
		}
		return vinculados;
	}
}
