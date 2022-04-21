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

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Implementação de operações gerais e definição de operações específicas para
 * <i>Data Access Object</i>.
 *
 * @author Luis Guisso &lt;luis dot guisso at ifnmg dot edu dot br&gt;
 * @version 0.1, 18/04/2022
 */
public abstract class Dao<E, K>
        implements IDao<E, K> {

    @Override
    public K salvar(E e) {

        Long id = 0L;

        // TODO id==0 não distingue se uma pessoa física é nova ou se deve ser atualizada devido ao CPF sempre ser obrigatório
        if (((Entidade) e).getId() == 0) {
            // Inserir novo registro

            try ( PreparedStatement preparedStatement
                    = ConexaoBd
                            .getConexao()
                            .prepareStatement(
                                    obterSentencaInsert(),
                                    Statement.RETURN_GENERATED_KEYS)) {

                        // montar a declaração sql com os dados (->?)
                        montarDeclaracao(preparedStatement, e);

                        preparedStatement.executeUpdate();

                        ResultSet resultSet = preparedStatement.getGeneratedKeys();

                        if (resultSet.next()) {
                            id = resultSet.getLong(1);
                        }

                    } catch (Exception ex) {
                        System.out.println(">> " + ex);
                    }

        } else {
            // Atualizar registro existente

        }

        return (K) id;
    }

    /**
     * Sentença SQL específica para cada tipo de objeto a ser persistido no
     * banco de dados.
     *
     * @return Sentença SQL de inserção.
     */
    public abstract String obterSentencaInsert();

    /**
     * Monta a declaração SQL com os valores contidos no objeto recebido.
     *
     * @param pstmt Consulta a ser preparada.
     * @param e Objeto com valores a serem embutidos na consulta.
     */
    public abstract void montarDeclaracao(PreparedStatement pstmt, E e);

}
