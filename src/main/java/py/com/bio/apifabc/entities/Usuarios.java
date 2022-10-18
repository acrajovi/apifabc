package py.com.bio.apifabc.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Usuarios implements Serializable {

	private static final long serialVersionUID = 8870816084422515801L;

	@Id
	private String id;
	private String nombre;
	private String password;
	private String passwordHashType;
}
