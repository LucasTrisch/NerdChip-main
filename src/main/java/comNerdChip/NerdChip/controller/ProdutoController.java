package comNerdChip.NerdChip.controller;



import comNerdChip.NerdChip.dtos.ProdutoDTO;
import comNerdChip.NerdChip.models.Produto;
import comNerdChip.NerdChip.repositories.PedidoRepository;
import comNerdChip.NerdChip.repositories.ProdutoRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private final repositories.ProdutoRepository produtoRepository;

    ProdutoController(repositories.ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }
    @Autowired
    
    @GetMapping
    public ResponseEntity<ResponseEntity<List<ProdutoDTO>>> listarTodos() {
        return ResponseEntity.ok(PedidoRepository.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> buscarPorId(@PathVariable Long id) {
        ProdutoDTO produto = produtoService.buscarPorId(id);
        return ResponseEntity.ok(produto);
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> criar(@RequestBody Produto produto) {
        ProdutoDTO novoProduto = produtoService.criar(produto);
        return ResponseEntity.ok(novoProduto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> atualizar(@PathVariable Long id, @RequestBody Produto produto) {
        ProdutoDTO produtoAtualizado = produtoService.atualizar(id, produto);
        return ResponseEntity.ok(produtoAtualizado);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        produtoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
