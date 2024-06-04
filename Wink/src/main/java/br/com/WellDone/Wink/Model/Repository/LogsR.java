package br.com.WellDone.Wink.Model.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.WellDone.Wink.Model.Entity.Logs_Erros;

public interface LogsR extends JpaRepository<Logs_Erros, Long>{

}
