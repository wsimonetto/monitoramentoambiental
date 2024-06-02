package br.com.fiap.monitoramentoambiental.dto;

import br.com.fiap.monitoramentoambiental.model.Leitura;
import br.com.fiap.monitoramentoambiental.model.Sensor;

import java.util.List;

public record SensorExibicaoDto(

        Long idSensor,
        String tipoSensor,
        String localizacao,
        List<Leitura> leituras
) {
    public SensorExibicaoDto(Sensor sensor){
        this(
                sensor.getIdSensor(),
                sensor.getTipoSensor(),
                sensor.getLocalizacao(),
                sensor.getLeituras()
        );
    }

} //FIM