package py.com.bio.apifabc.utilities;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

public class SecurityUtil {

	/**
	 * Método que firma Digitalmente en HMAC_SHA256 un String pasado por parámetro,
	 * con una clave privada
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 */
	public static String getHmac256Signed(String data, String key)
			throws NoSuchAlgorithmException, InvalidKeyException {
		SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), Constants.HMAC_SHA256);
		Mac mac = Mac.getInstance(Constants.HMAC_SHA256);
		mac.init(secretKeySpec);
		return Hex.encodeHexString(mac.doFinal(data.getBytes()));
	}

}
