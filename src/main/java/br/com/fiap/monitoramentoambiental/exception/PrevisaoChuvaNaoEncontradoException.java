package br.com.fiap.monitoramentoambiental.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PrevisaoChuvaNaoEncontradoException extends RuntimeException {

    public PrevisaoChuvaNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

} //FIM