package es.cic.curso25.proy014.repository;

import es.cic.curso25.proy014.model.Plaza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlazaRepository extends JpaRepository<Plaza, Long> {
   
}
