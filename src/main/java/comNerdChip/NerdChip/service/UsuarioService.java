package comNerdChip.NerdChip.service;

import java.util.List;

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

import comNerdChip.NerdChip.dtos.UsuarioDTO;
import comNerdChip.NerdChip.repositories.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired UsuarioRepository usuarioRepository;
    
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listarTodos(){
        return listarTodos();
    }
    @GetMapping
    public ResponseEntity<UsuarioDTO> bucarPorId(@PathVariable loong id){
       return bucarPorId(id); 
    }
    @PostMapping
    public ResponseEntity<UsuarioDTO> criar(@Validated @RequestBody UsuarioDTO usuarioDTO){
        UsuarioDTO novoUsuario = UsuarioRepository.criar(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> atualizar(
        @PathVariable long id,
        @Validated @RequestBody UsuarioDTO usuarioDTO) {
    
    UsuarioDTO usuarioAtualizado = usuarioService.atualizar(id, usuarioDTO);
    return ResponseEntity.ok(usuarioAtualizado);
}
    @DeleteMapping("/{id}")
    ResponseEntity<Object> deltetar(@PathVariable long id){
        return ResponseEntity.noContent().build();
    }

    
}
