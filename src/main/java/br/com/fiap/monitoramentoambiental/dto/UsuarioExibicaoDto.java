package br.com.fiap.monitoramentoambiental.dto;

import br.com.fiap.monitoramentoambiental.model.Usuario;
import br.com.fiap.monitoramentoambiental.model.UsuarioRole;

public record UsuarioExibicaoDto(

        Long idUsuario,
        String nome,
        String email,
        UsuarioRole role)
{
    public UsuarioExibicaoDto (Usuario usuario){
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getRole());
    }

} //FIM