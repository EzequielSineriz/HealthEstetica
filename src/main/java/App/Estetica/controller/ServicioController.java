package App.Estetica.controller;


import App.Estetica.dto.ServicioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import App.Estetica.service.ServicioService;

import java.util.List;

@RestController
@RequestMapping("/servicios")
public class ServicioController {

    @Autowired
    private ServicioService servicioService;

    @PostMapping
    public ResponseEntity<ServicioDTO> crear(@RequestBody ServicioDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(servicioService.crear(dto));
    }

    @GetMapping
    public List<ServicioDTO> listar(){
        return servicioService.listar();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody ServicioDTO dto) {
        try {
            ServicioDTO actualizado = servicioService.actualizar(id, dto);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
