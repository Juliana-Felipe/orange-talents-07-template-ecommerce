package br.com.zupacademy.mercadolivre.produto;

import br.com.zupacademy.mercadolivre.categoria.Categoria;
import br.com.zupacademy.mercadolivre.config.validacao.UniqueValue;
import br.com.zupacademy.mercadolivre.usuario.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime criacao;

    @NotBlank
    @Column(unique = true)
    private String nome;

    @NotNull
    @Min(0)
    private BigDecimal valor;

    @Min(0)
    private int quantidade;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produto")
    @Size(min = 3)
    @Valid
    private Set<Caracteristica> caracteristicas = new HashSet<>();

    @NotBlank
    @Length(max = 1000)
    private String descricao;

    @ManyToOne
    @NotNull
    private Categoria categoria;

    @ManyToOne
    @NotNull
    private Usuario usuario;

    public Produto(@NotBlank String nome, @NotNull @Min(0) BigDecimal valor, @Min(0) int quantidade, @Length(max = 1000) @NotBlank String descricao, Categoria categoria, Usuario usuario, Set<CaracteristicaRequest> caracteristicas) {
        this.criacao = LocalDateTime.now();
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        Set<Caracteristica> novasCaracteristicas = caracteristicas.stream().map(caracteristica -> caracteristica.converte(this)).collect(Collectors.toSet());
        this.caracteristicas.addAll(novasCaracteristicas);
        this.descricao = descricao;
        this.categoria = categoria;
        this.usuario = usuario;


    }

    @Deprecated
    public Produto() {
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", criacao=" + criacao +
                ", nome='" + nome + '\'' +
                ", valor=" + valor +
                ", quantidade=" + quantidade +
                ", caracteristicas=" + caracteristicas +
                ", descricao='" + descricao + '\'' +
                ", categoria=" + categoria +
                ", usuario=" + usuario +
                '}';
    }
}
