package co.edu.uniandes.dse.parcial1.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class ConciertoEntity extends BaseEntity {

    private String nombre;
    private Long presupuesto;
    //Punto 1 
    private String nombre_artista;
    private Date fecha_concierto;
    private Integer aforo_concierto;

}
