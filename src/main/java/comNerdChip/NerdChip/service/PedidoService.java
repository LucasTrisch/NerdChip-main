package comNerdChip.NerdChip.service;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import comNerdChip.NerdChip.dtos.PedidoDTO;
import comNerdChip.NerdChip.models.Pedido;
import comNerdChip.NerdChip.repositories.PedidoRepository;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    public List<PedidoDTO> listarTodos() {
        return pedidoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public PedidoDTO buscarPorId(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado com id: " + id));
        return toDTO(pedido);
    }

     public PedidoDTO criar(PedidoDTO dto) {
        Pedido pedido = toEntity(dto);
        pedidoRepository.save(pedido);
        return toDTO(pedido);
    }
    
    public PedidoDTO atualizar(Long id, PedidoDTO dto) {
        Pedido produto = toEntity(dto);
        produto.setId(id);
        pedidoRepository.save(produto);
        return toDTO(produto);
    }

    public void deletar(Long id) {
        if (!pedidoRepository.existsById(id)) {
            throw new RuntimeException("Pedido não encontrado com id: " + id);
        }
        pedidoRepository.deleteById(id);
    }

    private PedidoDTO toDTO(Pedido pedido) {
        return new PedidoDTO(
                pedido.getId(),
                pedido.getNumero(),
                pedido.getPreco(),
                pedido.getLista(),
                pedido.getPagamento()
        );
    }

    private Pedido toEntity(PedidoDTO dto) {
        return new Pedido(
                dto.getId(),
                dto.getNumero(),
                dto.getPreco(),
                dto.getLista(),
                dto.getPagamento()
        );
    }
}