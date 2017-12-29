package sajadv.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ProfessorDisciplina generated by hbm2java
 */
@Entity
@Table(name = "processo_responsavel")
public class ProcessoResponsavel extends AutoIncrementIdEntity {

	private Processo processo;
	
	private Responsavel responsavel;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "processo_id", nullable = false)
	public Processo getProcesso() {
		return processo;
	}
	
	public void setProcesso(Processo processo) {
		this.processo = processo;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "responsavel_id", nullable = false)
	public Responsavel getResponsavel() {
		return responsavel;
	}
	
	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}
}
