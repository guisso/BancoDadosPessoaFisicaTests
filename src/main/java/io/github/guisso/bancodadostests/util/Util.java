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
package io.github.guisso.bancodadostests.util;

/**
 * Utilitários
 *
 * @author Luis Guisso &lt;luis dot guisso at ifnmg dot edu dot br&gt;
 * @version 0.1, 19/04/2022
 */
public class Util {

    /**
     * Verifica validade de um CPF mediante seus dígitos verificadores
     *
     * @param cpf CPF a ser validado
     * @return Validação do CPF
     * @see
     * <a href="https://www.devmedia.com.br/validando-o-cpf-em-uma-aplicacao-java/22097">Validando
     * o CPF em uma Aplicação Java</a>
     */
    public static boolean isCpfValido(Long cpf) {

        return true;
    }

    /**
     * Extrai um dígito específico do número fornecido
     *
     * @param numero Base de extração
     * @param posicao Posição do dígito a ser extraído
     * @return Dígito da posição especificada
     * @throws IllegalArgumentException Exceção lançada em caso de posição inválida
     * @see
     * <a href="https://docs.oracle.com/javase/tutorial/essential/exceptions/runtime.html">Unchecked
     * Exceptions - The Controversy</a>
     */
    public static byte obterDigito(Long numero, int posicao)
            throws IllegalArgumentException {
        
        return 0;
    }
}
