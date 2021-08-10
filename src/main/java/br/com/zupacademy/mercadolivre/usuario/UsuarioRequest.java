package br.com.zupacademy.mercadolivre.usuario;

import br.com.zupacademy.mercadolivre.config.validacao.UniqueValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UsuarioRequest {
    @NotBlank
    @Email
    @UniqueValue(fieldName = "login", domainClass = Usuario.class)
    private String login;
    @NotBlank
    @Length(min = 6)
    private String senhaLimpa;

    @JsonCreator
    public UsuarioRequest(String login, String senhaLimpa) {
        this.login = login;
        this.senhaLimpa = senhaLimpa;
    }

    public Usuario converte() {

        return new Usuario(login, new SenhaLimpa(senhaLimpa));
    }
}
