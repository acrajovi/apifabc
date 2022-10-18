package py.com.bio.apifabc.config;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import py.com.bio.apifabc.entities.Usuarios;
import py.com.bio.apifabc.repository.UsuariosRepository;
import py.com.bio.apifabc.utilities.Constants;
import py.com.bio.apifabc.utilities.SecurityUtil;
import py.com.bio.apifabc.utilities.Util;


@Component
public class AppInitializer {

	@Autowired
	private UsuariosRepository usuariosRepository;

	@PostConstruct
	private void init() throws InvalidKeyException, NoSuchAlgorithmException {
		System.out.println("");
		System.out.println(" _ I N I C I A M O S _ A P I _ ");

		Usuarios usuario = new Usuarios();
		usuario.setId(Constants.APIFABC_USER_ID);
		usuario.setNombre(Constants.APIFABC_USER_NAME);
		usuario.setPassword(SecurityUtil.getHmac256Signed(Constants.APIFABC_PASSWORD, Constants.API_KEY_SIGNATURE));
		usuario.setPasswordHashType(Constants.HMAC_SHA256);

		Usuarios usuarioSaved = usuariosRepository.save(usuario);

		System.out.println("Datos de Acceso:");
		System.out.println(Util.getJsonFromObject(usuarioSaved));
		System.out.println(" _ A P I _ I N I C I A D A _ ");
		System.out.println("");
	}

}
