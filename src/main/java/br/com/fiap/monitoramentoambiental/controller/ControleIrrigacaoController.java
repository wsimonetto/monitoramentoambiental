package br.com.fiap.monitoramentoambiental.controller;

import br.com.fiap.monitoramentoambiental.dto.ControleIrrigacaoCadastroDto;
import br.com.fiap.monitoramentoambiental.dto.ControleIrrigacaoExibicaoDto;
import br.com.fiap.monitoramentoambiental.model.ControleIrrigacao;
import br.com.fiap.monitoramentoambiental.service.ControleIrrigacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
public class ControleIrrigacaoController {

    @Autowired
    private ControleIrrigacaoService controleIrrigacaoService;

    @PostMapping("/controle-irrigacao")
    @ResponseStatus(HttpStatus.CREATED)
    public ControleIrrigacaoExibicaoDto cadastrarControleIrrigacao(@Valid @RequestBody ControleIrrigacaoCadastroDto controleIrrigacaoCadastroDto) {

        return controleIrrigacaoService.cadastrarControleIrrigacao(controleIrrigacaoCadastroDto);
    }

    @GetMapping("/controle-irrigacao/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ControleIrrigacaoExibicaoDto buscarControleIrrigacaoPorId(@PathVariable Long id) {

        return controleIrrigacaoService.buscarControleIrrigacaoPorId(id);
    }

    @PutMapping("/controle-irrigacao/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ControleIrrigacaoExibicaoDto atualizarControleIrrigacao( @PathVariable Long id,@Valid @RequestBody ControleIrrigacaoCadastroDto controleIrrigacaoCadastroDto) {

        return controleIrrigacaoService.atualizarControleIrrigacao(id, controleIrrigacaoCadastroDto);
    }

    @DeleteMapping("/controle-irrigacao/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> excluirControleIrrigacao(@PathVariable Long id) {

        try {
            controleIrrigacaoService.excluirControleIrrigacao(id);

            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {

            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/controle-irrigacao")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ControleIrrigacao>> listarTodosControlesIrrigacao() {

        List<ControleIrrigacao> controlesIrrigacao = controleIrrigacaoService.listarTodosControlesIrrigacao();

        return ResponseEntity.ok(controlesIrrigacao);
    }

} //FIM