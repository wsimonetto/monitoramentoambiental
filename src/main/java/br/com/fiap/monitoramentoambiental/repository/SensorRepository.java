package br.com.fiap.monitoramentoambiental.repository;

import br.com.fiap.monitoramentoambiental.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {
} //FIM