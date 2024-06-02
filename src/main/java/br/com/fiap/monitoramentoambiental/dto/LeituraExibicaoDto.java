package br.com.fiap.monitoramentoambiental.dto;

import br.com.fiap.monitoramentoambiental.model.Leitura;
import br.com.fiap.monitoramentoambiental.model.Sensor;

import java.time.LocalDateTime;

public record LeituraExibicaoDto(

        Long idLeitura,
        Float valor,
        LocalDateTime dataHora,
        Sensor sensor
) {
    public LeituraExibicaoDto(Leitura leitura) {
        this(
                leitura.getIdLeitura(),
                leitura.getValor(),
                leitura.getDataHora(),
                leitura.getSensor()
        );
    }

} //FIM