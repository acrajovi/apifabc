package py.com.bio.apifabc.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.codec.binary.Base64;

import py.com.bio.apifabc.config.errors.customs.CustomInternalServerErrorException;

public class ImagesUtil {

	/**
	 * Método que retorna una Base64.encodeBase64String(bytes) de los bytes de una
	 * imgagen según una URL pasada por parámetro
	 * 
	 * @param imageURL
	 * @return
	 * @author acrajovi
	 */

	public static String getBase64EncodedImageFromURL(String imageURL) {
		java.net.URL url = null;
		String imagenCodificadaBase64 = "";
		try {
			url = new java.net.URL(imageURL);
			InputStream is = url.openStream();
			byte[] bytes = org.apache.commons.io.IOUtils.toByteArray(is);
			imagenCodificadaBase64 = Base64.encodeBase64String(bytes);
		} catch (MalformedURLException e) {
			throw new CustomInternalServerErrorException();
		} catch (IOException e) {
			throw new CustomInternalServerErrorException();
		} catch (Exception e) {
			throw new CustomInternalServerErrorException();
		}

		return imagenCodificadaBase64;
	}

	/**
	 * Método que retorna el ContentType de una Imagen en base al URL de la imagen
	 * pasado por parámetro
	 * 
	 * @param imageURL
	 * @return
	 * @author acrajovi
	 */
	public static String getImageContentTypeFromURL(String imageURL) {
		URL url = null;
		String contentType = "";
		try {
			url = new URL(imageURL);
			URLConnection conn = url.openConnection();
			contentType = conn.getContentType();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			throw new CustomInternalServerErrorException();
		}
		return contentType;
	}

}