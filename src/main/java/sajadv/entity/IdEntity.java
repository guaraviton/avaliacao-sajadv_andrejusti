package sajadv.entity;


public abstract class IdEntity extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected Integer id;
	
	public abstract Integer getId();
	
	public void setId(Integer id) {
		this.id = id;
	}
	
}
