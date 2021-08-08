package br.com.zupacademy.mercadolivre.cadastro;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class CadastroRequest {
    @NotBlank
    @Email
    private String login;
    @NotBlank
    @Length(min = 6)
    private String senhaLimpa;

    @JsonCreator
    public CadastroRequest(String login, String senhaLimpa) {
        this.login = login;
        this.senhaLimpa = senhaLimpa;
    }

    public Cadastro converte() {

        return new Cadastro(login, new SenhaLimpa(senhaLimpa));
    }
}
