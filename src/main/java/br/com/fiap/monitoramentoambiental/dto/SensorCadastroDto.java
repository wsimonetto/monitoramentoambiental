package br.com.fiap.monitoramentoambiental.dto;

import br.com.fiap.monitoramentoambiental.model.Leitura;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public record SensorCadastroDto(

        Long idSensor,

        @NotBlank(message = "Campo Tipo Sensor é Obrigatório!")
        @Pattern(regexp = "^(Sensor de Qualidade do Ar|Sensor de Umidade do Solo|Sensor de Temperatura)$",
                message = "Opção inválida para Sensores. Deve ser seguintes opções: " +
                        " Sensor de Qualidade do Ar | " +
                        " Sensor de Umidade do Solo | "+
                        " Sensor de Temperatura."
        )
        String tipoSensor,

        @NotBlank(message = "Campo Localização é Obrigatório!")
        @Pattern(regexp = "^(Parque Ibirapuera|Parque Villa-Lobos)$",
                message = "Opção inválida para Localização. Deve ser seguintes opções: " +
                        "Parque Ibirapuera | " +
                        "Parque Villa-Lobos "
        )
        String localizacao,

        List<Leitura> leitura
) {

} //FIM