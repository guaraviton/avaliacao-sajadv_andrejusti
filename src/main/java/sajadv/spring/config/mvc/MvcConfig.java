package sajadv.spring.config.mvc;

import java.util.List;
import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module.Feature;

import sajadv.spring.interceptor.LogInteceptor;

@Configuration
@ComponentScan(basePackages = { "sajadv" })
public class MvcConfig extends WebMvcConfigurationSupport  {

	private static final Locale locale = new Locale("pt", "BR");
	
	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer properties() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver lr = new SessionLocaleResolver();
		lr.setDefaultLocale(locale);
		Locale.setDefault(locale);
		return lr;
	}
	
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setDefaultEncoding("utf-8");
		return resolver;
	}

	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		String[] resources = { "classpath:mensagens" };
		messageSource.setBasenames(resources);
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Override
	public void configureMessageConverters(
			List<HttpMessageConverter<?>> converters) {
		converters.add(jacksonConverter());
		addDefaultHttpMessageConverters(converters);
	}
	
	public MappingJackson2HttpMessageConverter jacksonConverter() {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.getObjectMapper().disable(
				DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		converter.getObjectMapper().disable(
				DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
		converter.getObjectMapper().disable(
				SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		converter.getObjectMapper().enable(
				DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
		converter.getObjectMapper().enable(
				DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY);
		converter.getObjectMapper().setSerializationInclusion(
				JsonInclude.Include.NON_EMPTY);
		converter.getObjectMapper().registerModule(
				hibernate4Module());
		return converter;
	}

	/**
	 * Impedir serialize de mapeamentos lazy
	 * @return
	 */
	public Hibernate4Module hibernate4Module() {
		Hibernate4Module hibernate4Module = new Hibernate4Module();
		hibernate4Module.configure(Feature.USE_TRANSIENT_ANNOTATION, false);
		return hibernate4Module;
	}
	
	@Bean
	public LogInteceptor logAcessoInteceptor() {
		return new LogInteceptor();
	}
	
	@Override
	public void addInterceptors(final InterceptorRegistry registry) {
		registry.addInterceptor(logAcessoInteceptor());
	}
}