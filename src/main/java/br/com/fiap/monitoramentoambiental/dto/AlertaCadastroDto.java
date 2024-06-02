package br.com.fiap.monitoramentoambiental.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.sql.Timestamp;

public record AlertaCadastroDto(

        Long idAlerta,

        @NotBlank(message = "Campo Tipo de Alerta é Obrigatório!")
        @Pattern(
                regexp = "^(Alerta de Qualidade do Ar|Alerta de Umidade do Solo|Alerta de Temperatura)$",
                message = "Opção inválida para o tipo de alerta. As opções válidas são: 'Alerta de Qualidade do Ar', 'Alerta de Umidade do Solo' ou 'Alerta de Temperatura'."
        )
        String tipoAlerta,

        @NotBlank(message = "Campo Descrição do Alerta é obrigatório!.")
        @Pattern(
                regexp = "^(Poluição do ar acima do limite recomendado no Parque Ibirapuera" +
                        "|Umidade do solo abaixo do limite recomendado no Parque Ibirapuera" +
                        "|Temperatura elevada detectada no Parque Ibirapuera" +
                        "|Poluição do ar acima do limite recomendado no Parque Villa-Lobos" +
                        "|Umidade do solo abaixo do limite recomendado no Parque Villa-Lobos" +
                        "|Temperatura elevada detectada no Parque Villa-Lobos)$",
                message = "Opção inválida para a descrição do alerta. Deve ser seguintes opções:" +
                        " Poluição do ar acima do limite recomendado no Parque Ibirapuera (para Alerta de Qualidade do Ar)," +
                        " Umidade do solo abaixo do limite recomendado no Parque Ibirapuera (para Alerta de Umidade do Solo)," +
                        " Temperatura elevada detectada no Parque Ibirapuera (para Alerta de Temperatura), " +
                        " Poluição do ar acima do limite recomendado no Villa-Lobos (para Alerta de Qualidade do Ar)," +
                        " Umidade do solo abaixo do limite recomendado no Villa-Lobos (para Alerta de Umidade do Solo)," +
                        " Temperatura elevada detectada no Villa-Lobos (para Alerta de Temperatura)."
        )
        String descricao,

        @NotNull(message = "Campo Data/hora de aferição é obrigatório!")
        Timestamp dataHora
) {

} //FIM