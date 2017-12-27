package sajadv.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

@Entity 
@Table(name = "responsavel")
public class Responsavel extends AutoIncrementIdEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotEmpty
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@CPF
	@Column(name = "cpf", nullable = true)
	private String cpf;
	
	@NotEmpty
	@Email
	@Column(name = "email", nullable = false)
	private String email;
	
	@Lob
	private byte[] foto;

	public String getNome() {
		return nome;
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
}
