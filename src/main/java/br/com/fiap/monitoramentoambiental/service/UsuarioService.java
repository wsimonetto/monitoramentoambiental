package br.com.fiap.monitoramentoambiental.service;

import br.com.fiap.monitoramentoambiental.dto.UsuarioCadastroDto;
import br.com.fiap.monitoramentoambiental.dto.UsuarioExibicaoDto;
import br.com.fiap.monitoramentoambiental.model.Usuario;
import br.com.fiap.monitoramentoambiental.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioExibicaoDto cadastrarUsuario(UsuarioCadastroDto usuarioCadastroDto) {

        String senhaCriptografada = new BCryptPasswordEncoder().encode(usuarioCadastroDto.senha());
        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioCadastroDto, usuario);
        usuario.setSenha(senhaCriptografada);
        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return new UsuarioExibicaoDto(usuarioRepository.save(usuarioSalvo));
    }

    public UsuarioExibicaoDto buscarPorEmail(String email, UsuarioCadastroDto usuarioCadastroDto) {

        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioCadastroDto, usuario);

        Usuario usuarioExistente = (Usuario) usuarioRepository.findByEmail(email);

        if (usuarioExistente != null) {
            return new UsuarioExibicaoDto(usuarioExistente);
        } else {
            throw new UsernameNotFoundException("Usuário não encontrado!");
        }
    }

    public Page<UsuarioExibicaoDto> listarTodosOsUsuarios(Pageable paginacao) {

        return usuarioRepository
                .findAll(paginacao)
                .map(UsuarioExibicaoDto::new);
    }

    public void excluirUsuario(Long id) {

        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);

        if (usuarioExistente.isPresent()) {

            usuarioRepository.deleteById(id);
        } else {
            throw new RuntimeException("Usuário não encontrado!");
        }
    }

    public UsuarioExibicaoDto atualizarUsuario(String email, UsuarioCadastroDto usuarioCadastroDto) {

        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioCadastroDto, usuario);

        Usuario usuarioExistente = (Usuario) usuarioRepository.findByEmail(email);

        if (usuarioExistente != null) {

            String senhaCriptografada = new BCryptPasswordEncoder().encode(usuario.getSenha());

            usuarioExistente.setSenha(senhaCriptografada);
            usuarioExistente.setNome(usuario.getNome());
            usuarioExistente.setEmail(usuario.getEmail());
            usuarioExistente.setSenha(senhaCriptografada);
            usuarioExistente.setRole(usuario.getRole());

            Usuario usuarioAtualizado = usuarioRepository.save(usuarioExistente);

            return new UsuarioExibicaoDto(usuarioAtualizado);
        } else {
            throw new NoSuchElementException("Usuário não encontrado para atualização para o E-mail: " + email);
        }
    }

} //FIM