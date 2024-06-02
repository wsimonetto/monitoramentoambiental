package br.com.fiap.monitoramentoambiental.dto;

import br.com.fiap.monitoramentoambiental.model.Sensor;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record LeituraCadastroDto(

        Long idLeitura,

        @NotNull(message = "Campo do valor da Leitura não pode ser nulo!")
        Float valor,

        @NotNull(message = "Campo Data/hora da Leitura é obrigatório!")
        LocalDateTime dataHora,

        @NotNull(message = "Campo Sensor é obrigatório!")
        Sensor sensor
) {

} //FIM