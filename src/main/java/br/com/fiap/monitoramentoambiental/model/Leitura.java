package br.com.fiap.monitoramentoambiental.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tbl_leitura")
public class Leitura {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_LEITURA")
    @SequenceGenerator(
            name = "SEQ_LEITURA",
            sequenceName = "SEQ_LEITURA",
            allocationSize = 1)
    @Column(name = "id_leitura")
    private Long idLeitura;

    @Column(name = "valor")
    private float valor;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "data_hora")
    private LocalDateTime dataHora;

    @ManyToOne
    @JoinColumn(name = "id_sensor", referencedColumnName = "id_sensor", nullable = false)
    @JsonBackReference
    private Sensor sensor;

    // getters e setters

    @JsonProperty("id_sensor")
    public Long getIdSensor() {
        return this.sensor != null ? this.sensor.getIdSensor() : null;
    }
    public Long getIdLeitura() {
        return idLeitura;
    }

    public void setIdLeitura(Long idLeitura) {
        this.idLeitura = idLeitura;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Leitura leitura = (Leitura) o;
        return Float.compare(valor, leitura.valor) == 0 && Objects.equals(idLeitura, leitura.idLeitura) && Objects.equals(dataHora, leitura.dataHora) && Objects.equals(sensor, leitura.sensor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idLeitura, valor, dataHora, sensor);
    }

} //FIM