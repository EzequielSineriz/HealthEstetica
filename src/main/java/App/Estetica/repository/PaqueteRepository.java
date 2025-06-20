package App.Estetica.repository;

import App.Estetica.model.Paquete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PaqueteRepository extends JpaRepository<Paquete,Long> {
    List<Paquete> findByServicioId(Long servicioId);
}
