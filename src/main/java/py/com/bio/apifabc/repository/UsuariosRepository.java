package py.com.bio.apifabc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import py.com.bio.apifabc.entities.Usuarios;

@Repository
@Transactional(readOnly = true)
public interface UsuariosRepository extends JpaRepository<Usuarios, String> {

	Optional<Usuarios> findByPassword(String password);

	Optional<Usuarios> findByIdAndPassword(String id, String password);
}
