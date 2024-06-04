package br.com.WellDone.Wink.Model.Entity;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="usuario")
public class Usuario extends RepresentationModel<Usuario>{
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id_usuario;
	
    @Column(name = "nome", nullable = false, length = 255)
	private String nome;

    @Column(name = "email", length = 255)
    private String email;
    
    @Column(name = "senha", length = 255)
    private String senha;

}
