package br.com.fiap.monitoramentoambiental.dto;

import br.com.fiap.monitoramentoambiental.model.PrevisaoChuva;

import java.sql.Date;

public record PrevisaoChuvaExibicaoDto(

        Long idPrevisao,
        Date dataPrevisao,
        String previsao
) {
    public PrevisaoChuvaExibicaoDto(PrevisaoChuva previsaoChuva) {
        this(
                previsaoChuva.getIdPrevisao(),
                previsaoChuva.getDataPrevisao(),
                previsaoChuva.getPrevisao()
        );
    }

} //FIM