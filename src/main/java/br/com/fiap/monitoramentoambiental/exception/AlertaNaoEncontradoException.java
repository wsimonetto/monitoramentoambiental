package br.com.fiap.monitoramentoambiental.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AlertaNaoEncontradoException extends RuntimeException{

    public AlertaNaoEncontradoException(String menssagem) {
        super(menssagem);
    }

} //FIM