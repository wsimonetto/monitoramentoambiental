package br.com.fiap.monitoramentoambiental.dto;

import br.com.fiap.monitoramentoambiental.model.Alerta;

import java.sql.Timestamp;

public record AlertaExibicaoDto(
        Long idAlerta,
        String tipoAlerta,
        String descricao,
        Timestamp dataHora
) {

    public AlertaExibicaoDto(Alerta alerta) {
        this(
                alerta.getIdAlerta(),
                alerta.getTipoAlerta(),
                alerta.getDescricao(),
                alerta.getDataHora());
    }

} //FIM