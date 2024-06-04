package br.com.WellDone.Wink.Resources;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.http.HttpStatus.CREATED;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.WellDone.Wink.Model.Entity.Usuario;
import br.com.WellDone.Wink.Model.Repository.UsuarioR;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping(value = "/index_usuario")
public class RepU {

    @Autowired
    private UsuarioR usuarioRepository;
    
   // @Operation(description = "Este serviço retorna todos os Usuarios", summary = "Retorna todos os Usuarios", tags = "Consulta")
    @GetMapping(value = "/todos")
    public List<Usuario> PesquisarUsuarios() {
        List<Usuario> lista = usuarioRepository.findAll();
        for (Usuario i : lista) {
            i.add(linkTo(methodOn(RepU.class).findById(i.getId_usuario()))
                    .withRel("deseja ver as informações deste usuario? Acesse este link: "));
        }
        return lista;
    } 

   // @Operation(description = "Este serviço retorna Usuarios por id", summary = "Retorna Usuarios por id", tags = "Consulta")
    @GetMapping(value = "/{id_usuario}")
    public ResponseEntity<Usuario> findById(@PathVariable Long id_usuario) {
        Optional<Usuario> usuario = usuarioRepository.findById(id_usuario);

        if (usuario.isPresent()) {
            Usuario u1 = usuario.get();
            u1.add(linkTo(methodOn(RepU.class).PesquisarUsuarios())
                    .withRel("deseja consultar todos Usuarios? Acesse este link: "));
            u1.add(linkTo(methodOn(RepU.class).inserirUsuario(null))
                    .withRel("deseja inserir um usuario? Acesse este link: "));
            u1.add(linkTo(methodOn(RepU.class).atualizarUsuario(id_usuario, null))
                    .withRel("deseja atualizar um usuario? Acesse este link: "));
            u1.add(linkTo(methodOn(RepU.class).apagarUsuario(id_usuario))
                    .withRel("deseja deletar um usuario? Acesse este link: "));
            return ResponseEntity.ok(u1);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

   // @Operation(description = "Este serviço insere Usuarios", summary = "Inerção de usuarios", tags = "Inserção")
    @ResponseStatus(CREATED)
    @PostMapping
    public ResponseEntity<Usuario> inserirUsuario(@RequestBody Usuario usuario) {
        Usuario savedUsuario = usuarioRepository.save(usuario);
        return ResponseEntity.status(CREATED).body(savedUsuario);
    }

   // @Operation(description = "Este serviço remove Usuarios", summary = "Remoção de usuarios", tags = "Remoção")
    @DeleteMapping(value = "/remove_usuario/{id_usuario}")
    public Usuario apagarUsuario(@PathVariable Long id_usuario) {

		Usuario u1 = usuarioRepository.findById(id_usuario).get();
		usuarioRepository.deleteById(id_usuario);
		return u1;

	}

   // @Operation(description = "Este serviço atualiza Usuarios", summary = "Atualização de usuarios", tags = "Atualização")
    @PutMapping(value = "/atualiza_usuario/{id_usuario}")
    @Transactional
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id_usuario, @RequestBody Usuario usuarioAtualizado) {
        return usuarioRepository.findById(id_usuario).map(usuario -> {
            usuario.setNome(usuarioAtualizado.getNome());
            usuario.setEmail(usuarioAtualizado.getEmail());
            usuario.setSenha(usuarioAtualizado.getSenha());
            Usuario updatedUsuario = usuarioRepository.save(usuario);
            return ResponseEntity.ok(updatedUsuario);
        }).orElse(ResponseEntity.notFound().build());
    }
}
