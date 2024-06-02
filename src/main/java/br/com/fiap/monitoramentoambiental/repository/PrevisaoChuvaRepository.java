package br.com.fiap.monitoramentoambiental.repository;

import br.com.fiap.monitoramentoambiental.model.PrevisaoChuva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrevisaoChuvaRepository extends JpaRepository<PrevisaoChuva, Long> {
} //FIM