package comNerdChip.NerdChip.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import comNerdChip.NerdChip.dtos.UsuarioDTO;
import comNerdChip.NerdChip.models.Produto;
import comNerdChip.NerdChip.models.Usuario;
import comNerdChip.NerdChip.repositories.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    public List<UsuarioDTO> listarTodos() {
        List<Usuario> lista = usuarioRepository.findAll();
        return lista.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    
    }
    public UsuarioDTO buscarPorId(Long id) { // Changed parameter type from Produto to Usuario
        Produto usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return toDTO(usuario);
    }
    public UsuarioDTO criar(Usuario usuario) {
        Usuario novoUsuario = usuarioRepository.saveAll(usuario);
        return toDTO(novoUsuario);
    }
    public  UsuarioDTO atualizar(Long id, Usuario usuario) {
        usuario.setId(id); // Changed parameter type from S to Usuario
        Usuario usuarioAtualizado = usuarioRepository.save(usuario);
        return toDTO(usuarioAtualizado);
    }

    // Deletar usuário
    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }

    // Conversão para DTO
    private UsuarioDTO toDTO(Usuario usuario) {
                return new UsuarioDTO(
                        usuario.getId(),
                        usuario.getNome(),
                        usuario.getEmail(),
                        usuario.getSenha(),
                        usuario.getTelefone(),
                        usuario.getEndereco()
        );
    }

    // Conversão para entidade (se precisar)
    private Usuario toEntity(UsuarioDTO usuarioDTO) {
        return new Usuario(
                usuarioDTO.getId(),
                usuarioDTO.getNome(),
                usuarioDTO.getEmail(),
                usuarioDTO.getSenha(),
                usuarioDTO.getTelefone(),
                usuarioDTO.getEndereco()
        );
    }
}
