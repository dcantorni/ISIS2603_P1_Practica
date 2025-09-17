package co.edu.uniandes.dse.parcial1.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcial1.entities.ConciertoEntity;
import co.edu.uniandes.dse.parcial1.repositories.ConciertoRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ConciertoService {

    @Autowired
    private ConciertoRepository conciertoRepository;

    @Transactional
    public ConciertoEntity createConcierto(ConciertoEntity concierto){
        LocalDateTime fechaActual = LocalDateTime.now();

        if (concierto.getFecha_concierto().isBefore(fechaActual)){
        throw new IllegalArgumentException("La fecha del concierto no puede ser previa a la fecha actual.");
    }
    if (concierto.getAforo_concierto() <= 10){
        throw new IllegalArgumentException("El aforo del concierto debe ser mayor a 10 personas.");
    }
    if (concierto.getPresupuesto() <= 1000){
        throw new IllegalArgumentException("El presupuesto del concierto debe ser superior a $1000 dolares.");
    }
        return conciertoRepository.save(concierto); 
    }
}

