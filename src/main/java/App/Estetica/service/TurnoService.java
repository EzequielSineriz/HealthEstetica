package App.Estetica.service;

import App.Estetica.mapper.TurnoMapper;
import App.Estetica.model.*;
import App.Estetica.dto.TurnoRequestDTO;
import App.Estetica.dto.TurnoResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import App.Estetica.repository.ClienteRepository;
import App.Estetica.repository.EmpleadoRepository;
import App.Estetica.repository.PaqueteRepository;
import App.Estetica.repository.TurnoRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TurnoService {


    @Autowired
    private TurnoRepository turnoRepository;
    @Autowired
    private EmpleadoRepository empleadoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private PaqueteRepository paqueteRepository;

    @Autowired
    private TurnoMapper turnoMapper;

    public TurnoResponseDTO crearTurno(TurnoRequestDTO dto) {
        Empleado empleado = empleadoRepository.findById(dto.getEmpleadoId())
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        Paquete paquete = paqueteRepository.findById(dto.getPaqueteId())
                .orElseThrow(() -> new RuntimeException("Paquete no encontrado"));
        if (paquete.getDuracionMinutos() == null) {
            throw new RuntimeException("El paquete no tiene una duración definida");
        }

        if (!empleadoDisponible(empleado.getId(), dto.getFecha(), dto.getHora(), paquete.getDuracionMinutos())) {
            throw new RuntimeException("Empleado no disponible en ese horario");
        }



        Turno turno = new Turno();
        turno.setCliente(cliente);
        turno.setEmpleado(empleado);
        turno.setPaquete(paquete);
        turno.setFecha(dto.getFecha());
        turno.setHora(dto.getHora());
        turno.setEstado(EstadoTurno.PENDIENTE);


        Turno guardado = turnoRepository.save(turno);
        return toDTO(guardado);
    }

    public List<TurnoResponseDTO> listarTurnos() {
        return turnoRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private boolean empleadoDisponible(Long empleadoId, LocalDate fecha, LocalTime hora, int duracionMinutos) {
        List<Turno> turnos = turnoRepository.findByEmpleadoIdAndFecha(empleadoId, fecha);
        LocalTime inicioNuevo = hora;
        LocalTime finNuevo = hora.plusMinutes(duracionMinutos);

        for (Turno t : turnos) {
            LocalTime inicioExistente = t.getHora();
            LocalTime finExistente = inicioExistente.plusMinutes(t.getPaquete().getDuracionMinutos());
            if (inicioNuevo.isBefore(finExistente) && finNuevo.isAfter(inicioExistente)) {
                return false;
            }
        }
        return true;
    }
    public TurnoResponseDTO obtenerPorId(Long id) {
        Turno turno = turnoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Turno no encontrado"));
        return turnoMapper.toDTO(turno);
    }

    public TurnoResponseDTO actualizarTurno(Long id, TurnoRequestDTO dto) {
        Turno existente = turnoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Turno no encontrado"));

        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        Empleado empleado = empleadoRepository.findById(dto.getEmpleadoId())
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
        Paquete paquete = paqueteRepository.findById(dto.getPaqueteId())
                .orElseThrow(() -> new RuntimeException("Paquete no encontrado"));

        existente.setFecha(dto.getFecha());
        existente.setHora(dto.getHora());
        existente.setEstado(dto.getEstado());
        existente.setCliente(cliente);
        existente.setEmpleado(empleado);
        existente.setPaquete(paquete);

        if (existente.getEstado() != EstadoTurno.PENDIENTE) {
            throw new IllegalStateException("No se puede editar un turno que ya fue " + existente.getEstado());
        }


        return turnoMapper.toDTO(turnoRepository.save(existente));
    }


    public void eliminarTurno(Long id) {
        Turno turno = turnoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Turno no encontrado"));
        turnoRepository.delete(turno);
    }

    public List<TurnoResponseDTO> obtenerPorEstado(EstadoTurno estado) {
        List<Turno> turnos = turnoRepository.findByEstado(estado);
        return turnos.stream()
                .map(turnoMapper::toDTO)
                .collect(Collectors.toList());
    }
    public List<TurnoResponseDTO> obtenerTurnosEntreFechas(LocalDate inicio, LocalDate fin) {
        List<Turno> turnos = turnoRepository.findByFechaBetween(inicio, fin);
        return turnos.stream()
                .map(turnoMapper::toDTO)
                .collect(Collectors.toList());
    }


    private TurnoResponseDTO toDTO(Turno turno) {
        TurnoResponseDTO dto = new TurnoResponseDTO();
        dto.setId(turno.getId());
        dto.setClienteNombre(turno.getCliente().getNombre());
        dto.setEmpleadoNombre(turno.getEmpleado().getNombre());
        dto.setPaqueteNombre(turno.getPaquete().getNombre());
        dto.setServicioNombre(turno.getPaquete().getServicio().getNombre());
        dto.setFecha(turno.getFecha());
        dto.setHora(turno.getHora());
        dto.setEstado(turno.getEstado());
        dto.setDuracionPaquete(turno.getPaquete().getDuracionMinutos());
        return dto;
    }
}
