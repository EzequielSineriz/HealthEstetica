package App.Estetica.repository;

import App.Estetica.model.EstadoTurno;
import App.Estetica.model.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long> {
    List<Turno> findByEmpleadoIdAndFecha(Long empleadoId, LocalDate fecha);
    List<Turno> findByEstado(EstadoTurno estado);
    List<Turno> findByFechaBetween(LocalDate inicio, LocalDate fin);
}
