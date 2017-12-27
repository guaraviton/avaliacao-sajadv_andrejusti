package sajadv.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import sajadv.common.util.StringUtils;
import sajadv.enums.StatusRegistro;

@MappedSuperclass
public abstract class StatusRegistroEntity extends AutoIncrementIdEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String statusRegistro;
	
	@Column(name = "status_registro", nullable = false, length = 1)
	public String getStatusRegistro() {
		return this.statusRegistro;
	}

	public void setStatusRegistro(String statusRegistro) {
		this.statusRegistro = statusRegistro;
	}
	
	@Transient
	public String getDescricaoStatusRegistro(){
		if(StringUtils.isBlank(statusRegistro)){
			return null;
		}
		return StatusRegistro.getStatusRegistro(statusRegistro).toString();
	}
}
