package py.com.bio.apifabc.config.errors.customs;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import py.com.bio.apifabc.enums.EcodigosErrores;
import py.com.bio.apifabc.utilities.Constants;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationEntryPoint {

	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			AuthenticationException e) throws IOException, ServletException {

		httpServletResponse.setContentType(Constants.APPLICATION_TYPE_JSON);
		httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

		Map<String, Object> data = new HashMap<>();
		data.put(Constants.STRING_CODIGO, EcodigosErrores.ERROR_UNAUTHORIZED.getCodigoError());
		data.put(Constants.STRING_ERROR, EcodigosErrores.ERROR_UNAUTHORIZED.getMensajeError());

		httpServletResponse.getOutputStream().println(objectMapper.writeValueAsString(data));

	}
}