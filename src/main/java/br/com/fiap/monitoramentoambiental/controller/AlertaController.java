package br.com.fiap.monitoramentoambiental.controller;

import br.com.fiap.monitoramentoambiental.dto.AlertaCadastroDto;
import br.com.fiap.monitoramentoambiental.dto.AlertaExibicaoDto;
import br.com.fiap.monitoramentoambiental.dto.LeituraCadastroDto;
import br.com.fiap.monitoramentoambiental.model.Alerta;
import br.com.fiap.monitoramentoambiental.service.AlertaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
public class AlertaController {

    @Autowired
    private AlertaService alertaService;

    @PostMapping("/alertas")
    @ResponseStatus(HttpStatus.CREATED)
    public AlertaExibicaoDto cadastrarAlerta(@Valid @RequestBody AlertaCadastroDto alertaCadastroDto) {

        return alertaService.cadastrarAlerta(alertaCadastroDto);
    }

    @GetMapping("/alertas/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AlertaExibicaoDto buscarAlertaPorId(@PathVariable Long id) {

        return alertaService.buscarAlertaPorId(id);
    }

    @PutMapping("/alertas/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AlertaExibicaoDto atualizarAlerta(@PathVariable Long id,@Valid @RequestBody AlertaCadastroDto alertaCadastroDto) {

          return alertaService.atualizarAlerta(id, alertaCadastroDto);
    }

    @DeleteMapping("/alertas/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> excluirAlerta(@PathVariable Long id) {

        try {
            alertaService.excluirAlerta(id);

            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {

            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/alertas")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<AlertaExibicaoDto>> listarTodosAlertas() {

        List<AlertaExibicaoDto> alertasDto = alertaService.listarTodosAlertas();

        return ResponseEntity.ok(alertasDto);
    }

} //FIM