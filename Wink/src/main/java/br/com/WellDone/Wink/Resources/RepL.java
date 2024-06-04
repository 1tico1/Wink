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
import br.com.WellDone.Wink.Model.Entity.Logs_Erros;
import br.com.WellDone.Wink.Model.Repository.LogsR;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping(value= "index_logs")
public class RepL {

	@Autowired
	private LogsR logsRepository;
	
	// @Operation(description = "Este serviço retorna todos os Logs de Erros", summary = "Retorna todos os Logs de Erros", tags = "Consulta")
    @GetMapping(value = "/todos")
    public List<Logs_Erros> PesquisarLogs() {
        List<Logs_Erros> lista = logsRepository.findAll();
        for (Logs_Erros i : lista) {
            i.add(linkTo(methodOn(RepD.class).findById(i.getId_log()))
                    .withRel("deseja ver as informações desta Denuncia? Acesse este link: "));
        }
        return lista;
    } 
    
 // @Operation(description = "Este serviço retorna Logs por id", summary = "Retorna Logs por id", tags = "Consulta")
    @GetMapping(value = "/{id_log}")
    public ResponseEntity<Logs_Erros> findById(@PathVariable Long id_log) {
        Optional<Logs_Erros> logs_erros = logsRepository.findById(id_log);

        if (logs_erros.isPresent()) {
        	Logs_Erros l1 = logs_erros.get();
        	l1.add(linkTo(methodOn(RepL.class).PesquisarLogs())
                    .withRel("deseja consultar todas Denuncia? Acesse este link: "));
        	l1.add(linkTo(methodOn(RepL.class).inserirLogs(null))
                    .withRel("deseja inserir uma Denuncia? Acesse este link: "));
        	l1.add(linkTo(methodOn(RepL.class).atualizarLogs(id_log, null))
                    .withRel("deseja atualizar uma Denuncia? Acesse este link: "));
        	l1.add(linkTo(methodOn(RepL.class).apagarLogs(id_log))
                    .withRel("deseja deletar uma Denuncia? Acesse este link: "));
            return ResponseEntity.ok(l1);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    
    
    
 // @Operation(description = "Este serviço insere os Logs", summary = "Inerção de Logs", tags = "Inserção")
    @ResponseStatus(CREATED)
    @PostMapping
    public ResponseEntity<Logs_Erros> inserirLogs(@RequestBody Logs_Erros logs_erros) {
    	Logs_Erros saveLogs_Erros = logsRepository.save(logs_erros);
        return ResponseEntity.status(CREATED).body(saveLogs_Erros);
    }

   // @Operation(description = "Este serviço remove Logs", summary = "Remoção de Logs", tags = "Remoção")
    @DeleteMapping(value = "/remove_logs/{id_log}")
    public Logs_Erros apagarLogs(@PathVariable Long id_log) {

    	Logs_Erros l1 = logsRepository.findById(id_log).get();
		logsRepository.deleteById(id_log);
		return l1;

	}
    
 // @Operation(description = "Este serviço atualiza Logs", summary = "Atualização de Logs", tags = "Atualização")
    @PutMapping(value = "/atualiza_logs/{id_log}")
    @Transactional
    public ResponseEntity<Logs_Erros> atualizarLogs(@PathVariable Long id_log, @RequestBody Logs_Erros logsAtualizado) {
        return logsRepository.findById(id_log).map(logs_erros -> {
        	logs_erros.setNome_proc(logsAtualizado.getNome_proc());
        	logs_erros.setNome_usuario(logsAtualizado.getNome_usuario()); 
        	logs_erros.setData(logsAtualizado.getData());   
        	logs_erros.setCodigo_erro(logsAtualizado.getCodigo_erro());  
        	logs_erros.setMsg_erro(logsAtualizado.getMsg_erro());   
            Logs_Erros updateLogs_Erros = logsRepository.save(logs_erros); 
            return ResponseEntity.ok(updateLogs_Erros);
        }).orElse(ResponseEntity.notFound().build());
    }
}
