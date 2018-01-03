package sajadv.service;

public interface EmailService {

	static final String EMAIL_ADMIN = "guaravitonrj@gmail.com";
	static final String EMAIL_FROM = "guaravitonrj@gmail.com";
	static final String EMAIL_SENDGRID_USERNAME = "guaraviton";
	static final String EMAIL_SENDGRID_PASSWORD = "sendgridsenhaguaraviton";
	void enviar(String para, String titulo, String conteudo);
	void enviar(String to, String[] bcc, String titulo, String conteudo);
	
}
