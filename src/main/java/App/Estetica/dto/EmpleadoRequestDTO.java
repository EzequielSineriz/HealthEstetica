package App.Estetica.dto;

import jakarta.validation.constraints.NotBlank;

public class EmpleadoRequestDTO {

    @NotBlank
    private String nombre;

    private String especialidad;

    public EmpleadoRequestDTO() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
}
