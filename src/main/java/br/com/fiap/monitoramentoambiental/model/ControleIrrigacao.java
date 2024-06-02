package br.com.fiap.monitoramentoambiental.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "tbl_controle_irrigacao")
public class ControleIrrigacao {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_CONTROLE_IRRIGACAO"
    )
    @SequenceGenerator(
            name = "SEQ_CONTROLE_IRRIGACAO",
            sequenceName = "SEQ_CONTROLE_IRRIGACAO",
            allocationSize = 1
    )
    @Column(name = "id_controle")
    private Long idControle;
    private String localizacao;
    private String estado;
    @Column(name = "data_hora")
    private Timestamp dataHora;
    @ManyToOne
    @JoinColumn(name = "id_previsao", referencedColumnName = "id_previsao", nullable = false)
    @JsonBackReference
    private PrevisaoChuva previsaoChuva;

    // getters e setters

    // Método personalizado para serialização do ID da previsaoChuva
    @JsonProperty("id_previsao")
    public Long getIdPrevisao() {
        return this.previsaoChuva != null ? this.previsaoChuva.getIdPrevisao() : null;
    }

    public Long getIdControle() {
        return idControle;
    }

    public void setIdControle(Long idControle) {
        this.idControle = idControle;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Timestamp getDataHora() {
        return dataHora;
    }

    public void setDataHora(Timestamp dataHora) {
        this.dataHora = dataHora;
    }

    public PrevisaoChuva getPrevisaoChuva() {
        return previsaoChuva;
    }

    public void setPrevisaoChuva(PrevisaoChuva previsaoChuva) {
        this.previsaoChuva = previsaoChuva;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ControleIrrigacao that = (ControleIrrigacao) o;
        return Objects.equals(idControle, that.idControle) && Objects.equals(localizacao, that.localizacao) && Objects.equals(estado, that.estado) && Objects.equals(dataHora, that.dataHora) && Objects.equals(previsaoChuva, that.previsaoChuva);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idControle, localizacao, estado, dataHora, previsaoChuva);
    }

} // FIM