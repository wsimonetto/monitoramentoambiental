package br.com.fiap.monitoramentoambiental.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.sql.Date;

public record PrevisaoChuvaCadastroDto(

        Long idPrevisao,

        @NotNull(message = "Campo Data de Previsão da Chuva é obrigatório!")
        Date dataPrevisao,

        @NotBlank(message = "Campo Previsão Chuva é Obrigatório!")
        @Pattern(
                regexp = "^(Com Chuva|Sem Chuva)$",
                message = "Opção inválida para a Previsão de Chuva. Deve ser seguintes opções:" +
                        " Com Chuva ou Sem Chuva"
        )
        String previsao
) {

} //FIM
