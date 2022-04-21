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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Tratamento da conexão com o banco de dados.
 *
 * @author Luis Guisso &lt;luis dot guisso at ifnmg dot edu dot br&gt;
 * @version 0.2, 19/04/2022
 */
public class ConexaoBd {

    // Retém a conexão estabelecida com o banco de dados durante a operação do sistema.
    private static Connection conexao;

    /**
     * URL de conexão com o banco de dados
     * (protocolo/sgbd/ip/porta/banco/parâmetros).
     */
    public static final String URL;

    // Usuário para acesso ao banco de dados.
    private static final String USUARIO;

    // Senha para acesso ao banco de dados.
    private static final String SENHA;

    // Inicialização de atributos estáticos.
    static {
        // Servidor Local
        URL = "jdbc:mysql://127.0.0.1:3306/sistema"
                + "?useUnicode=true"
                + "&useJDBCCompliantTimezoneShift=true"
                + "&serverTimezone=UTC"
                + "&autoReconnect=true";
        USUARIO = "root";
        SENHA = "";
    }

    //<editor-fold defaultstate="collapsed" desc="Construtor privado">
    /*
     * Construtor privado para forçar acesso à conexão pelo membro estático
     * getConexao() sem que seja requerida a  geração de novos objetos 
     * ConexaoBd ConexaoBd.
     */
    private ConexaoBd() {
    }
    //</editor-fold>

    /**
     * Estabelece e gera a retenção da conexão com o banco de dados.
     *
     * @return Conexão com o banco de dados.
     */
    public static Connection getConexao() {

        // Se não há uma conexão estabelecida...
        if (conexao == null) {
            // ... tenta ...
            try {
                System.out.println(">> Nova conexão estabelecida com o banco de dados");
                // ... estabelecer e reter a conexão a partir da URL, 
                // do usuário e da senha fornecidos
                conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            } catch (SQLException ex) {
                // TODO Rever procedimento e encerrar o programa em caso de falha
                // Registra falha
                Logger.getLogger(ConexaoBd.class.getName()).log(Level.SEVERE, null, ex);
                // System.exit(-1);
            }
        }

        // Devolve a conexão estabelecida
        return conexao;
    }
}
