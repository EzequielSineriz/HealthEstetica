package App.Estetica.mapper;

import App.Estetica.dto.ClienteRequestDTO;
import App.Estetica.dto.ClienteResponseDTO;
import App.Estetica.model.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper
{
    public ClienteResponseDTO toDTO(Cliente cliente) {
        ClienteResponseDTO dto = new ClienteResponseDTO();
        dto.setIdCliente(cliente.getIdCliente());
        dto.setNombre(cliente.getNombre());
        dto.setCelular(cliente.getCelular());
        dto.setGenero(cliente.getGenero());
        return dto;
    }

    public Cliente toEntity(ClienteRequestDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNombre(dto.getNombre());
        cliente.setCelular(dto.getCelular());
        cliente.setGenero(dto.getGenero());
        return cliente;
    }

    public void actualizarEntidad(Cliente cliente, ClienteRequestDTO dto) {
        cliente.setNombre(dto.getNombre());
        cliente.setCelular(dto.getCelular());
        cliente.setGenero(dto.getGenero());
    }
}
