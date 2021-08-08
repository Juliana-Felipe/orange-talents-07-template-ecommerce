package br.com.zupacademy.mercadolivre.cadastro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/cadastro")
public class CadastroController {
    @Autowired
    CadastroRepository cadastroRepository;
    @PostMapping
    ResponseEntity insereCadastro(@RequestBody @Valid CadastroRequest cadastroRequest){
        Cadastro novoCadastro = cadastroRequest.converte();
        cadastroRepository.save(novoCadastro);
        return ResponseEntity.ok().build();
    }
}
