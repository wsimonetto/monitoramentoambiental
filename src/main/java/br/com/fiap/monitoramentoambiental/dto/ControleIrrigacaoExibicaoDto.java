package br.com.fiap.monitoramentoambiental.dto;

import br.com.fiap.monitoramentoambiental.model.ControleIrrigacao;
import br.com.fiap.monitoramentoambiental.model.PrevisaoChuva;

import java.sql.Timestamp;
import java.util.Optional;

public record ControleIrrigacaoExibicaoDto(

        Long idControle,

        String localizacao,

        String estado,

        Timestamp dataHora,

        PrevisaoChuva previsaoChuva
) {
    public ControleIrrigacaoExibicaoDto(ControleIrrigacao controleIrrigacao) {
       this(
               controleIrrigacao.getIdControle(),
               controleIrrigacao.getLocalizacao(),
               controleIrrigacao.getEstado(),
               controleIrrigacao.getDataHora(),
               controleIrrigacao.getPrevisaoChuva()
       );
    }

} //FIM