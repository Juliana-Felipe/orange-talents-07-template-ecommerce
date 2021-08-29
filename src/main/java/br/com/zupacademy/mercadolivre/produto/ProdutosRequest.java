package br.com.zupacademy.mercadolivre.produto;

import br.com.zupacademy.mercadolivre.categoria.Categoria;
import br.com.zupacademy.mercadolivre.categoria.CategoriaRepository;
import br.com.zupacademy.mercadolivre.config.validacao.UniqueValue;
import br.com.zupacademy.mercadolivre.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonCreator;
import net.bytebuddy.implementation.bytecode.Throw;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class ProdutosRequest {
    @NotBlank
    @UniqueValue(domainClass = Produto.class, fieldName = "nome")
    private String nome;
    @NotNull
    @Min(0)
    private BigDecimal valor;
    @Min(0)
    private int quantidade;
    @Size(min = 3)
    private Set<CaracteristicaRequest> caracteristicas;
    @NotBlank
    @Length(max = 1000)
    private String descricao;
    @NotNull
    private Long categoriaId;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public ProdutosRequest(String nome, BigDecimal valor, int quantidade, Set<CaracteristicaRequest> caracteristicas, String descricao, Long categoriaId) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.caracteristicas = caracteristicas;
        this.descricao = descricao;
        this.categoriaId = categoriaId;
    }


    public Produto converte(CategoriaRepository categoriaRepository, Usuario usuario) {
        Optional<Categoria> possivelCategoria = categoriaRepository.findById(categoriaId);
        if (possivelCategoria.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não encontrada.");
        }

        Categoria categoria = possivelCategoria.get();
        return new Produto(nome, valor, quantidade, descricao, categoria, usuario, caracteristicas);
    }


}
