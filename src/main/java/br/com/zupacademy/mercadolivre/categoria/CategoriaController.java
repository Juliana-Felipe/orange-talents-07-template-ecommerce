package br.com.zupacademy.mercadolivre.categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    CategoriaRepository categoriaRepository;

    @PostMapping
    ResponseEntity insereCategoria(@RequestBody @Valid CategoriaRequest categoriaRequest) {
        Categoria novaCategoria = categoriaRequest.converte(categoriaRepository);
        categoriaRepository.save(novaCategoria);
        return ResponseEntity.ok().build();
    }
}
