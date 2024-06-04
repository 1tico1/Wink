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

import br.com.WellDone.Wink.Model.Entity.Feedback;
import br.com.WellDone.Wink.Model.Repository.FeedbackR;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping(value = "index_feedback")
public class RepF {

	@Autowired
    private FeedbackR feedbackRepository;
	
	// @Operation(description = "Este serviço retorna todos os Feedbacks", summary = "Retorna todos os Feedbacks", tags = "Consulta")
    @GetMapping(value = "/todos")
    public List<Feedback> PesquisarFeedback() {
        List<Feedback> lista = feedbackRepository.findAll();
        for (Feedback i : lista) {
            i.add(linkTo(methodOn(RepF.class).findById(i.getId_feedback()))
                    .withRel("deseja consultar a pessoa por ID? Acesse este link: "));
        }
        return lista;
    } 
    
 // @Operation(description = "Este serviço retorna Feedbacks por id", summary = "Retorna Feedbacks por id", tags = "Consulta")
    @GetMapping(value = "/{id_feedback}")
    public ResponseEntity<Feedback> findById(@PathVariable Long id_feedback) {
        Optional<Feedback> feedback = feedbackRepository.findById(id_feedback);

        if (feedback.isPresent()) {
        	Feedback feed = feedback.get();
        	feed.add(linkTo(methodOn(RepF.class).PesquisarFeedback())
                    .withRel("deseja consultar todos Usuarios? Acesse este link: "));
        	feed.add(linkTo(methodOn(RepF.class).inserirFeedback(null))
                    .withRel("deseja inserir um cliente? Acesse este link: "));
        	feed.add(linkTo(methodOn(RepF.class).atualizarFeedback(id_feedback, null))
                    .withRel("deseja atualizar um cliente? Acesse este link: "));
        	feed.add(linkTo(methodOn(RepF.class).apagarFeedback(id_feedback))
                    .withRel("deseja deletar um cliente? Acesse este link: "));
            return ResponseEntity.ok(feed);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
 // @Operation(description = "Este serviço insere Feedbacks", summary = "Inerção de Feedbacks", tags = "Inserção")
    @ResponseStatus(CREATED)
    @PostMapping
    public ResponseEntity<Feedback> inserirFeedback(@RequestBody Feedback feedback) {
    	Feedback saveFeedback = feedbackRepository.save(feedback);
        return ResponseEntity.status(CREATED).body(saveFeedback);
    }

   // @Operation(description = "Este serviço remove Feedbacks", summary = "Remoção de Feedbacks", tags = "Remoção")
    @DeleteMapping(value = "/remove_feedback/{id_feedback}")
    public Feedback apagarFeedback(@PathVariable Long id_feedback) {

		Feedback f1 = feedbackRepository.findById(id_feedback).get();
		feedbackRepository.deleteById(id_feedback);
		return f1;

	}
    
    // @Operation(description = "Este serviço atualiza Usuarios", summary = "Atualização de usuarios", tags = "Atualização")
    @PutMapping(value = "/atualiza_feedback/{id_feedback}")
    @Transactional
    public ResponseEntity<Feedback> atualizarFeedback(@PathVariable Long id_feedback, @RequestBody Feedback feedbackAtualizado) {
        return feedbackRepository.findById(id_feedback).map(feedback -> {
        	feedback.setComentario(feedbackAtualizado.getComentario());
            feedback.setAvaliacao(feedbackAtualizado.getAvaliacao());       
            Feedback updateFeedback = feedbackRepository.save(feedback);
            return ResponseEntity.ok(updateFeedback);
        }).orElse(ResponseEntity.notFound().build());
    }
 
    
    
    
}
