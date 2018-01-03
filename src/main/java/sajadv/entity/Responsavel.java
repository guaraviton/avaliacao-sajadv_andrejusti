package sajadv.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity 
@Table(name = "responsavel")
public class Responsavel extends AutoIncrementIdEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotEmpty
	@Length(max = 100)
	private String nome;
	
	@CPF
	private String cpf;
	
	@NotEmpty
	@Email
	@Length(max = 400)
	private String email;
	
	@Lob
	@JsonIgnore
	private byte[] foto;
	
	private List<ProcessoResponsavel> processos = new ArrayList<ProcessoResponsavel>(0);
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="responsavel")
	@Fetch(value = FetchMode.SUBSELECT)
	public List<ProcessoResponsavel> getProcessos() {
		return processos;
	}

	public void setProcessos(List<ProcessoResponsavel> processos) {
		this.processos = processos;
	}

	public String getNome() {
		return nome;
	}
	
	@Transient
	@JsonGetter
	public boolean hasFoto() {
		return foto != null;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	
	@Transient
	public String getProcessosStr() {
		try{
			StringBuilder sb = new StringBuilder("");
			for(ProcessoResponsavel processoResponsavel : processos){
				if(!"".equals(sb.toString())){
					sb.append(" - ");
				}
				sb.append(processoResponsavel.getProcesso().getNumeroProcessoUnificado());
			}
			return sb.toString();
		}catch(Exception e){}
		return "";
	}
}
