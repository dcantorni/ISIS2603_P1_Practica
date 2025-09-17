package co.edu.uniandes.dse.parcial1.services;

import java.time.Duration;
import java.util.List;

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
public class ConciertoEstadioService {
    @Autowired
    ConciertoRepository conciertoRepository;

    @Autowired
    EstadioRepository estadioRepository;

    @Transactional
    public void addConciertoToEstadio(ConciertoEntity concierto, EstadioEntity estadio){
        if (concierto.getAforo_concierto() > estadio.getAforo_maximo())
        throw new IllegalArgumentException("La capacidad de un concierto no debe superar la capacidad del estadio.");
        
        if (concierto.getPresupuesto() < estadio.getPrecioAlquiler())
        throw new IllegalArgumentException("El precio de alquiler del estadio no debe superar el presupuesto del concierto.");

        List<ConciertoEntity> conciertosEstadio = estadio.getConciertos();
        
        for (ConciertoEntity conciertoEstadio : conciertosEstadio){
            if (Math.abs(Duration.between(concierto.getFecha_concierto(), conciertoEstadio.getFecha_concierto()).toDays()) < 2)
        throw new IllegalArgumentException("Siempre debe existir un tiempo mínimo de 2 días entre los conciertos asociados a un estadio.");

        }

        estadio.getConciertos().add(concierto);
        concierto.setEstadio(estadio);
        conciertoRepository.save(concierto);    
    }

}
