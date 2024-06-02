package br.com.fiap.monitoramentoambiental.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "tbl_previsao_chuva")
public class PrevisaoChuva {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_PRECISAO_CHUVA")
    @SequenceGenerator(
            name = "SEQ_PRECISAO_CHUVA",
            sequenceName = "SEQ_PRECISAO_CHUVA",
            allocationSize = 1)

    @Column(name = "id_previsao")
    private Long idPrevisao;

    @Column(name = "data_previsao")
    private Date dataPrevisao;

    private String previsao;

    public Long getIdPrevisao() {
        return idPrevisao;
    }

    public void setIdPrevisao(Long idPrevisao) {
        this.idPrevisao = idPrevisao;
    }

    public Date getDataPrevisao() {
        return dataPrevisao;
    }

    public void setDataPrevisao(Date dataPrevisao) {
        this.dataPrevisao = dataPrevisao;
    }

    public String getPrevisao() {
        return previsao;
    }

    public void setPrevisao(String previsao) {
        this.previsao = previsao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrevisaoChuva that = (PrevisaoChuva) o;
        return Objects.equals(idPrevisao, that.idPrevisao) && Objects.equals(dataPrevisao, that.dataPrevisao) && Objects.equals(previsao, that.previsao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPrevisao, dataPrevisao, previsao);
    }

} //FIM
