package co.edu.uniandes.dse.parcial1.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;

import jakarta.persistence.ManyToOne;

import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

@Data
@Entity
public class ConciertoEntity extends BaseEntity {

    private String nombre;
    private Long presupuesto;
    //Punto 1 
    private String nombre_artista;
    private LocalDateTime fecha_concierto;
    private Integer aforo_concierto;
    @PodamExclude
    @ManyToOne
    private EstadioEntity estadio;
}
