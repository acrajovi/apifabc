package py.com.bio.apifabc.services;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

public interface NoticiasService {

	/**
	 * Servicio que retorna un {JSON} de las noticias (Sin Fotos) consultando la
	 * página de ABC DIGITAL y haciendo Web Scraping del mismo para obtener los
	 * datos
	 * 
	 * @param strSearch {String}
	 * @returnm ResponseEntity<?>
	 * @throws Exception
	 */
	public ResponseEntity<?> getNoticiasWithoutPhotos(Optional<String> strSearch) throws Exception;

	/**
	 * Servicio que retorna un {JSON} de las noticias (Con Fotos) consultando la
	 * página de ABC DIGITAL y haciendo Web Scraping del mismo para obtener los
	 * datos
	 * 
	 * @param strSearch {String}
	 * @returnm ResponseEntity<?>
	 * @throws Exception
	 */
	public ResponseEntity<?> getNoticiasWithPhotos(Optional<String> strSearch) throws Exception;
}