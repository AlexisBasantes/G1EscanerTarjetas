package RegistroAcceso;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RegistroAcceso {
    private String estudiante;
    private LocalDateTime fechaHora;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public RegistroAcceso(String estudiante, LocalDateTime fechaHora) {
        this.estudiante = estudiante;
        this.fechaHora = fechaHora != null ? fechaHora : LocalDateTime.now();
    }

    public String getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(String estudiante) {
        if (estudiante != null && !estudiante.trim().isEmpty()) {
            this.estudiante = estudiante;
        }
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        if (fechaHora != null) {
            this.fechaHora = fechaHora;
        }
    }

    @Override
    public String toString() {
        return "👤 Estudiante: " + estudiante + " |  FechaHora: " + FORMATTER.format(fechaHora);
    }
}