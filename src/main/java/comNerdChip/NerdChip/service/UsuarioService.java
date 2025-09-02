package comNerdChip.NerdChip.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import comNerdChip.NerdChip.dtos.UsuarioDTO;
import comNerdChip.NerdChip.models.Usuario;
import comNerdChip.NerdChip.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public List<UsuarioDTO> listarTodos() {
        return usuarioRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
    public UsuarioDTO buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow();
        return toDTO(usuario);
    }

    public UsuarioDTO criar(UsuarioDTO dto) {
        Usuario usuario = toEntity(dto);
        Usuario novoUsuario = usuarioRepository.save(usuario);
        return toDTO(novoUsuario);
    }
    public UsuarioDTO atualizar(Long id, UsuarioDTO dto) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com id: " + id));

        usuarioExistente.setNome(dto.getNome());
        usuarioExistente.setEmail(dto.getEmail());
        usuarioExistente.setSenha(dto.getSenha());
        usuarioExistente.setTelefone(dto.getTelefone());
        usuarioExistente.setEndereco(dto.getEndereco());

        Usuario atualizado = usuarioRepository.save(usuarioExistente);
        return toDTO(atualizado);
    }

    public void deletar(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado com id: " + id);
        }
        usuarioRepository.deleteById(id);
    }

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

    private Usuario toEntity(UsuarioDTO dto) {
        return new Usuario(
                dto.getId(),
                dto.getNome(),
                dto.getEmail(),
                dto.getSenha(),
                dto.getTelefone(),
                dto.getEndereco()
        );
    }
}
