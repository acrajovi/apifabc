package py.com.bio.apifabc.config.errors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import py.com.bio.apifabc.config.errors.customs.CustomErrorModel;
import py.com.bio.apifabc.config.errors.customs.CustomInternalServerErrorException;
import py.com.bio.apifabc.config.errors.customs.CustomInvalidQueryParametersException;
import py.com.bio.apifabc.config.errors.customs.CustomNotFoundException;

@ControllerAdvice
public class ErrorsControllerAdvice {

	/**
	 * Error de parámetro inválido
	 * 
	 * @param customInvalidQueryParametersException
	 * @return
	 */
	@ExceptionHandler(CustomInvalidQueryParametersException.class)
	public ResponseEntity<CustomErrorModel> generateInvalidParameterException(
			CustomInvalidQueryParametersException customInvalidQueryParametersException) {

		CustomErrorModel customErrorModel = new CustomErrorModel();
		customErrorModel.setCodigo(customInvalidQueryParametersException.getCodigo());
		customErrorModel.setError(customInvalidQueryParametersException.getError());

		return new ResponseEntity<CustomErrorModel>(customErrorModel,
				customInvalidQueryParametersException.getHttpStatus());
	}

	/**
	 * Error de resultado vacío
	 * 
	 * @param customNotFoundException
	 * @return
	 */
	@ExceptionHandler(CustomNotFoundException.class)
	public ResponseEntity<CustomErrorModel> generateNotFoundException(CustomNotFoundException customNotFoundException) {

		CustomErrorModel customErrorModel = new CustomErrorModel();
		customErrorModel.setCodigo(customNotFoundException.getCodigo());
		customErrorModel.setError(customNotFoundException.getError());

		return new ResponseEntity<CustomErrorModel>(customErrorModel, customNotFoundException.getHttpStatus());
	}

	/**
	 * Internal Server Error
	 * 
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<CustomErrorModel> generateInternalSererError(Exception exception) {

		CustomErrorModel customErrorModel = new CustomErrorModel();
		CustomInternalServerErrorException customInternalServerErrorException = new CustomInternalServerErrorException();

		customErrorModel.setCodigo(customInternalServerErrorException.getCodigo());
		customErrorModel.setError(customInternalServerErrorException.getError());

		exception.printStackTrace();
		return new ResponseEntity<CustomErrorModel>(customErrorModel,
				customInternalServerErrorException.getHttpStatus());
	}
}