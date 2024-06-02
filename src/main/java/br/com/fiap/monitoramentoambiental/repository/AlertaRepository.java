package br.com.fiap.monitoramentoambiental.repository;

import br.com.fiap.monitoramentoambiental.model.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertaRepository extends JpaRepository<Alerta, Long> {
} //FIM