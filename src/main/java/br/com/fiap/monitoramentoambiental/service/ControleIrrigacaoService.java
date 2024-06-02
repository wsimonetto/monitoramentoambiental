package br.com.fiap.monitoramentoambiental.service;

import br.com.fiap.monitoramentoambiental.dto.ControleIrrigacaoCadastroDto;
import br.com.fiap.monitoramentoambiental.dto.ControleIrrigacaoExibicaoDto;
import br.com.fiap.monitoramentoambiental.exception.ControleIrrigacaoNaoEncontradoException;
import br.com.fiap.monitoramentoambiental.model.ControleIrrigacao;
import br.com.fiap.monitoramentoambiental.model.PrevisaoChuva;
import br.com.fiap.monitoramentoambiental.repository.ControleIrrigacaoRepository;
import br.com.fiap.monitoramentoambiental.repository.PrevisaoChuvaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ControleIrrigacaoService {

    @Autowired
    private ControleIrrigacaoRepository controleIrrigacaoRepository;

    @Autowired
    private PrevisaoChuvaRepository previsaoChuvaRepository;

    public ControleIrrigacaoExibicaoDto cadastrarControleIrrigacao(ControleIrrigacaoCadastroDto controleIrrigacaoCadastroDto) {

        ControleIrrigacao controleIrrigacao = new ControleIrrigacao();
        BeanUtils.copyProperties(controleIrrigacaoCadastroDto, controleIrrigacao);

        if (controleIrrigacao.getPrevisaoChuva() == null || controleIrrigacao.getPrevisaoChuva().getIdPrevisao() == null) {

            throw new IllegalArgumentException("ID da previsão de chuva não especificado para o Controle de Irrigação.");
        }

        Long idPrevisao = controleIrrigacao.getPrevisaoChuva().getIdPrevisao();

        Optional<PrevisaoChuva> optionalPrevisaoChuva = previsaoChuvaRepository.findById(idPrevisao);

        PrevisaoChuva previsaoChuva = optionalPrevisaoChuva.orElseThrow(() -> new NoSuchElementException("Previsão de chuva não encontrada para o ID especificado."));

        controleIrrigacao.setPrevisaoChuva(previsaoChuva);

        return new ControleIrrigacaoExibicaoDto(controleIrrigacaoRepository.save(controleIrrigacao));
    }

    public ControleIrrigacaoExibicaoDto buscarControleIrrigacaoPorId(Long id) {

        Optional<ControleIrrigacao> controleIrrigacaoOptional = controleIrrigacaoRepository.findById(id);

        if (controleIrrigacaoOptional.isPresent()) {

            return new ControleIrrigacaoExibicaoDto(controleIrrigacaoOptional.get());
        } else {
            throw new ControleIrrigacaoNaoEncontradoException("Controle de Irrigação não encontrado");
        }
    }

    public ControleIrrigacaoExibicaoDto atualizarControleIrrigacao(Long id, ControleIrrigacaoCadastroDto controleIrrigacaoCadastroDto) {

        ControleIrrigacao controleIrrigacao = new ControleIrrigacao();
        BeanUtils.copyProperties(controleIrrigacaoCadastroDto, controleIrrigacao);

        Optional<ControleIrrigacao> controleIrrigacaoOptional = controleIrrigacaoRepository.findById(id);

        if (controleIrrigacaoOptional.isPresent()) {

            ControleIrrigacao controleIrrigacaoExistente = controleIrrigacaoOptional.get();

            controleIrrigacaoExistente.setLocalizacao(controleIrrigacao.getLocalizacao());
            controleIrrigacaoExistente.setEstado(controleIrrigacao.getEstado());
            controleIrrigacaoExistente.setDataHora(controleIrrigacao.getDataHora());

            PrevisaoChuva previsaoChuvaExistente = previsaoChuvaRepository.findById(controleIrrigacao.getIdPrevisao())
                    .orElseThrow(() -> new NoSuchElementException("Previsão Chuva não encontrada"));

            controleIrrigacaoExistente.setIdControle(previsaoChuvaExistente.getIdPrevisao());

            controleIrrigacaoExistente = controleIrrigacaoRepository.save(controleIrrigacao);

            return new ControleIrrigacaoExibicaoDto(
                    controleIrrigacaoExistente.getIdControle(),
                    controleIrrigacaoExistente.getLocalizacao(),
                    controleIrrigacaoExistente.getEstado(),
                    controleIrrigacaoExistente.getDataHora(),
                    controleIrrigacaoExistente.getPrevisaoChuva()
            );
        } else {
            throw new NoSuchElementException("Controle Irrigação não encontrado");
        }
    }


    public void excluirControleIrrigacao(Long id) {

        Optional<ControleIrrigacao> controleIrrigacaoOptional = controleIrrigacaoRepository.findById(id);

        if (controleIrrigacaoOptional.isPresent()) {

            controleIrrigacaoRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("Leitura não encontrada para o ID: " + id);
        }
    }

    @Transactional(readOnly = true)
    public List<ControleIrrigacao> listarTodosControlesIrrigacao() {

        return controleIrrigacaoRepository.findAll();
    }

} //FIM