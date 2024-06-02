package br.com.fiap.monitoramentoambiental.controller;

import br.com.fiap.monitoramentoambiental.dto.PrevisaoChuvaCadastroDto;
import br.com.fiap.monitoramentoambiental.dto.PrevisaoChuvaExibicaoDto;
import br.com.fiap.monitoramentoambiental.service.PrevisaoChuvaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
public class PrevisaoChuvaController {

    @Autowired
    private PrevisaoChuvaService previsaoChuvaService;

    @PostMapping("/previsoes-chuva")
    @ResponseStatus(HttpStatus.CREATED)
    public PrevisaoChuvaExibicaoDto cadastrarPrevisaoChuva(@Valid @RequestBody PrevisaoChuvaCadastroDto previsaoChuvaCadastroDto) {

            return previsaoChuvaService.cadastrarPrevisaoChuva(previsaoChuvaCadastroDto);
    }

    @GetMapping("/previsoes-chuva/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PrevisaoChuvaExibicaoDto buscarPrevisaoChuvaPorId(@PathVariable Long id) {

        return previsaoChuvaService.buscarPrevisaoChuvaPorId(id);
    }

    @PutMapping("/previsoes-chuva/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PrevisaoChuvaExibicaoDto> atualizarPrevisaoChuva( @PathVariable Long id,@Valid @RequestBody PrevisaoChuvaCadastroDto previsaoChuvaCadastroDto) {

        PrevisaoChuvaExibicaoDto previsaoChuvaAtualizado = previsaoChuvaService.atualizarPrevisaoChuva(id, previsaoChuvaCadastroDto);

        return ResponseEntity.ok(previsaoChuvaAtualizado);
    }

    @DeleteMapping("/previsoes-chuva/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> excluirPrevisaoChuva(@PathVariable Long id) {

        try{
            previsaoChuvaService.excluirPrevisaoChuva(id);

            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {

            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/previsoes-chuva")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<PrevisaoChuvaExibicaoDto>> listarPrevisoesChuva() {

        List<PrevisaoChuvaExibicaoDto> previsaoChuvaDto = previsaoChuvaService.listarTodasPrevisaoChuva();

        return ResponseEntity.ok(previsaoChuvaDto);
    }

} //FIM