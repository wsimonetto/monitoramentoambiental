package br.com.fiap.monitoramentoambiental.repository;

import br.com.fiap.monitoramentoambiental.model.ControleIrrigacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ControleIrrigacaoRepository extends JpaRepository<ControleIrrigacao, Long> {
} //FIM