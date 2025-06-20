package App.Estetica.mapper;

import App.Estetica.dto.EmpleadoRequestDTO;
import App.Estetica.dto.EmpleadoResponseDTO;
import App.Estetica.model.Empleado;
import org.springframework.stereotype.Component;

@Component
public class EmpleadoMapper {

    public EmpleadoResponseDTO toDTO(Empleado empleado) {
        EmpleadoResponseDTO dto = new EmpleadoResponseDTO();
        dto.setId(empleado.getId());
        dto.setNombre(empleado.getNombre());
        dto.setEspecialidad(empleado.getEspecialidad());
        return dto;
    }

    public Empleado toEntity(EmpleadoRequestDTO dto) {
        Empleado empleado = new Empleado();
        empleado.setNombre(dto.getNombre());
        empleado.setEspecialidad(dto.getEspecialidad());
        return empleado;
    }

    public void actualizarEntidad(Empleado empleado, EmpleadoRequestDTO dto) {
        empleado.setNombre(dto.getNombre());
        empleado.setEspecialidad(dto.getEspecialidad());
    }
}
