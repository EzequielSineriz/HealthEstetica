package App.Estetica.service;


import App.Estetica.dto.PaqueteDTO;
import App.Estetica.mapper.PaqueteMapper;
import App.Estetica.model.Paquete;
import App.Estetica.model.Servicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import App.Estetica.repository.PaqueteRepository;
import App.Estetica.repository.ServicioRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaqueteService {

    @Autowired
    private PaqueteRepository paqueteRepository;

    @Autowired
    private PaqueteMapper paqueteMapper;

    @Autowired
    private ServicioRepository servicioRepository;

    public PaqueteDTO crear(PaqueteDTO dto){
        Paquete paquete = paqueteMapper.toEntity(dto);
        return paqueteMapper.toDTO(paqueteRepository.save(paquete));
    }

    public void eliminar(Long id){
        paqueteRepository.deleteById(id);
    }

    public List<PaqueteDTO> listarPorServicio(Long servicioId) {
        return paqueteRepository.findByServicioId(servicioId).stream()
                .map(paqueteMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PaqueteDTO actualizar(Long id, PaqueteDTO dto) {
        Paquete existente = paqueteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paquete no encontrado"));

        existente.setNombre(dto.getNombre());
        existente.setDescripcion(dto.getDescripcion());
        existente.setPrecio(dto.getPrecio());
        existente.setDuracionMinutos(dto.getDuracionMinutos());

        if (!existente.getServicio().getId().equals(dto.getServicioId())) {
            Servicio nuevoServicio = servicioRepository.findById(dto.getServicioId())
                    .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));
            existente.setServicio(nuevoServicio);
        }

        return paqueteMapper.toDTO(paqueteRepository.save(existente));
    }

}
