package App.Estetica.controller;


import App.Estetica.dto.PaqueteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import App.Estetica.service.PaqueteService;

import java.util.List;

@RestController
@RequestMapping("/paquetes")
public class PaqueteController {

    @Autowired
    private PaqueteService paqueteService;

    @PostMapping
    public ResponseEntity<PaqueteDTO> crear(@RequestBody PaqueteDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(paqueteService.crear(dto));
    }

    @GetMapping("/por-servicio/{servicioId}")
    public List<PaqueteDTO> listarPorServicio(@PathVariable Long servicioId) {
        return paqueteService.listarPorServicio(servicioId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        paqueteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody PaqueteDTO dto) {
        try {
            PaqueteDTO actualizado = paqueteService.actualizar(id, dto);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
