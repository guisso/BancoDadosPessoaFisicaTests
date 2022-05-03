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

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Operações concretas que suportam os procedimentos CRUD em objetos em banco de
 * dados.
 *
 * @author Luis Guisso &lt;luis dot guisso at ifnmg dot edu dot br&gt;
 * @version 0.2, 27/04/2022
 */
public class PessoaFisicaDao
        extends Dao<PessoaFisica, Long> {

    /*
    CREATE TABLE `pessoafisica` (
        `id` bigint(20) NOT NULL AUTO_INCREMENT,
        `cpf` bigint(20) NOT NULL,
        `nome` varchar(50) NOT NULL,
        `email` varchar(255) DEFAULT NULL,
        `nascimento` date DEFAULT NULL,
        `ativo` tinyint(1) DEFAULT '1',
        PRIMARY KEY (`id`),
        UNIQUE KEY `cpf` (`cpf`),
        UNIQUE KEY `email` (`email`)
    ) ENGINE=MyISAM DEFAULT CHARSET=latin1
-- */
//    
    @Override
    public String obterSentencaInsert() {
        return "insert into pessoafisica (cpf, nome, email, nascimento, ativo) values (?, ?, ?, ?, ?);";
    }

    @Override
    public String obterSentencaUpdate() {
        return "update pessoafisica set cpf = ?, nome = ?, email = ?, nascimento = ?, ativo = ? where id = ?;";
    }

    @Override
    public String obterSentencaLocalizarPorId() {
        return "select id, cpf, nome, email, nascimento, ativo from pessoafisica where id = ?;";
    }

    @Override
    public String obterSentencaLocalizarTodos() {
        return "select id, cpf, nome, email, nascimento, ativo from pessoafisica where excluido = false;";
    }

    @Override
    public void montarDeclaracao(PreparedStatement pstmt, PessoaFisica e) {

        // -- SQLs a serem montadas "n" parâmetros:
        // insert into pessoafisica (cpf, nome, email, nascimento, ativo) values (?, ?, ?, ?, ?);
        // update pessoafisica set cpf = ?, nome = ?, email = ?, nascimento = ?, ativo = ? where id = ?;
//        
        // TODO Montar declaração SQL para "n" parâmetros
        try {
            if (e.getId() == null || e.getId() == 0) {
                pstmt.setLong(1, e.getCpf());
                pstmt.setString(2, e.getNome());
                pstmt.setString(3, e.getEmail());
                // https://www.baeldung.com/java-convert-localdate-sql-date
                pstmt.setDate(4, Date.valueOf(e.getNascimento()));
                pstmt.setBoolean(5, e.getAtivo());
            } else {
                pstmt.setLong(1, e.getCpf());
                pstmt.setString(2, e.getNome());
                pstmt.setString(3, e.getEmail());
                pstmt.setDate(4, Date.valueOf(e.getNascimento()));
                pstmt.setBoolean(5, e.getAtivo());
                pstmt.setLong(6, e.getId());
            }
        } catch (Exception ex) {
            System.out.println("Exceptoin: " + ex);
        }
    }

    //        
    // -- SQLs a serem montadas com 0/1 parâmetro
    // select id, nome, email, nascimento from pessoafisica;
    // select id, nome, email, nascimento from pessoafisica where id = ?;
    // delete from pessoafisica where id = ?;
    //
    /**
     * Extrai um objeto PessoaFisica do resultado gerado pela consulta
     *
     * @param resultSet Registro recuperado do banco de dados
     * @return PessoaFisica equivalente ao registro recebido
     */
    @Override
    public PessoaFisica extrairObjeto(ResultSet resultSet) {
        PessoaFisica pf = new PessoaFisica();

        try {
            pf.setId(resultSet.getLong("id"));
            pf.setCpf(resultSet.getLong("cpf"));
            pf.setNome(resultSet.getString("nome"));
            pf.setEmail(resultSet.getString("email"));
            pf.setNascimento(resultSet.getObject("nascimento", LocalDate.class));
            pf.setAtivo(resultSet.getBoolean("ativo"));

        } catch (SQLException ex) {
            Logger.getLogger(PessoaFisicaDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pf;
    }

}
