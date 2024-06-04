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

import br.com.WellDone.Wink.Model.Entity.Denuncia;
import br.com.WellDone.Wink.Model.Repository.DenunciaR;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping(value = "index_denuncia")

public class RepD {

	@Autowired
	private DenunciaR denunciaRepository;
	
	// @Operation(description = "Este serviço retorna todas as Denuncias", summary = "Retorna todas Denuncias", tags = "Consulta")
    @GetMapping(value = "/todos")
    public List<Denuncia> PesquisarDenuncia() {
        List<Denuncia> lista = denunciaRepository.findAll();
        for (Denuncia i : lista) {
            i.add(linkTo(methodOn(RepD.class).findById(i.getId_denuncia()))
                    .withRel("deseja ver as informações desta Denuncia? Acesse este link: "));
        }
        return lista;
    } 
    
 // @Operation(description = "Este serviço retorna Denuncias por id", summary = "Retorna Denuncias por id", tags = "Consulta")
    @GetMapping(value = "/{id_denuncia}")
    public ResponseEntity<Denuncia> findById(@PathVariable Long id_denuncia) {
        Optional<Denuncia> denuncia = denunciaRepository.findById(id_denuncia);

        if (denuncia.isPresent()) {
        	Denuncia d1 = denuncia.get();
        	d1.add(linkTo(methodOn(RepD.class).PesquisarDenuncia())
                    .withRel("deseja consultar todas Denuncia? Acesse este link: "));
        	d1.add(linkTo(methodOn(RepD.class).inserirDenuncia(null))
                    .withRel("deseja inserir uma Denuncia? Acesse este link: "));
        	d1.add(linkTo(methodOn(RepD.class).atualizarDenuncia(id_denuncia, null))
                    .withRel("deseja atualizar uma Denuncia? Acesse este link: "));
        	d1.add(linkTo(methodOn(RepD.class).apagarDenuncia(id_denuncia))
                    .withRel("deseja deletar uma Denuncia? Acesse este link: "));
            return ResponseEntity.ok(d1);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
 // @Operation(description = "Este serviço insere Feedbacks", summary = "Inerção de Feedbacks", tags = "Inserção")
    @ResponseStatus(CREATED)
    @PostMapping
    public ResponseEntity<Denuncia> inserirDenuncia(@RequestBody Denuncia denuncia) {
    	Denuncia saveDenuncia = denunciaRepository.save(denuncia);
        return ResponseEntity.status(CREATED).body(saveDenuncia);
    }

   // @Operation(description = "Este serviço remove Denuncias", summary = "Remoção de Denuncias", tags = "Remoção")
    @DeleteMapping(value = "/remove_denuncia/{id_denuncia}")
    public Denuncia apagarDenuncia(@PathVariable Long id_denuncia) {

    	Denuncia d1 = denunciaRepository.findById(id_denuncia).get();
		denunciaRepository.deleteById(id_denuncia);
		return d1;

	}
    
    // @Operation(description = "Este serviço atualiza Denuncias", summary = "Atualização de Denuncias", tags = "Atualização")
    @PutMapping(value = "/atualiza_denuncia/{id_denuncia}")
    @Transactional
    public ResponseEntity<Denuncia> atualizarDenuncia(@PathVariable Long id_denuncia, @RequestBody Denuncia denunciaAtualizado) {
        return denunciaRepository.findById(id_denuncia).map(denuncia -> {
        	denuncia.setTipo_denuncia(denunciaAtualizado.getTipo_denuncia());
            denuncia.setDescricao(denunciaAtualizado.getDescricao()); 
            denuncia.setLocalizacao(denunciaAtualizado.getLocalizacao());       
            Denuncia updateDenuncia = denunciaRepository.save(denuncia);
            return ResponseEntity.ok(updateDenuncia);
        }).orElse(ResponseEntity.notFound().build());
    }
 
}
