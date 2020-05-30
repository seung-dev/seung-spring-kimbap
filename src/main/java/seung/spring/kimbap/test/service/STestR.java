package seung.spring.kimbap.test.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import seung.spring.kimbap.test.STest;

@Repository(value = "sTestR")
public interface STestR extends JpaRepository<STest, Long> {

}
