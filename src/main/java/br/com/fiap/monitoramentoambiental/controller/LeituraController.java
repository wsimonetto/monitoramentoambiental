package br.com.fiap.monitoramentoambiental.controller;

import br.com.fiap.monitoramentoambiental.dto.LeituraCadastroDto;
import br.com.fiap.monitoramentoambiental.dto.LeituraExibicaoDto;
import br.com.fiap.monitoramentoambiental.service.LeituraService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
public class LeituraController {

    @Autowired
    private LeituraService leituraService;

    @PostMapping("/leituras")
    @ResponseStatus(HttpStatus.CREATED)
    public LeituraExibicaoDto cadastrarLeitura(@Valid @RequestBody LeituraCadastroDto leituraCadastroDto) {

        return leituraService.cadastrarLeitura(leituraCadastroDto);
    }

    @GetMapping("/leituras/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LeituraExibicaoDto buscarLeituraPorId(@PathVariable Long id) {

        return leituraService.buscarLeituraPorId(id);
    }

    @PutMapping("/leituras/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LeituraExibicaoDto atualizarLeitura( @PathVariable Long id,@Valid @RequestBody LeituraCadastroDto leituraCadastroDto) {

        return leituraService.atualizarLeitura(id, leituraCadastroDto);
    }

    @DeleteMapping("leituras/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> excluirLeitura(@PathVariable Long id) {

        try {
            leituraService.excluirLeitura(id);

            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {

            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/leituras")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<LeituraExibicaoDto>> listarLeituras() {

        List<LeituraExibicaoDto> leiturasDto = leituraService.buscarTodasLeituras();

        return ResponseEntity.ok(leiturasDto);
    }

} //FIM