package App.Estetica.service;

import App.Estetica.dto.EmpleadoRequestDTO;
import App.Estetica.dto.EmpleadoResponseDTO;
import App.Estetica.mapper.EmpleadoMapper;
import App.Estetica.model.Empleado;
import App.Estetica.repository.EmpleadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpleadoService {


    private final EmpleadoRepository empleadoRepository;
    private final EmpleadoMapper empleadoMapper;

    public EmpleadoService(EmpleadoRepository empleadoRepository, EmpleadoMapper empleadoMapper) {
        this.empleadoRepository = empleadoRepository;
        this.empleadoMapper = empleadoMapper;
    }

    public EmpleadoResponseDTO crear(EmpleadoRequestDTO dto) {
        Empleado empleado = empleadoMapper.toEntity(dto);
        return empleadoMapper.toDTO(empleadoRepository.save(empleado));
    }


    public List<EmpleadoResponseDTO> listar() {
        return empleadoRepository.findAll().stream()
                .map(empleadoMapper::toDTO)
                .collect(Collectors.toList());
    }


    public EmpleadoResponseDTO obtenerPorId(Long id) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
        return empleadoMapper.toDTO(empleado);
    }


    public EmpleadoResponseDTO actualizar(Long id, EmpleadoRequestDTO dto) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
        empleadoMapper.actualizarEntidad(empleado, dto);
        return empleadoMapper.toDTO(empleadoRepository.save(empleado));
    }


    public void eliminar(Long id) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
        empleadoRepository.delete(empleado);
    }

}
