package App.Estetica.mapper;

import App.Estetica.dto.PaqueteDTO;
import App.Estetica.dto.ServicioDTO;
import App.Estetica.dto.TurnoRequestDTO;
import App.Estetica.dto.TurnoResponseDTO;
import App.Estetica.model.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
public class TurnoMapper {



    public TurnoResponseDTO toDTO(Turno turno){
        TurnoResponseDTO dto = new TurnoResponseDTO();
        dto.setId(turno.getId());
        dto.setFecha(turno.getFecha());
        dto.setHora(turno.getHora());
        dto.setEstado(turno.getEstado());

        dto.setClienteNombre(turno.getCliente().getNombre());
        dto.setEmpleadoNombre(turno.getEmpleado().getNombre());
        dto.setPaqueteNombre(turno.getPaquete().getNombre());
        if (turno.getPaquete() != null) {
            dto.setPaqueteNombre(turno.getPaquete().getNombre());
            dto.setDuracionPaquete(turno.getPaquete().getDuracionMinutos());
            if (turno.getPaquete().getServicio() != null) {
                dto.setServicioNombre(turno.getPaquete().getServicio().getNombre());
            }
        }




        return dto;
    }
    public Turno toEntity(TurnoRequestDTO dto, Cliente cliente, Empleado empleado, Paquete paquete){
        Turno turno = new Turno();
        turno.setHora(dto.getHora());
        turno.setFecha(dto.getFecha());
        turno.setEstado(dto.getEstado() != null ? dto.getEstado() : EstadoTurno.PENDIENTE);

        turno.setCliente(cliente);
        turno.setEmpleado(empleado);
        turno.setPaquete(paquete);

        return turno;
    }
}
