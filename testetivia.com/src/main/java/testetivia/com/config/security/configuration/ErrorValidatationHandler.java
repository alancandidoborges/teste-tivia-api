package testetivia.com.config.security.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ErrorValidatationHandler{

	@Autowired
	private MessageSource messageSource;
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<CamposInvalidos> handler(MethodArgumentNotValidException exception){
		List<CamposInvalidos> erros = new ArrayList<>();
		List<FieldError> fieldsErro = exception.getBindingResult().getFieldErrors();
		fieldsErro.forEach(e -> {
			String msg = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			CamposInvalidos erro = new CamposInvalidos(e.getField(), msg);
			erros.add(erro);
		});
 	    return erros;
	}
}
