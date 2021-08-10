package br.com.zupacademy.mercadolivre.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/cadastroUsuario")
public class UsuarioController {
    @Autowired
    UsuarioRepository usuarioRepository;
    @PostMapping
    ResponseEntity insereCadastro(@RequestBody @Valid UsuarioRequest usuarioRequest){
        Usuario novoUsuario = usuarioRequest.converte();
        usuarioRepository.save(novoUsuario);
        return ResponseEntity.ok().build();
    }
}
