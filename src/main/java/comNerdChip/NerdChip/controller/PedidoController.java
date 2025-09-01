package comNerdChip.NerdChip.controller;

import comNerdChip.NerdChip.dtos.PedidoDTO;
import comNerdChip.NerdChip.models.Pedido;
import comNerdChip.NerdChip.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    private final PedidoService pedidoService;

    @Autowired
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }
    @GetMapping
    public ResponseEntity<List<PedidoDTO>> listarTodos() {
        List<PedidoDTO> lista = pedidoService.listarTodos();
        return ResponseEntity.ok(lista);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseEntity<PedidoDTO>> buscarPorId(@PathVariable Long id) {
        ResponseEntity<PedidoDTO> pedido = pedidoService.buscarPorId(id);
        return ResponseEntity.ok(pedido);
    }
    @PostMapping
    public ResponseEntity<ResponseEntity<PedidoDTO>> criar(@RequestBody Pedido pedido) {
        ResponseEntity<PedidoDTO> novoPedido = pedidoService.criar(pedido);
        return ResponseEntity.ok(novoPedido);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ResponseEntity<PedidoDTO>> atualizar(@PathVariable Long id, @RequestBody Pedido pedido) {
        ResponseEntity<PedidoDTO> pedidoAtualizado = pedidoService.atualizar(id, pedido);
        return ResponseEntity.ok(pedidoAtualizado);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        pedidoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}