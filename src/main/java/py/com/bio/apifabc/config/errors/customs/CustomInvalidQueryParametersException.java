package py.com.bio.apifabc.config.errors.customs;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import py.com.bio.apifabc.enums.EcodigosErrores;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomInvalidQueryParametersException extends Exception {

	private static final long serialVersionUID = -683699767737233828L;

	private String codigo = EcodigosErrores.ERROR_INVALID_QUERY_PARAM.getCodigoError();
	private String error = EcodigosErrores.ERROR_INVALID_QUERY_PARAM.getMensajeError();
	private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

}
