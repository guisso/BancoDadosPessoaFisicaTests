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

/**
 * Definição de métodos obrigatórios nas classes DAO.
 *
 * @author Luis Guisso &lt;luis dot guisso at ifnmg dot edu dot br&gt;
 * @version 0.1, 18/04/2022
 */
public interface IDao<E, K> {

    /**
     * Operação de salvamento de entidade no banco de dados
     *
     * @param e Entidade a ser salva
     * @return Chave primária da entidade salva
     */
    public K salvar(E e);
}
