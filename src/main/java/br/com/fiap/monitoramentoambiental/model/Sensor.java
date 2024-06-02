package br.com.fiap.monitoramentoambiental.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tbl_sensor")
public class Sensor {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_SENSOR"
    )
    @SequenceGenerator(
            name = "SEQ_SENSOR",
            sequenceName = "SEQ_SENSOR",
            allocationSize = 1
    )
    @Column(name = "id_sensor")
    private Long idSensor;
    @Column(name = "tipo_sensor")
    private String tipoSensor;
    private String localizacao;
    @OneToMany(mappedBy = "sensor", cascade = CascadeType.ALL)
    private List<Leitura> leituras;

    // getters e setters

    public Long getIdSensor() {
        return idSensor;
    }

    public String getTipoSensor() {
        return tipoSensor;
    }

    public void setTipoSensor(String tipoSensor) {
        this.tipoSensor = tipoSensor;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public List<Leitura> getLeituras() {
        return leituras;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sensor sensor = (Sensor) o;
        return Objects.equals(idSensor, sensor.idSensor) && Objects.equals(tipoSensor, sensor.tipoSensor) && Objects.equals(localizacao, sensor.localizacao) && Objects.equals(leituras, sensor.leituras);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSensor, tipoSensor, localizacao, leituras);
    }

} //FIM