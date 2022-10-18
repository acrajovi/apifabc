package py.com.bio.apifabc.utilities;

import com.google.gson.Gson;

public class Util {

	private static Gson gson = new Gson();

	/**
	 * Método;todo que verifica si un objeto es nulo o vacío
	 *
	 * @param objeto
	 *
	 * @return {@code TRUE} si es nulo o vacio sino {@code FALSE}
	 *
	 * @author acrajovi
	 */
	public static boolean isNullOrEmpty(Object objeto) {
		return objeto == null || "".equals(objeto) ? Boolean.TRUE : Boolean.FALSE;
	}

	/**
	 * Método que obtiene un JSON a partir de un objeto proveído
	 * 
	 * @param objeto
	 * 
	 * @return {@code JSON}
	 * 
	 * @author jose.acosta
	 */
	public static String getJsonFromObject(Object objeto) {

		return gson.toJson(objeto);

	}

	/**
	 * Método que obtiene un JSON a partir de un objeto proveído
	 * 
	 * @param objeto
	 * 
	 * @author jose.acosta
	 * 
	 * @return {@code JSON}
	 */
	public static Object getObjectFromJson(String json, Class<?> objeto) {

		return gson.fromJson(json, objeto);

	}
}
