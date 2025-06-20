package App.Estetica.controller;

import App.Estetica.dto.TurnoRequestDTO;
import App.Estetica.dto.TurnoResponseDTO;
import App.Estetica.model.EstadoTurno;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import App.Estetica.service.TurnoService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    @Autowired
    private TurnoService turnoService;

    @PostMapping
    public ResponseEntity<TurnoResponseDTO> crearTurno(@RequestBody @Valid TurnoRequestDTO dto) {
            TurnoResponseDTO creado = turnoService.crearTurno(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @GetMapping
    public ResponseEntity<List<TurnoResponseDTO>> listarTurnos() {
        if(turnoService.listarTurnos().isEmpty()){
            ResponseEntity.status(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(turnoService.listarTurnos());
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<TurnoResponseDTO>> getTurnosPorEstado(@PathVariable EstadoTurno estado) {
        return ResponseEntity.ok(turnoService.obtenerPorEstado(estado));
    }

    @GetMapping("/rango")
    public ResponseEntity<List<TurnoResponseDTO>> getTurnosEntreFechas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin) {
        return ResponseEntity.ok(turnoService.obtenerTurnosEntreFechas(inicio, fin));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurnoResponseDTO>ObtenerTurno(@PathVariable Long id){
        return  ResponseEntity.ok(turnoService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> eliminarTurno(@PathVariable Long id){
        turnoService.eliminarTurno(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}")
    public ResponseEntity<TurnoResponseDTO> actualizarTurno(
            @PathVariable Long id,
            @RequestBody TurnoRequestDTO dto) {

        TurnoResponseDTO actualizado = turnoService.actualizarTurno(id, dto);
        return ResponseEntity.ok(actualizado);
    }
}
