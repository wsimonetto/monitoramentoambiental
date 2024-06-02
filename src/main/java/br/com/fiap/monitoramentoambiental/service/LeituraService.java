package br.com.fiap.monitoramentoambiental.service;

import br.com.fiap.monitoramentoambiental.dto.LeituraCadastroDto;
import br.com.fiap.monitoramentoambiental.dto.LeituraExibicaoDto;
import br.com.fiap.monitoramentoambiental.exception.LeituraNaoEncontradoException;
import br.com.fiap.monitoramentoambiental.model.Leitura;
import br.com.fiap.monitoramentoambiental.model.Sensor;
import br.com.fiap.monitoramentoambiental.repository.LeituraRepository;
import br.com.fiap.monitoramentoambiental.repository.SensorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LeituraService {

    @Autowired
    private LeituraRepository leituraRepository;
    @Autowired
    private SensorRepository sensorRepository;

    public LeituraExibicaoDto cadastrarLeitura(LeituraCadastroDto leituraCadastroDto) {

        Leitura leitura = new Leitura();

        BeanUtils.copyProperties(leituraCadastroDto, leitura);

        if (leitura.getSensor() == null || leitura.getSensor().getIdSensor() == null) {

            throw new IllegalArgumentException("Sensor não especificado para a leitura.");
        }

        Long idSensor = leitura.getSensor().getIdSensor();
        Optional<Sensor> optionalSensor = sensorRepository.findById(idSensor);

        Sensor sensor = optionalSensor.orElseThrow(() -> new NoSuchElementException("Sensor não encontrado para o ID especificado."));
        leitura.setSensor(sensor);

        return new LeituraExibicaoDto(leituraRepository.save(leitura));
    }

   public LeituraExibicaoDto buscarLeituraPorId(Long id) {

        Optional<Leitura> leituraOptional = leituraRepository.findById(id);

        if (leituraOptional.isPresent()){

            return new LeituraExibicaoDto(leituraOptional.get());
        }else {
            throw new LeituraNaoEncontradoException("Leitura não encontrada");
        }
    }

    public LeituraExibicaoDto atualizarLeitura(Long id, LeituraCadastroDto leituraCadastroDto) {

        Leitura leitura = new Leitura();
        BeanUtils.copyProperties(leituraCadastroDto, leitura);

        Optional<Leitura> optionalLeitura = leituraRepository.findById(id);

        if(optionalLeitura.isPresent()) {

            Leitura leituraExistente = optionalLeitura.get();

            leituraExistente.setValor(leitura.getValor());
            leituraExistente.setDataHora(leitura.getDataHora());

            Long idSensor = leitura.getSensor().getIdSensor();
            Sensor sensorExistente = sensorRepository.findById(idSensor)
                    .orElseThrow(() -> new NoSuchElementException("Sensor não encontrado"));

            leituraExistente.setSensor(sensorExistente);

            leituraExistente = leituraRepository.save(leituraExistente);

            return new LeituraExibicaoDto(
                    leituraExistente.getIdLeitura(),
                    leituraExistente.getValor(),
                    leituraExistente.getDataHora(),
                    leituraExistente.getSensor()
            );
        }else {
            throw new NoSuchElementException("Leitura não encontrada para atualização para o ID: " +id);
        }
    }

    public void excluirLeitura(Long id) {

        Optional<Leitura> leituaOptional = leituraRepository.findById(id);

        if (leituaOptional.isPresent()) {

            leituraRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("Leitura não encontrada para o ID: " + id);
        }
    }

    @Transactional(readOnly = true)
    public List<LeituraExibicaoDto> buscarTodasLeituras() {

        List<Leitura> leituras = leituraRepository.findAll();

        return leituras.stream()
                .map(LeituraExibicaoDto::new)
                .collect(Collectors.toList());
    }

} //FIM