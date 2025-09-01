package comNerdChip.NerdChip.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import comNerdChip.NerdChip.dtos.ProdutoDTO;
import comNerdChip.NerdChip.models.Produto;
import comNerdChip.NerdChip.repositories.ProdutoRepository;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public List<ProdutoDTO> listarTodos() {
        return produtoRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ProdutoDTO buscarPorId(Long id) {
        Produto produto = produtoRepository.findById(id).orElseThrow();
        return toDTO(produto);
    }

    public ProdutoDTO criar(ProdutoDTO dto) {
        Produto produto = toEntity(dto);
        produtoRepository.save(produto);
        return toDTO(produto);
    }

    public ProdutoDTO atualizar(Long id, ProdutoDTO dto) {
        Produto produto = toEntity(dto);
        produto.setId(id);
        produtoRepository.save(produto);
        return toDTO(produto);
    }

    public void deletar(Long id) {
        produtoRepository.deleteById(id);
    }

    private ProdutoDTO toDTO(Produto produto) {
        return new ProdutoDTO(produto.getId(), produto.getNome(), produto.getDescricao(), produto.getPreco(), null);
    }

    private Produto toEntity(ProdutoDTO dto) {
        return new Produto(dto.getId(), dto.getNome(), dto.getDescricao(), dto.getPreco(), null);
    }
}