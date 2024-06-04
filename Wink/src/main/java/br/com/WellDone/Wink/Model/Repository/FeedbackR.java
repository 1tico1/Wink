package br.com.WellDone.Wink.Model.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.WellDone.Wink.Model.Entity.Feedback;

public interface FeedbackR  extends JpaRepository<Feedback, Long> {

}
