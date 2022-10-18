package py.com.bio.apifabc.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatesUtilis {

	private SimpleDateFormat dateFormat = null;

	public DatesUtilis() {
		dateFormat = new SimpleDateFormat();
	}

	/**
	 * Método que recibe una fecha en formato {String} y lo devuelve en formato
	 * {String} según un String[] de patterns de formatos posibles
	 * 
	 * @author jose.acosta
	 * @param sDate
	 * @param patterns
	 * @return
	 */
	public Date getDateParse(String sDate, String... patterns) {

		Date dDateFormat = null;

		for (String pattern : patterns) {
			dateFormat.applyPattern(pattern);
			try {
				dDateFormat = dateFormat.parse(sDate);
				return dDateFormat;
			} catch (ParseException e) {

			}
		}

		return dDateFormat;
	}
}