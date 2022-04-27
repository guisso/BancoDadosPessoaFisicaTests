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
 * @version 0.2, 27/04/2022
 */
public abstract class Dao<E, K>
        implements IDao<E, K> {

    @Override
    public K salvar(E e) {

        // Chave primária de resposta
        Long id = 0L;

        if (((Entidade) e).getId() == null
                || ((Entidade) e).getId() == 0) {
            // Inserir novo registro
            // try-with-resources
            try ( PreparedStatement preparedStatement
                    = ConexaoBd.getConexao().prepareStatement(
                            obterSentencaInsert(),
                            Statement.RETURN_GENERATED_KEYS)) {

                // Monta a declaração SQL com os dados (->?)
                montarDeclaracao(preparedStatement, e);

                // Exibe a sentença completa
                System.out.println("SQL: " + preparedStatement);

                // Executa a inserção no banco de dados
                preparedStatement.executeUpdate();

                // Recupera a chave primária gerada
                ResultSet resultSet = preparedStatement.getGeneratedKeys();

                // Movimenta para o primeiro dado recuperado
                if (resultSet.next()) {

                    // Recupera a chave primária retornada
                    id = resultSet.getLong(1);
                }

            } catch (Exception ex) {
                System.out.println(">> " + ex);
            }

        } else {
            // Atualizar registro existente
            try ( PreparedStatement preparedStatement
                    = ConexaoBd.getConexao().prepareStatement(
                            obterSentencaUpdate())) {

                // Monta a declaração SQL com os dados (->?)
                montarDeclaracao(preparedStatement, e);

                // Exibe a sentença completa
                System.out.println(">> " + preparedStatement);

                // Executa a atualização no banco de dados
                preparedStatement.executeUpdate();

                // Mantém a chave primária
                id = ((Entidade) e).getId();

            } catch (Exception ex) {
                System.out.println("Exception: " + ex);
            }
        }

        return (K) id;
    }

    /**
     * Recupera um objeto do banco de dados.
     *
     * @param id Chave primária
     * @return Objeto buscado
     */
    public E localizarPorId(K id) {
        try ( PreparedStatement preparedStatement
                = ConexaoBd.getConexao().prepareStatement(obterSentencaLocalizarPorId())) {

            // Substitui respectiva id na sentença SQL
            preparedStatement.setLong(1, (Long) id);

            // Recupera os dados da consulta
            ResultSet resultSet
                    = preparedStatement.executeQuery();

            // Movimenta para o primeiro dado recuperado
            if (resultSet.next()) {

                // Extrai o objeto representado pelo registro recuperado
                return extrairObjeto(resultSet);
            }

        } catch (Exception ex) {
            System.out.println(">> " + ex);
        }

        // Caso não haja registro com a id fornecida
        return null;
    }

    /**
     * Sentença SQL específica para cada tipo de objeto a ser persistido no
     * banco de dados.
     *
     * @return Sentença SQL de inserção.
     */
    public abstract String obterSentencaInsert();

    /**
     * Sentença SQL específica para cada tipo de objeto a ser atualizado no
     * banco de dados.
     *
     * @return Sentença SQL de atualização.
     */
    public abstract String obterSentencaUpdate();

    /**
     * Sentença SQL específica para cada tipo de objeto a ser localizado no
     * banco de dados.
     *
     * @return Sentença SQL de consulta de um registro.
     */
    public abstract String obterSentencaLocalizarPorId();

    /**
     * Monta a declaração SQL com os valores contidos no objeto recebido.
     *
     * @param pstmt Consulta a ser preparada.
     * @param e Objeto com valores a serem embutidos na consulta.
     */
    public abstract void montarDeclaracao(PreparedStatement pstmt, E e);

    public abstract E extrairObjeto(ResultSet resultSet);

}
