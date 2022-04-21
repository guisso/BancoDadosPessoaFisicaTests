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

/**
 * Operações concretas que suportam os procedimentos CRUD em objetos em banco de
 * dados.
 *
 * @author Luis Guisso &lt;luis dot guisso at ifnmg dot edu dot br&gt;
 * @version 0.1, 18/04/2022
 */
public class PessoaFisicaDao
        extends Dao<PessoaFisica, Long> {

    /*
CREATE TABLE `pessoafisica` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `email` varchar(255) NOT NULL,
  `nascimento` date DEFAULT NULL,
  `ativo` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=MyISAM AUTO_INCREMENT=0 DEFAULT CHARSET=latin1
-- */
//    
    @Override
    public String obterSentencaInsert() {
        return "insert into pessoafisica values (?, ?, ?, ?, ?);";
    }

    @Override
    public void montarDeclaracao(PreparedStatement pstmt, PessoaFisica e) {

        // -- SQLs a serem montadas "n" parâmetros:
        // insert into pessoafisica values (?, ?, ?, ?, ?);
        // update pessoafisica set nome = ?, email = ?, nascimento = ?, ativo = ? where id = ?;
//        
        // -- SQLs a serem montadas com 0/1 parâmetro
        // select id, nome, email, nascimento from pessoafisica;
        // select id, nome, email, nascimento from pessoafisica where id = ?;
        // delete from pessoafisica where id = ?;
//        
        // TODO Montar declaração SQL para "n" parâmetros
        // TODO Método específico SQL para 0/1 parâmetro
    }

}
