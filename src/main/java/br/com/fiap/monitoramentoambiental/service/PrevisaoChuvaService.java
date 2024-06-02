package br.com.fiap.monitoramentoambiental.service;

import br.com.fiap.monitoramentoambiental.dto.PrevisaoChuvaCadastroDto;
import br.com.fiap.monitoramentoambiental.dto.PrevisaoChuvaExibicaoDto;
import br.com.fiap.monitoramentoambiental.exception.PrevisaoChuvaNaoEncontradoException;
import br.com.fiap.monitoramentoambiental.model.PrevisaoChuva;
import br.com.fiap.monitoramentoambiental.repository.PrevisaoChuvaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PrevisaoChuvaService {

    @Autowired
    private PrevisaoChuvaRepository previsaoChuvaRepository;

    public PrevisaoChuvaExibicaoDto cadastrarPrevisaoChuva(PrevisaoChuvaCadastroDto previsaoChuvaCadastroDto) {

        PrevisaoChuva previsaoChuva = new PrevisaoChuva();

        BeanUtils.copyProperties(previsaoChuvaCadastroDto, previsaoChuva);

        return new PrevisaoChuvaExibicaoDto(previsaoChuvaRepository.save(previsaoChuva));
    }

    public PrevisaoChuvaExibicaoDto buscarPrevisaoChuvaPorId(Long id) {

        Optional<PrevisaoChuva> previsaoChuvaOptional = previsaoChuvaRepository.findById(id);

        if (previsaoChuvaOptional.isPresent()){

            return new PrevisaoChuvaExibicaoDto(previsaoChuvaOptional.get());
        }else{
            throw new PrevisaoChuvaNaoEncontradoException("Previsao de Chuva não encontrada");
        }
    }

    public PrevisaoChuvaExibicaoDto atualizarPrevisaoChuva(Long id, PrevisaoChuvaCadastroDto previsaoChuvaCadastroDto) {

        PrevisaoChuva previsaoChuva = new PrevisaoChuva();

        BeanUtils.copyProperties(previsaoChuvaCadastroDto, previsaoChuva);

        Optional<PrevisaoChuva> optionalPrevisaoChuva = previsaoChuvaRepository.findById(id);

        if (optionalPrevisaoChuva.isPresent()) {

            PrevisaoChuva previsaoChuvaExistente = optionalPrevisaoChuva.get();

            previsaoChuvaExistente.setDataPrevisao(previsaoChuva.getDataPrevisao());
            previsaoChuvaExistente.setPrevisao(previsaoChuva.getPrevisao());

            previsaoChuvaExistente = previsaoChuvaRepository.save(previsaoChuvaExistente);

            return new PrevisaoChuvaExibicaoDto(
                    previsaoChuvaExistente.getIdPrevisao(),
                    previsaoChuvaExistente.getDataPrevisao(),
                    previsaoChuvaExistente.getPrevisao()
            );
        } else {
            throw new NoSuchElementException("Previsao de Chuva não encontrado para atualização");
        }
    }

    public void excluirPrevisaoChuva(Long id) {

        Optional<PrevisaoChuva> previsaoChuvaOptional = previsaoChuvaRepository.findById(id);

        if (previsaoChuvaOptional.isPresent()) {

            previsaoChuvaRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("Previsão de chuva não encontrada para o ID: " + id);
        }
    }

    public List<PrevisaoChuvaExibicaoDto> listarTodasPrevisaoChuva() {

        List<PrevisaoChuva> previsoesChuva = previsaoChuvaRepository.findAll();

        return previsoesChuva.stream()
                .map(PrevisaoChuvaExibicaoDto::new)
                .collect(Collectors.toList());
    }

} //FIM