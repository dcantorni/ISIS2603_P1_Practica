package co.edu.uniandes.dse.parcial1.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import co.edu.uniandes.dse.parcial1.entities.ConciertoEntity;
import jakarta.transaction.Transactional;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@DataJpaTest
@Transactional
@Import(ConciertoService.class)
public class conciertoServiceTest {
    @Autowired
    private ConciertoService conciertoService;

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();

    private List<ConciertoEntity> conciertoList = new ArrayList<>();
    @BeforeEach
    void setUp() {
        clearData();
        insertData();
    }

    private void clearData() {
        entityManager.getEntityManager().createQuery("delete from ConciertoEntity").executeUpdate();

    }

      private void insertData() {
        for (int i = 0; i < 3; i++) {
            ConciertoEntity concierto = factory.manufacturePojo(ConciertoEntity.class);
            concierto.setFecha_concierto(LocalDateTime.now().plusDays(i + 1)); // fechas válidas
            concierto.setAforo_concierto(100 + i);
            concierto.setPresupuesto(2000L + i);
            entityManager.persist(concierto);
            conciertoList.add(concierto);
        }
    }

    @Test
    void testCreateConciertoValid() {
        ConciertoEntity newConcierto = factory.manufacturePojo(ConciertoEntity.class);
        newConcierto.setFecha_concierto(LocalDateTime.now().plusDays(10));
        newConcierto.setAforo_concierto(200);
        newConcierto.setPresupuesto(5000L);

        ConciertoEntity result = conciertoService.createConcierto(newConcierto);

        assertNotNull(result);
        ConciertoEntity entity = entityManager.find(ConciertoEntity.class, result.getId());
        assertEquals(newConcierto.getAforo_concierto(), entity.getAforo_concierto());
        assertEquals(newConcierto.getPresupuesto(), entity.getPresupuesto());
    }

    @Test
    void testCreateConciertoFechaPasada() {
        assertThrows(IllegalArgumentException.class, () -> {
            ConciertoEntity newConcierto = factory.manufacturePojo(ConciertoEntity.class);
            newConcierto.setFecha_concierto(LocalDateTime.now().minusDays(1)); // fecha pasada
            newConcierto.setAforo_concierto(200);
            newConcierto.setPresupuesto(5000L);
            conciertoService.createConcierto(newConcierto);
        });
    }
}
