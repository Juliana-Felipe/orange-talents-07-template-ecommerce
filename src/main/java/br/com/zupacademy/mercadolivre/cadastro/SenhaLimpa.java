package br.com.zupacademy.mercadolivre.cadastro;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotNull;

public class SenhaLimpa {
    @Length(min = 6)
    @NotNull
    private String senhaTextoPuro;

    public SenhaLimpa(@Length(min = 6) @NotNull String senhaTextoPuro) {
        this.senhaTextoPuro = senhaTextoPuro;
    }

    public String encoda() {
        return new BCryptPasswordEncoder().encode(senhaTextoPuro);
    }
}
