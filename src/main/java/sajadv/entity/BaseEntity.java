package sajadv.entity;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import sajadv.common.util.HibernateUtils;

@MappedSuperclass
public class BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Transient
	@JsonIgnore
	public Object getRealClass(){
		return HibernateUtils.getRealObject(this);
	}
}
