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
public class CustomNotFoundException extends Exception {

	private static final long serialVersionUID = 4331386649294075548L;

	private String codigo = EcodigosErrores.ERROR_EMPTY_NOTICE.getCodigoError();
	private String textoBusqueda;
	private HttpStatus httpStatus = HttpStatus.NOT_FOUND;
	private String error = EcodigosErrores.ERROR_EMPTY_NOTICE.getMensajeError();

	public CustomNotFoundException(String textoBusqueda) {
		super();
		this.error = error + textoBusqueda;

	}
}
