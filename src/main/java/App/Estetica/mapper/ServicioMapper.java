package App.Estetica.mapper;


import App.Estetica.dto.ServicioDTO;
import App.Estetica.model.Servicio;
import org.springframework.stereotype.Component;

@Component
public class ServicioMapper {
    public ServicioDTO toDTO(Servicio entity){
        ServicioDTO dto = new ServicioDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setDescripcion(entity.getDescripcion());
        return dto;
    }
    public Servicio toEntity(ServicioDTO dto){
        Servicio entity = new Servicio();
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        return entity;
    }
}
