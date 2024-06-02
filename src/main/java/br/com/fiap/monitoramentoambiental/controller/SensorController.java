package br.com.fiap.monitoramentoambiental.controller;

import br.com.fiap.monitoramentoambiental.dto.SensorCadastroDto;
import br.com.fiap.monitoramentoambiental.dto.SensorExibicaoDto;
import br.com.fiap.monitoramentoambiental.service.SensorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
public class SensorController {

    @Autowired
    private SensorService sensorService;

    @PostMapping("/sensores")
    @ResponseStatus(HttpStatus.CREATED)
    public SensorExibicaoDto cadastrarSensor(@Valid @RequestBody SensorCadastroDto sensorCadastroDto) {

        return sensorService.cadastrarSensor(sensorCadastroDto);
    }

    @GetMapping("/sensores/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SensorExibicaoDto buscarSensorPorId(@PathVariable Long id) {

        return sensorService.buscarSensorPorId(id);
    }

    @PutMapping("/sensores/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<SensorExibicaoDto> atualizarSensor(@PathVariable Long id, @Valid  @RequestBody SensorCadastroDto sensorCadastroDto) {

        SensorExibicaoDto sensorAtualizado = sensorService.atualizarSensor(id, sensorCadastroDto);

        return ResponseEntity.ok(sensorAtualizado);
    }

    @DeleteMapping("/sensores/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> excluirSensor(@PathVariable Long id) {

        try {
            sensorService.excluirSensor(id);

            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {

            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/sensores")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<SensorExibicaoDto>> listarTodosSensores() {

        List<SensorExibicaoDto> sensoresDto = sensorService.listarTodosSensores();

        return ResponseEntity.ok(sensoresDto);
    }

} //FIM