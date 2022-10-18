package py.com.bio.apifabc.services;

import java.util.ArrayList;
import java.util.Optional;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.LoadState;

import py.com.bio.apifabc.config.errors.customs.CustomInvalidQueryParametersException;
import py.com.bio.apifabc.config.errors.customs.CustomNotFoundException;
import py.com.bio.apifabc.payloads.Noticia;
import py.com.bio.apifabc.payloads.NoticiaWithPhoto;
import py.com.bio.apifabc.utilities.Constants;
import py.com.bio.apifabc.utilities.DatesUtilis;
import py.com.bio.apifabc.utilities.ImagesUtil;
import py.com.bio.apifabc.utilities.NoticiasUtils;
import py.com.bio.apifabc.utilities.Util;

@Service
public class NoticiaServicesImpl implements NoticiasService {

	@Override
	public ResponseEntity<?> getNoticiasWithoutPhotos(Optional<String> strSearch) throws Exception {

		// Si el parámetro es vacío o nulo
		if (strSearch.isEmpty() || Util.isNullOrEmpty(strSearch.get())) {
			throw new CustomInvalidQueryParametersException();
		}

		String url = Constants.URL_ABC + strSearch;

		Playwright playwright = Playwright.create();
		final BrowserType chromium = playwright.chromium();
		final Browser browser = chromium.launch();
		Page page = browser.newPage();
		page.navigate(url);
		page.waitForLoadState(LoadState.NETWORKIDLE);
		final ElementHandle contentElement = page.querySelector("[class=article-list-wrapper]");

		Document document = null;

		// si no hay noticias
		if (contentElement == null) {
			throw new CustomNotFoundException(strSearch.get());
		}

		document = Jsoup.parse(contentElement.innerHTML());
		Elements articulos = NoticiasUtils.getArticulosNoticia(document);

		ArrayList<Noticia> noticiaList = new ArrayList<>();

		DatesUtilis datesUtilis = new DatesUtilis();

		String fecha, enlace, enlaceFoto, titulo, resumen = "";

		System.out.println("Total de Noticias: " + articulos.size());
		System.out.println("________________________________\n");
		for (Element articulo : articulos) {

			Noticia noticia = new Noticia();

			fecha = NoticiasUtils.getFechaNoticia(articulo);
			System.out.println("FechaOrig: " + fecha);
			System.out.println(
					"FechaDate: " + datesUtilis.getDateParse(fecha, Constants.ARRAY_FORMATOS_POSIBLES_FECHAS_NOTICIAS));
			System.out.println("FechaDateISO: " + datesUtilis
					.getDateParse(fecha, Constants.ARRAY_FORMATOS_POSIBLES_FECHAS_NOTICIAS).toInstant().toString());
			noticia.setFecha(datesUtilis.getDateParse(fecha, Constants.ARRAY_FORMATOS_POSIBLES_FECHAS_NOTICIAS)
					.toInstant().toString());

			enlace = NoticiasUtils.getEnlaceNoticia(articulo);
			enlace = Constants.URL_ABC.substring(0, Constants.URL_ABC.length() - 1) + enlace;
			noticia.setEnlace(enlace);
			System.out.println("Enlace: " + enlace);

			enlaceFoto = NoticiasUtils.getEnlaceFotoNoticia(articulo);
			noticia.setEnlace_foto(enlaceFoto);
			System.out.println("EnlaceFoto: " + enlaceFoto);

			titulo = NoticiasUtils.getTituloNoticia(articulo);
			noticia.setTitulo(titulo);
			System.out.println("Título: " + titulo);

			resumen = NoticiasUtils.getResumenNoticia(articulo);
			noticia.setResumen(resumen);
			System.out.println("Resumen: " + resumen);

			noticiaList.add(noticia);
			System.out.println("Noticia: " + Util.getJsonFromObject(noticia));
			System.out.println("\n");
		}

		browser.close();

		return new ResponseEntity<>(noticiaList, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getNoticiasWithPhotos(Optional<String> strSearch) throws Exception {
		// Si el parámetro es vacío o nulo
		if (strSearch.isEmpty() || Util.isNullOrEmpty(strSearch.get())) {
			throw new CustomInvalidQueryParametersException();
		}

		String url = Constants.URL_ABC + strSearch;

		Playwright playwright = Playwright.create();
		final BrowserType chromium = playwright.chromium();
		final Browser browser = chromium.launch();
		Page page = browser.newPage();
		page.navigate(url);
		page.waitForLoadState(LoadState.NETWORKIDLE);
		final ElementHandle contentElement = page.querySelector("[class=article-list-wrapper]");

		Document document = null;

		// si no hay noticias
		if (contentElement == null) {
			throw new CustomNotFoundException(strSearch.get());
		}

		document = Jsoup.parse(contentElement.innerHTML());
		Elements articulos = NoticiasUtils.getArticulosNoticia(document);

		ArrayList<NoticiaWithPhoto> noticiaWithPhotosList = new ArrayList<>();

		DatesUtilis datesUtilis = new DatesUtilis();

		String fecha, enlace, enlaceFoto, titulo, resumen, contenido_foto, content_type_foto = "";

		System.out.println("Total de Noticias: " + articulos.size());
		System.out.println("________________________________\n");
		for (Element articulo : articulos) {

			NoticiaWithPhoto noticiaWithPhoto = new NoticiaWithPhoto();

			fecha = NoticiasUtils.getFechaNoticia(articulo);
			System.out.println("FechaOrig: " + fecha);
			System.out.println(
					"FechaDate: " + datesUtilis.getDateParse(fecha, Constants.ARRAY_FORMATOS_POSIBLES_FECHAS_NOTICIAS));
			System.out.println("FechaDateISO: " + datesUtilis
					.getDateParse(fecha, Constants.ARRAY_FORMATOS_POSIBLES_FECHAS_NOTICIAS).toInstant().toString());
			noticiaWithPhoto.setFecha(datesUtilis.getDateParse(fecha, Constants.ARRAY_FORMATOS_POSIBLES_FECHAS_NOTICIAS)
					.toInstant().toString());

			enlace = NoticiasUtils.getEnlaceNoticia(articulo);
			enlace = Constants.URL_ABC.substring(0, Constants.URL_ABC.length() - 1) + enlace;
			noticiaWithPhoto.setEnlace(enlace);
			System.out.println("Enlace: " + enlace);

			enlaceFoto = NoticiasUtils.getEnlaceFotoNoticia(articulo);
			noticiaWithPhoto.setEnlace_foto(enlaceFoto);
			System.out.println("EnlaceFoto: " + enlaceFoto);

			// Obtenemos la foto en Base64
			if (!Util.isNullOrEmpty(enlaceFoto)) {
				contenido_foto = ImagesUtil.getBase64EncodedImageFromURL(enlaceFoto);
				noticiaWithPhoto.setContenido_foto(contenido_foto);
				System.out.println("ContenidoFoto: " + contenido_foto);
				content_type_foto = ImagesUtil.getImageContentTypeFromURL(enlaceFoto);
				noticiaWithPhoto.setContent_type_foto(content_type_foto);
				System.out.println("ContentTypeFoto: " + content_type_foto);
			}

			titulo = NoticiasUtils.getTituloNoticia(articulo);
			noticiaWithPhoto.setTitulo(titulo);
			System.out.println("Título: " + titulo);

			resumen = NoticiasUtils.getResumenNoticia(articulo);
			noticiaWithPhoto.setResumen(resumen);
			System.out.println("Resumen: " + resumen);

			noticiaWithPhotosList.add(noticiaWithPhoto);
			System.out.println("Noticia: " + Util.getJsonFromObject(noticiaWithPhoto));
			System.out.println("\n");
		}

		browser.close();

		return new ResponseEntity<>(noticiaWithPhotosList, HttpStatus.OK);
	}
}