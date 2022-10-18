package py.com.bio.apifabc.payloads;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class NoticiaWithPhoto extends Noticia implements Serializable {

	private static final long serialVersionUID = 7604820520191598141L;

	private String contenido_foto;
	private String content_type_foto;
}
