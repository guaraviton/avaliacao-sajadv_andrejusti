package sajadv.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity 
@Table(name = "processo")
public class Processo extends AutoIncrementIdEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String numeroProcessoUnificado;
	
	private Date dataDistribuicao;
	
	private String segredoJustica;
	
	private String pastaFisicaCliente;
	
	private String descricao;

	private Situacao situacao;
	
	private Processo processoVinculado;
	
	private List<ProcessoResponsavel> responsaveis = new ArrayList<ProcessoResponsavel>(0);

	@Column(name="numero_processo_unificado")
	public String getNumeroProcessoUnificado() {
		return numeroProcessoUnificado;
	}

	public void setNumeroProcessoUnificado(String numeroProcessoUnificado) {
		this.numeroProcessoUnificado = numeroProcessoUnificado;
	}

	@Column(name="data_distribuicao")
	public Date getDataDistribuicao() {
		return dataDistribuicao;
	}

	public void setDataDistribuicao(Date dataDistribuicao) {
		this.dataDistribuicao = dataDistribuicao;
	}

	@Column(name="segredo_justica")
	public String getSegredoJustica() {
		return segredoJustica;
	}

	public void setSegredoJustica(String segredoJustica) {
		this.segredoJustica = segredoJustica;
	}

	@Column(name="pasta_fisica_cliente")
	public String getPastaFisicaCliente() {
		return pastaFisicaCliente;
	}

	public void setPastaFisicaCliente(String pastaFisicaCliente) {
		this.pastaFisicaCliente = pastaFisicaCliente;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "situacao_id", nullable = false)
	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	@ManyToOne(fetch = FetchType.EAGER, optional=true)
	@JoinColumn(name = "processo_id_vinculado", nullable = true)
	public Processo getProcessoVinculado() {
		return processoVinculado;
	}

	public void setProcessoVinculado(Processo processoVinculado) {
		this.processoVinculado = processoVinculado;
	}

	@OneToMany(fetch=FetchType.LAZY, mappedBy="processo", orphanRemoval = true, cascade = {CascadeType.ALL})
	public List<ProcessoResponsavel> getResponsaveis() {
		return responsaveis;
	}

	public void setResponsaveis(List<ProcessoResponsavel> responsaveis) {
		this.responsaveis = responsaveis;
	}
}
