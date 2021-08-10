package br.com.zupacademy.mercadolivre.categoria;

import br.com.zupacademy.mercadolivre.config.validacao.UniqueValue;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.Optional;

public class CategoriaRequest {

    @UniqueValue(fieldName = "nome", domainClass = Categoria.class)
    @NotBlank
    private String nome;

    @Positive
    private Long idcategoriaMae;

    public String getNome() {
        return nome;
    }

    public Long getIdcategoriaMae() {
        return idcategoriaMae;
    }

    public Categoria converte(CategoriaRepository repository) {
        if(getIdcategoriaMae()!=null){
        Optional<Categoria> possivelCategoria = repository.findById(getIdcategoriaMae());
        if (possivelCategoria.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria mãe não encontrada.");
        }
        Categoria categoriaEncontrada = possivelCategoria.get();

        return new Categoria(nome, categoriaEncontrada);
        }

        return new Categoria(nome);
    }
   }