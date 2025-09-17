package co.edu.uniandes.dse.parcial1.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcial1.entities.ConciertoEntity;
import co.edu.uniandes.dse.parcial1.entities.EstadioEntity;
import co.edu.uniandes.dse.parcial1.repositories.ConciertoRepository;
import co.edu.uniandes.dse.parcial1.repositories.EstadioRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EstadioService {

    @Autowired
    private EstadioRepository estadioRepository;

    @Transactional
    public EstadioEntity createEstadio(EstadioEntity estadio){
        

    if (estadio.getNombre_ciudad().length() < 3){
        throw new IllegalArgumentException("El nombre de la ciudad debe tener 3 caracteres o más");
    }
    if (estadio.getAforo_maximo() >= 1000000 || estadio.getAforo_maximo() <= 1000 ){
        throw new IllegalArgumentException("La capacidad del estadio debe ser superior a 1000 personas y menor a 1000000");
    }
    if (estadio.getPrecioAlquiler() <= 100000){
        throw new IllegalArgumentException("El precio de alquiler debe ser superior a 100000 dólares.");
    }
        return estadioRepository.save(estadio);
    }

}
