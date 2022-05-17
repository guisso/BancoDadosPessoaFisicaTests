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
package io.github.guisso.bancodadostests.repository;

import io.github.guisso.bancodadostests.entity.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe UsuarioDao
 *
 * <code>
 * CREATE TABLE `usuario` (
 * `id` bigint(20) NOT NULL AUTO_INCREMENT,
 * `nomesistema` varchar(50) NOT NULL,
 * `senha` char(32) NOT NULL,
 * `admin` tinyint(1) DEFAULT '0', -- Administrador?
 * PRIMARY KEY (`id`),
 * UNIQUE KEY `nomesistema` (`nomesistema`)
 * )
 * </code>
 *
 * @author Luis Guisso &lt;luis dot guisso at ifnmg dot edu dot br&gt;
 * @version 0.1, 16/05/2022
 *
 */
public class UsuarioDao
        extends Dao<Usuario, Long> {

    @Override
    public String obterSentencaInsert() {
        return "insert into usuario (id, nomesistema, senha, admin) values (default, ?, md5(concat('1FnM6', ?)), ?);";
    }

    @Override
    public String obterSentencaUpdate() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String obterSentencaLocalizarPorId() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String obterSentencaLocalizarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void montarDeclaracao(PreparedStatement pstmt, Usuario e) {

        try {
            pstmt.setString(1, e.getNomeSistema());
            pstmt.setString(2, e.getSenha());
            pstmt.setBoolean(3, e.getAdministrador());
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        // TODO Implementar atualizar: depende da respectiva SQL
        if (e.getId() != null && e.getId() != 0) {

        }
    }

    @Override
    public Usuario extrairObjeto(ResultSet resultSet) {
        Usuario resposta = new Usuario();

        // TODO Gerar objeto a partir do resultSet
        resposta.setId(null);
        resposta.setNomeSistema(null);
        resposta.setSenha(null);  // Desejável???
        resposta.setAdministrador(null);

        return resposta;
    }

    public Usuario autenticar(Usuario usuario) {

        Usuario resposta = null;

        // Validação de usuario
        try ( PreparedStatement preparedStatement
                = ConexaoBd.getConexao().prepareStatement("select * from usuario where nomesistema = '?' and senha = md5(concat('1FnM6', ?));")) {

            preparedStatement.setString(1, usuario.getNomeSistema());
            preparedStatement.setString(2, usuario.getSenha());

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

        return resposta;
    }

}
