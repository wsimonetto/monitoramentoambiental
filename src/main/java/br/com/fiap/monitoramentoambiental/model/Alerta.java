package br.com.fiap.monitoramentoambiental.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "tbl_alerta")
public class Alerta {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_ALERTA"
    )
    @SequenceGenerator(
            name = "SEQ_ALERTA",
            sequenceName = "SEQ_ALERTA",
            allocationSize = 1
    )
    @Column(name = "id_alerta")
    private Long idAlerta;
    @Column(name = "tipo_alerta")
    private String tipoAlerta;
    private String descricao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "data_hora")
    private Timestamp dataHora;

    public Long getIdAlerta() {
        return idAlerta;
    }

    public void setIdAlerta(Long idAlerta) {
        this.idAlerta = idAlerta;
    }

    public String getTipoAlerta() {
        return tipoAlerta;
    }

    public void setTipoAlerta(String tipoAlerta) {
        this.tipoAlerta = tipoAlerta;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Timestamp getDataHora() {
        return dataHora;
    }

    public void setDataHora(Timestamp dataHora) {
        this.dataHora = dataHora;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alerta alerta = (Alerta) o;
        return idAlerta == alerta.idAlerta && Objects.equals(tipoAlerta, alerta.tipoAlerta) && Objects.equals(descricao, alerta.descricao) && Objects.equals(dataHora, alerta.dataHora);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAlerta, tipoAlerta, descricao, dataHora);
    }

} //FIM