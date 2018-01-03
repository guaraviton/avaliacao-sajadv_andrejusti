package sajadv.service.impl;

import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sendgrid.SendGrid;
import com.sendgrid.SendGridException;

import sajadv.common.exception.GlobalException;
import sajadv.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);
	
	@Override
	public void enviar(String para, String titulo, String conteudo) {
		enviar(para, null, titulo, conteudo);
	}

	@Override
	public void enviar(String to, String[] bcc, String titulo, String conteudo) {
		SendGrid sendgrid = new SendGrid(EMAIL_SENDGRID_USERNAME, EMAIL_SENDGRID_PASSWORD);
		
		/*HttpHost proxy = new HttpHost("inet-sys.petrobras.com.br", 8080);
		CloseableHttpClient http = HttpClientBuilder.create().setProxy(proxy).setUserAgent("sendgrid/" + sendgrid.getVersion() + ";java").build();
		sendgrid.setClient(http);*/

	    SendGrid.Email email = new SendGrid.Email();
	    
	    if(to != null){
	    	email.addTo(to);
	    }
	    if(bcc != null){
	    	email.addBcc(bcc);	
	    }
	    email.setFrom(EMAIL_FROM);
	    email.setSubject(titulo);
	    email.setHtml(conteudo);

	    try {
	      SendGrid.Response response = sendgrid.send(email);
	      if(!response.getStatus()){
	    	  if(response.getCode() == 407){
	    		  throw new GlobalException("Access Denied");
	    	  }
	    	  //throw new GlobalException("Erro ao enviar email");
	      }
	      LOGGER.info("Email enviado com sucesso" + (to == null ? "" : " para " + to) + (bcc == null ? "" : " para bcc " + ArrayUtils.toString(bcc)) + " com o titulo " + titulo + " e conteudo " + conteudo + ". Mensagem do servidor de email: " + response.getMessage());
	    }catch (SendGridException e) {
	    	LOGGER.error("Erro ao enviar email", e);
	    	//throw new GlobalException(e);
	    }
	}

}
