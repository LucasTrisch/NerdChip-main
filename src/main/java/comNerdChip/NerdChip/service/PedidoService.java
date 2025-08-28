package comNerdChip.NerdChip.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import comNerdChip.NerdChip.dtos.PedidoDTO;
import comNerdChip.NerdChip.models.Pedido;
import comNerdChip.NerdChip.repositories.PedidoRepository;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;
   public List<PedidoDTO> listarTodos() {
        List<Pedido> lista = pedidoRepository.findAll();
        return lista.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
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
    private Pedido toEntity(PedidoDTO pedidoDTO) {
        return new Pedido(
                pedidoDTO.getId(),
                pedidoDTO.getNumero(),
                pedidoDTO.getPreco(),
                pedidoDTO.getLista(),
                pedidoDTO.getPagamento()
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> buscarPorId(@PathVariable Long id) {
     Pedido pedido = pedidoRepository.findById(id).orElseThrow();
     PedidoDTO pedidoDTO = toDTO(pedido);
     return ResponseEntity.ok(pedidoDTO);
    }
    @PostMapping
    public ResponseEntity<PedidoDTO> criar(@Validated @RequestBody PedidoDTO pedidoDTO) {
        Pedido pedido = toEntity(pedidoDTO);
        Pedido novoPedido = pedidoRepository.save(pedido);
        pedidoDTO = toDTO(novoPedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoDTO);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PedidoDTO> atualizar(@PathVariable Long id, @Validated @RequestBody PedidoDTO pedidoDTO) {
        Pedido pedido = toEntity(pedidoDTO);
        pedido.setId(id);
        Pedido pedidoAtualizado = pedidoRepository.save(pedido);
        pedidoDTO = toDTO(pedidoAtualizado);
        return ResponseEntity.ok(pedidoDTO);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        pedidoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
