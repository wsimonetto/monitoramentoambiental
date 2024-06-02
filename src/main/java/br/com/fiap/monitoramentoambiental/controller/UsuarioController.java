package br.com.fiap.monitoramentoambiental.controller;

import br.com.fiap.monitoramentoambiental.dto.UsuarioCadastroDto;
import br.com.fiap.monitoramentoambiental.dto.UsuarioExibicaoDto;
import br.com.fiap.monitoramentoambiental.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/usuarios/{email}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioExibicaoDto buscarUsuarioPeloId(@PathVariable String email, UsuarioCadastroDto usuarioCadastroDto) {

        return usuarioService.buscarPorEmail(email, usuarioCadastroDto);
    }

    @GetMapping("/usuarios")
    @ResponseStatus(HttpStatus.OK)
    public Page<UsuarioExibicaoDto> listarTodosOsUsuarios(Pageable paginacao) {

        return usuarioService.listarTodosOsUsuarios(paginacao);
    }

    @DeleteMapping("/usuarios/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirUsuario(@PathVariable Long id) {

        usuarioService.excluirUsuario(id);
    }

    @PutMapping("/usuarios/{email}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioExibicaoDto atualizarUsuario(@PathVariable String email, @Valid @RequestBody UsuarioCadastroDto usuarioCadastroDto) {

        return usuarioService.atualizarUsuario(email, usuarioCadastroDto);
    }

} //FIM