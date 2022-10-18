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
public class CustomInternalServerErrorException extends RuntimeException {

	private static final long serialVersionUID = -4465219359431472700L;

	private String codigo = EcodigosErrores.ERROR_INTERNO.getCodigoError();
	private String error = EcodigosErrores.ERROR_INTERNO.getMensajeError();
	private HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

}
