package co.edu.uniandes.dse.parcial1.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

@Data
@Entity
public class EstadioEntity extends BaseEntity {

    private String nombre;
    private Long precioAlquiler;
    private String nombre_ciudad;
    private Integer aforo_maximo;
    @PodamExclude
    @OneToMany(mappedBy = "estadio", fetch = FetchType.LAZY)
    private List<ConciertoEntity> conciertos;
}
