package br.com.fiap.monitoramentoambiental.service;

import br.com.fiap.monitoramentoambiental.dto.SensorCadastroDto;
import br.com.fiap.monitoramentoambiental.dto.SensorExibicaoDto;
import br.com.fiap.monitoramentoambiental.exception.SensorNaoEncontradoException;
import br.com.fiap.monitoramentoambiental.model.PrevisaoChuva;
import br.com.fiap.monitoramentoambiental.model.Sensor;
import br.com.fiap.monitoramentoambiental.repository.SensorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SensorService {

    @Autowired
    private SensorRepository sensorRepository;

    public SensorExibicaoDto cadastrarSensor(SensorCadastroDto sensorCadastroDto) {

        Sensor sensor = new Sensor();
        BeanUtils.copyProperties(sensorCadastroDto, sensor);

        return new SensorExibicaoDto(sensorRepository.save(sensor));
    }

    public SensorExibicaoDto buscarSensorPorId(Long id) {

        Optional<Sensor> sensorOptional = sensorRepository.findById(id);

        if (sensorOptional.isPresent()){
            return new SensorExibicaoDto(sensorOptional.get());
        }else {
            throw new SensorNaoEncontradoException("Sensor não encontrado");
        }
    }

    public SensorExibicaoDto atualizarSensor(Long id, SensorCadastroDto sensorCadastroDto) {

        Sensor sensor = new Sensor();
        BeanUtils.copyProperties(sensorCadastroDto, sensor);

        Optional<Sensor> sensorOptional = sensorRepository.findById(id);

        if (sensorOptional.isPresent()) {

            Sensor sensorExistente = sensorOptional.get();

            sensorExistente.setTipoSensor(sensor.getTipoSensor());
            sensorExistente.setLocalizacao(sensor.getLocalizacao());

            sensorExistente = sensorRepository.save(sensorExistente);

            return new SensorExibicaoDto(
                    sensorExistente.getIdSensor(),
                    sensorExistente.getTipoSensor(),
                    sensorExistente.getLocalizacao(),
                    sensorExistente.getLeituras()
            );
        } else {
            throw new NoSuchElementException("Sensor não encontrado para atualização");
        }
    }

    public void excluirSensor(Long id) {
        Optional<Sensor> sensorOptional = sensorRepository.findById(id);
        if (sensorOptional.isPresent()) {
            sensorRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("Sensor não encontrado para o ID: " + id);
        }
    }

    public List<SensorExibicaoDto> listarTodosSensores() {

        List<Sensor> sensores = sensorRepository.findAll();

        return sensores.stream()
                .map(SensorExibicaoDto::new)
                .collect(Collectors.toList());
    }

} // FIM