package br.com.fiap.monitoramentoambiental.dto;

import br.com.fiap.monitoramentoambiental.model.UsuarioRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioCadastroDto(
        Long idUsuario,
        @NotBlank
        String nome,
        @NotBlank(message = "O e-mail do usuário é obrigatório!")
        @Email(message = "O e-mail do usuário inválido!")
        String email,
        @NotBlank(message = "A senha é obrigatória!")
        @Size(min = 6, max = 20, message = "A senha deve conter entre 6 e 20 caracteres")
        String senha,

        UsuarioRole role
) {

} //FIM