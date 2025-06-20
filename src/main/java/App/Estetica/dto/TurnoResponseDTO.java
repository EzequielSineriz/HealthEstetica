package App.Estetica.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import App.Estetica.model.EstadoTurno;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@Getter@Setter
public class TurnoResponseDTO {
    private Long id;
    private String clienteNombre;
    private String empleadoNombre;
    private String paqueteNombre;
    private String servicioNombre;
    @NotNull(message = "La duracion no puede ser nula")
    private Integer duracionPaquete;
    private LocalDate fecha;
    private LocalTime hora;
    private EstadoTurno estado;

    public TurnoResponseDTO() {
    }

    public Integer getDuracionPaquete() {
        return duracionPaquete;
    }

    public void setDuracionPaquete(Integer duracionPaquete) {
        this.duracionPaquete = duracionPaquete;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClienteNombre() {
        return clienteNombre;
    }

    public void setClienteNombre(String clienteNombre) {
        this.clienteNombre = clienteNombre;
    }

    public String getEmpleadoNombre() {
        return empleadoNombre;
    }

    public void setEmpleadoNombre(String empleadoNombre) {
        this.empleadoNombre = empleadoNombre;
    }

    public String getPaqueteNombre() {
        return paqueteNombre;
    }

    public void setPaqueteNombre(String paqueteNombre) {
        this.paqueteNombre = paqueteNombre;
    }

    public String getServicioNombre() {
        return servicioNombre;
    }

    public void setServicioNombre(String servicioNombre) {
        this.servicioNombre = servicioNombre;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public EstadoTurno getEstado() {
        return estado;
    }

    public void setEstado(EstadoTurno estado) {
        this.estado = estado;
    }
}
