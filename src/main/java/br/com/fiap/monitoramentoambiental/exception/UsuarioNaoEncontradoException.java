package br.com.fiap.monitoramentoambiental.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsuarioNaoEncontradoException{
    public UsuarioNaoEncontradoException(String mensagem){
        super();
    }

} //FIM