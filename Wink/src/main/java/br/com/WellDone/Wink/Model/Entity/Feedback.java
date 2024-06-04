package br.com.WellDone.Wink.Model.Entity;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "feedback")
public class Feedback extends RepresentationModel<Feedback>{

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id_feedback;
	
	@ManyToOne
    @JoinColumn(name = "denuncia_id_cliente") 
	private Usuario id_usuario;
	
	
	private String comentario;
	
	private String avaliacao;
}
