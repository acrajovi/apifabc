package py.com.bio.apifabc.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import py.com.bio.apifabc.services.NoticiaServicesImpl;
import py.com.bio.apifabc.utilities.Constants;

@RestController
@RequestMapping("/api")
public class MainController {

	@Autowired
	private NoticiaServicesImpl noticiaServices;

	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE,
			MediaType.TEXT_PLAIN_VALUE, MediaType.TEXT_HTML_VALUE })
	@RequestMapping("/consulta")
	public ResponseEntity<?> getNoticias(@RequestParam("q") Optional<String> strSearch,
			@RequestParam("f") Optional<String> showPicture) throws Exception {

		// Si el parámetro f = true, retornamos lista de noticias con fotos en Base64
		if (showPicture.isPresent() && showPicture.get().equals(Constants.STRING_TRUE)) {
			return noticiaServices.getNoticiasWithPhotos(strSearch);
		} else { // Si no, retornamos las noticas sin fotos
			return noticiaServices.getNoticiasWithoutPhotos(strSearch);
		}
	}
}