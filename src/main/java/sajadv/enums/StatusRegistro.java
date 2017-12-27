package sajadv.enums;

import sajadv.common.util.MessageUtils;

public enum StatusRegistro {
	
	ATIVO("A"){
		@Override
        public String toString() {
            return MessageUtils.get("label.ativo");
        }
	}, EXCLUIDO("C"){
		@Override
        public String toString() {
            return MessageUtils.get("label.excluido");
        }
	}, INATIVO("I"){
		@Override
        public String toString() {
            return MessageUtils.get("label.inativo");
        }
	}, TODOS("T");
	
	String codigo;
	
    private StatusRegistro(final String codigo) {
    	this.codigo = codigo;
    }
    
    public static StatusRegistro getStatusRegistro(final String codigo) {
    	for (StatusRegistro e : StatusRegistro.values()) {
            if (codigo.equals(e.getCodigo())) {
            	return e;
            }
    	}
    	return null;
    }
    
    public String getCodigo() {
    	return codigo;
    }
}
