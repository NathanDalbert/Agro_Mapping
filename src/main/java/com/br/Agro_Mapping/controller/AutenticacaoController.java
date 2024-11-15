package com.br.Agro_Mapping.controller;

import com.br.Agro_Mapping.dto.authentication.AutenticationDTO;
import com.br.Agro_Mapping.dto.authentication.RegisterDTO;
import com.br.Agro_Mapping.dto.request.UsuarioRequestDTO;
import com.br.Agro_Mapping.infra.security.LoginResponseDTO;
import com.br.Agro_Mapping.infra.security.TokenService;
import com.br.Agro_Mapping.model.Usuario;
import com.br.Agro_Mapping.repository.UsuarioRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")

@RequiredArgsConstructor
public class AutenticacaoController {


    private final AuthenticationManager authenticationManager;

    private final UsuarioRepository usuarioRepository;

    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AutenticationDTO data) {
        var username = new UsernamePasswordAuthenticationToken(data.email(), data.senha());

        if (username == null) {
            return ResponseEntity.badRequest().body("Usuário não encontrado.");
        }
        var usuario = usuarioRepository.findByEmail(data.email()).get();
        var auth = authenticationManager.authenticate(username);

        boolean isPasswordMatch = passwordEncoder.matches(data.senha(), usuario.getSenha());


        if (!isPasswordMatch) {
            return ResponseEntity.badRequest().body("Senha incorreta.");
        }

        var token = tokenService.generateToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {

        if (this.usuarioRepository.findByEmail(data.email()).isPresent()) {
            return ResponseEntity.badRequest().body("Email já está em uso.");
        }


        if (data == null || data.dataDeNascimento() == null) {
            return ResponseEntity.badRequest().body("Nome e data de nascimento são obrigatórios.");
        }

        if (data.senha() == null) {
            return ResponseEntity.badRequest().body("A senha não pode ser nula.");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());

        Usuario user = new Usuario();
        user.setNome(data.nome());
        user.setEmail(data.email());
        user.setSenha(encryptedPassword);
        user.setDataDeNascimento(data.dataDeNascimento());
        user.setUserRole(data.userRole());


        this.usuarioRepository.save(user);

        return ResponseEntity.ok("Usuário registrado com sucesso.");
    }


}
