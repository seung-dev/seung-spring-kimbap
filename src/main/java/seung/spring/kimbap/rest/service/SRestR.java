package seung.spring.kimbap.rest.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import seung.spring.kimbap.rest.SRest;

@Repository(value = "sRestR")
public interface SRestR extends JpaRepository<SRest, Long> {

}
