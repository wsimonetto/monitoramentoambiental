package br.com.fiap.monitoramentoambiental.service;

import br.com.fiap.monitoramentoambiental.dto.AlertaCadastroDto;
import br.com.fiap.monitoramentoambiental.dto.AlertaExibicaoDto;
import br.com.fiap.monitoramentoambiental.exception.AlertaNaoEncontradoException;
import br.com.fiap.monitoramentoambiental.model.Alerta;
import br.com.fiap.monitoramentoambiental.repository.AlertaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlertaService {

    @Autowired
    private AlertaRepository alertaRepository;

    public AlertaExibicaoDto cadastrarAlerta(AlertaCadastroDto alertaCadastroDto) {

        Alerta alerta = new Alerta();
        BeanUtils.copyProperties(alertaCadastroDto, alerta);

        return new AlertaExibicaoDto(alertaRepository.save(alerta));
    }

    public AlertaExibicaoDto buscarAlertaPorId(Long id) {

        Optional<Alerta> alertaOptional = alertaRepository.findById(id);

        if (alertaOptional.isPresent()) {
            return new AlertaExibicaoDto(alertaOptional.get());
        } else {
            throw new AlertaNaoEncontradoException("Alerta não encontrado");
        }
    }

    public AlertaExibicaoDto atualizarAlerta(Long id, AlertaCadastroDto alertaCadastroDto) {

        Alerta alerta = new Alerta();
        BeanUtils.copyProperties(alertaCadastroDto, alerta);

        Optional<Alerta> optionalAlerta = alertaRepository.findById(id);

        if (optionalAlerta.isPresent()) {

            Alerta alertaExistente = optionalAlerta.get();

            alertaExistente.setTipoAlerta(alerta.getTipoAlerta());
            alertaExistente.setDescricao(alerta.getDescricao());
            alertaExistente.setDataHora(alerta.getDataHora());

            alertaExistente = alertaRepository.save(alertaExistente);

            return new AlertaExibicaoDto(
                    alertaExistente.getIdAlerta(),
                    alertaExistente.getTipoAlerta(),
                    alertaExistente.getDescricao(),
                    alertaExistente.getDataHora()
            );
        } else {
            throw new NoSuchElementException("Alerta não encontrado para atualização para o ID: " +id);
        }
    }

    public void excluirAlerta(Long id) {

        Optional<Alerta> alertaOptional = alertaRepository.findById(id);

        if (alertaOptional.isPresent()){
            alertaRepository.deleteById(id);
        }else{
            throw new NoSuchElementException("Alerta não encontrado para o ID: " +id);
        }
    }

    public List<AlertaExibicaoDto> listarTodosAlertas() {

        List<Alerta> alertas = alertaRepository.findAll();

        return alertas.stream()
                .map(AlertaExibicaoDto::new)
                .collect(Collectors.toList());
    }

} //FIM