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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "logs_erros")
public class Logs_Erros extends RepresentationModel<Logs_Erros>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id_log;
	
    @Column(name = "nm_proc", nullable = false, length = 255)
	private String nome_proc;
    
    @Column(name = "nm_usuario", nullable = false, length = 255)
	private String nome_usuario;
	
	private String data;
	
	@Column(name= "cd_erro")
	private Long codigo_erro;
	
	private String msg_erro;

}
