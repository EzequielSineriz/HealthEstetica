package App.Estetica.service;


import App.Estetica.dto.ServicioDTO;
import App.Estetica.mapper.ServicioMapper;
import App.Estetica.model.Servicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import App.Estetica.repository.ServicioRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicioService {

    @Autowired
    private ServicioRepository servicioRepository;

    @Autowired
    private ServicioMapper servicioMapper;

    public ServicioDTO crear(ServicioDTO dto){
        Servicio servicio = servicioMapper.toEntity(dto);
        return servicioMapper.toDTO(servicioRepository.save(servicio));
    }

    public List<ServicioDTO> listar(){
        return servicioRepository.findAll().stream().map(servicioMapper::toDTO).collect(Collectors.toList());
    }

    public ServicioDTO actualizar(Long id, ServicioDTO dto) {
        Servicio existente = servicioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));

        existente.setNombre(dto.getNombre());
        existente.setDescripcion(dto.getDescripcion());

        return servicioMapper.toDTO(servicioRepository.save(existente));
    }





}
