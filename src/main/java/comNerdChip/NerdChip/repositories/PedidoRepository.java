package comNerdChip.NerdChip.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import comNerdChip.NerdChip.models.Pedido;
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
