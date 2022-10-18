package py.com.bio.apifabc.config.errors.customs;

import java.io.Serializable;

import lombok.Data;

@Data
public class CustomErrorModel implements Serializable {

	private static final long serialVersionUID = 5776175647427299409L;

	private String codigo;
	private String error;
}
