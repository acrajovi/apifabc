package py.com.bio.apifabc.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EcodigosErrores {

	/**
	 * Clase de tipo Enum, que almacena los códigos y textos de mensajes de
	 * respuestas
	 * 
	 * @author acrajovi
	 */

	ERROR_INTERNO("g100", "Error interno del servidor"),
	ERROR_EMPTY_NOTICE("g267", "No se encuentra noticias para el texto: "),
	ERROR_INVALID_QUERY_PARAM("g268", "Parámetros Inválidos"),
	ERROR_UNAUTHORIZED("g103", "No autorizado");

	private String codigoError;
	private String mensajeError;
}
