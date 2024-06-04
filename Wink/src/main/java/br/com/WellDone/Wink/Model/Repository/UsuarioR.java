package br.com.WellDone.Wink.Model.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.WellDone.Wink.Model.Entity.Usuario;

public interface UsuarioR extends JpaRepository<Usuario, Long>{

}
