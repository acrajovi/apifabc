package py.com.bio.apifabc.utilities;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class NoticiasUtils {

	/**
	 * Método que una lista de artículos en formato {Elements} de las noticias en
	 * base a un {Document} pasado por parámetro
	 * 
	 * @author jose.acosta
	 * @param element
	 * @return
	 */
	public static Elements getArticulosNoticia(Document document) {
		return document.select("div.item-article");
	}

	/**
	 * Método que retorna la fecha de la noticia según el Element pasado por
	 * parámetro
	 * 
	 * @author jose.acosta
	 * @param element
	 * @return
	 */
	public static String getFechaNoticia(Element element) {
		return element.selectFirst("div.article-info").selectFirst("div.article-time").selectFirst("span").text();
	}

	/**
	 * Método que retorna el enlace de la noticia según el Element pasado por
	 * parámetro
	 * 
	 * @author jose.acosta
	 * @param element
	 * @return
	 */
	public static String getEnlaceNoticia(Element element) {
		return element.selectFirst("div.article-info").selectFirst("a").attr("href");
	}

	/**
	 * Método que retorna el enlace de la Foto de la noticia según el Element pasado
	 * por parámetro
	 * 
	 * @author jose.acosta
	 * @param element
	 * @return
	 */
	public static String getEnlaceFotoNoticia(Element element) {
		return element.selectFirst("div.article-photo").selectFirst("img").attr("src");
	}

	/**
	 * Método que retorna el Título de la noticia según el Element pasado por
	 * parámetro
	 * 
	 * @author jose.acosta
	 * @param element
	 * @return
	 */
	public static String getTituloNoticia(Element element) {
		return element.selectFirst("div.article-info").selectFirst("div.article-title").selectFirst("span").text();
	}

	/**
	 * Método que retorna el resumen de la noticia según el Element pasado por
	 * parámetro
	 * 
	 * @author jose.acosta
	 * @param element
	 * @return
	 */
	public static String getResumenNoticia(Element element) {
		return element.selectFirst("div.article-info").selectFirst("div.article-intro").selectFirst("p").text();
	}

}