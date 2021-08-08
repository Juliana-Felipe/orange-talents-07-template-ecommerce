package br.com.zupacademy.mercadolivre.cadastro;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

@Entity
public class Cadastro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @PastOrPresent
    private LocalDateTime data = LocalDateTime.now();

    @Email
    @NotBlank
    @Column(unique = true)
    private String login;

    @Length(min = 6)
    @NotBlank
    private String senhaCriptografada;

    public Cadastro( @Email @NotBlank String login,@Valid SenhaLimpa senhaLimpa) {
        this.login = login;
        this.senhaCriptografada = senhaLimpa.encoda();
    }
    @Deprecated
    public Cadastro() {
    }
}
