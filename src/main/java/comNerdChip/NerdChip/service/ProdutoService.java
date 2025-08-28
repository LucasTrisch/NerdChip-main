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

import comNerdChip.NerdChip.dtos.ProdutoDTO;
import comNerdChip.NerdChip.models.Produto;
import comNerdChip.NerdChip.repositories.ProdutoRepository;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ProdutoService  {
    @Autowired
    private ProdutoRepository produtoRepository;
   public List<ProdutoDTO> listarTodos() {
        List<Produto> lista = produtoRepository.findAll();
        return lista.stream()
                .map(this::toDTO) // converte cada Produto para ProdutoDTO
                .collect(Collectors.toList());
    }

    // ===== Conversão de Entidade → DTO =====
    private ProdutoDTO toDTO(Produto produto) {
        return new ProdutoDTO(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getDescricao()
        );
    }
        // ===== Conversão de DTO → ENTIDADE =====
        private Produto toEntity(ProdutoDTO produtoDTO) {
            return new Produto(
                    produtoDTO.getId(),
                    produtoDTO.getNome(),
                    produtoDTO.getDescricao(),
                    produtoDTO.getPreco(),
                    produtoDTO.getDescricao()
            );
        }
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> buscarPorId(@PathVariable Long id) {
     Produto produto = produtoRepository.findById(id).orElseThrow();
     ProdutoDTO produtoDTO = toDTO(produto);
     return ResponseEntity.ok(produtoDTO);
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> criar(@Validated @RequestBody ProdutoDTO produtoDTO) {
        Produto produto = toEntity(produtoDTO);
        Produto novoProduto = produtoRepository.save(produto);
        produtoDTO = toDTO(novoProduto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> atualizar(@PathVariable Long id, @Validated @RequestBody ProdutoDTO produtoDTO) {
        Produto produto = toEntity(produtoDTO);
        produto.setId(id);
        Produto produtoAtualizado = produtoRepository.save(produto);
        produtoDTO = toDTO(produtoAtualizado);
        return ResponseEntity.ok(produtoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        produtoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}