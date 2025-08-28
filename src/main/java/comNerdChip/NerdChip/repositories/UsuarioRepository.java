package comNerdChip.NerdChip.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import comNerdChip.NerdChip.models.Produto;
@Repository

public interface UsuarioRepository<Usuario> extends JpaRepository<Produto, Long> {
    Optional<Produto> findByDescricao(String descricao);

}