package co.edu.uniandes.dse.parcial1.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

@Data
@Entity
public class ConciertoEntity extends BaseEntity {

    private String nombre;
    private Long presupuesto;
    //Punto 1 
    private String nombre_artista;
    private Date fecha_concierto;
    private Integer aforo_concierto;

    @ManyToOne
    private EstadioEntity estadio;
}
