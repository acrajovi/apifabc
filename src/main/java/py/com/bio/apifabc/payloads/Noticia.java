package py.com.bio.apifabc.payloads;

import java.io.Serializable;

import lombok.Data;

@Data
public class Noticia implements Serializable {

	private static final long serialVersionUID = -6198251215129652301L;

	private String fecha;
	private String enlace;
	private String enlace_foto;
	private String titulo;
	private String resumen;

}