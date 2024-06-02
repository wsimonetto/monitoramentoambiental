package br.com.fiap.monitoramentoambiental.dto;

import br.com.fiap.monitoramentoambiental.model.PrevisaoChuva;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.sql.Timestamp;

public record ControleIrrigacaoCadastroDto(
        Long idControle,

        @NotBlank(message = "Localização é obrigatória!")
        @Pattern(
                regexp = "^(Parque Ibirapuera|Parque Villa-Lobos)$",
                message = "Opção inválida para a localização. " +
                        "As opções válidas são: " +
                        "Parque Ibirapuera ou Parque Villa-Lobos."
        )
        String localizacao,

        @NotBlank(message = "Estado do Controle de Irrigaçaõ é Obrigatória")
        @Pattern(
                regexp = "^(Ligado|Desligado)$",
                message = "Opção inválida para o estado do Controle de Irrigação. " +
                        "As opções válidas são: " +
                        "Ligado ou Desligado."
        )
        String estado,

        @NotNull(message = "Data/hora do Controle de Irrigação é obrigatória!")
        Timestamp dataHora,

        @NotBlank(message = "Um Id de Previsão de Chuva é Obrigatória!")
        PrevisaoChuva previsaoChuva
) {

} //FIM