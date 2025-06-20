package App.Estetica.mapper;

import App.Estetica.dto.PaqueteDTO;
import App.Estetica.model.Paquete;
import App.Estetica.model.Servicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import App.Estetica.repository.ServicioRepository;

@Component
public class PaqueteMapper {

    @Autowired
    private ServicioRepository servicioRepository;

    public PaqueteDTO toDTO (Paquete entity){
        PaqueteDTO dto = new PaqueteDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setDescripcion(entity.getDescripcion());
        dto.setPrecio(entity.getPrecio());
        dto.setDuracionMinutos((entity.getDuracionMinutos()));
        dto.setServicioId(entity.getServicio().getId());
        return dto;
    }

    public Paquete toEntity(PaqueteDTO dto){
        Paquete entity = new Paquete();
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        entity.setPrecio(dto.getPrecio());
        entity.setDuracionMinutos(dto.getDuracionMinutos());

        Servicio servicio = servicioRepository.findById(dto.getServicioId()).
                orElseThrow(()-> new RuntimeException("Servicio no encontrado"));
        entity.setServicio(servicio);

        return entity;
    }
}
