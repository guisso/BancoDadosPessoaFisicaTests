/* 
 * Material didático destinado ao curso de 
 * Programação Orientada a Objetos
 * do Bacharelado em Ciência da Computação do IFNMG 
 * - Câmpus Montes Claros.
 *
 * O uso deste material é livre e regido pela licença 
 * Creative Commons como Atribuição-NãoComercial
 * -CompartilhaIgual 4.0 Internacional:
 * http://creativecommons.org/licenses/by-nc-sa/4.0/
 */
package io.github.guisso.bancodadostests;

import io.github.guisso.bancodadostests.util.Util;
import java.time.LocalDate;

/**
 * Entidade que representa uma pessoa física.
 *
 * @author Luis Guisso &lt;luis dot guisso at ifnmg dot edu dot br&gt;
 * @version 0.1, 18/04/2022
 */
public class PessoaFisica
        extends Entidade {

    // TODO cpf foi excluído devido ao uso indireto da id herdada de Entidade. No entanto, nota-se que o salvamento de um objeto PessoaFisica parece falhar porque SEMPRE é requerido um CPF válido, ou seja, sempre há uma id e isso SEMPRE indica uma ATUALIZAÇÃO, nunca uma inclusão.
    // private Long cpf;
    private String nome;
    private String email;
    private LocalDate nascimento;
    private Boolean ativo;

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public PessoaFisica() {
    }

    public PessoaFisica(String nome, Long cpf, String email, LocalDate nascimento, Boolean ativo) {
        super(cpf);
        this.nome = nome; // set... para realizar validações
        this.email = email;
        this.nascimento = nascimento;
        this.ativo = ativo;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public Long getCpf() {
        return getId();
    }

    public void setCpf(Long cpf)
            throws IllegalArgumentException {
        // TODO Validar CPF: se inválido, disparar exceção
        // https://www.devmedia.com.br/validando-o-cpf-em-uma-aplicacao-java/22097
        if (Util.isCpfValido(cpf)) {
            setId(cpf);
        } else {
            throw new IllegalArgumentException("CPF inválido");
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome)
            throws IllegalArgumentException {
        if (nome.length() > 50) {
            throw new IllegalArgumentException("Comprimento de nome inválido");
        }
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
    //</editor-fold>

}
