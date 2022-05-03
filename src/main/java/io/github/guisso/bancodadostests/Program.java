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
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.MaskFormatter;

/**
 * Ponto de entrada no sistema.
 *
 * @author Luis Guisso &lt;luis dot guisso at ifnmg dot edu dot br&gt;
 * @version 0.1, 18/04/2022
 */
public class Program {

    public static void main(String[] args) {

        //<editor-fold defaultstate="collapsed" desc="Testes com CPF">
        try {
//            long cpf = 123_456_789_01L;
//            long cpf =  68_129_760_32L;
            long cpf = 129_760_08L;

            // Preenche os zeros faltantes à esquerda do CPF
            Formatter cpfPad0 = new Formatter()
                    .format("%011d", cpf);

            // Cria a máscara de formatação do CPF
            MaskFormatter mf
                    = new MaskFormatter("###.###.###-##");
            mf.setValueContainsLiteralCharacters(false);

            System.out.println(">> "
                    + mf.valueToString(cpfPad0)
                    + " > "
                    + (Util.isCpfValido(cpf)
                    ? "Válido" : "Inválido")
            );

        } catch (ParseException ex) {
            Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(-1);
        }
        //</editor-fold>

        // Pessoa física para testes
        PessoaFisica pf = new PessoaFisica();
//        pf.setId(4L);
//        pf.setCpf(123_456_789_01L); // Inválido
//        pf.setCpf( 68_129_760_32L);
        pf.setCpf(    129_760_08L);
//        pf.setCpf(887_617_510_57L);
        pf.setNome("Ana Zaira");
        pf.setEmail("ana@mail.com");
        pf.setNascimento(LocalDate.of(1986, 5, 2));
        pf.setAtivo(true);

        // A instrução de salvamento requer
        // operações em banco de dados via
        // Java Database Connectivity (JDBC)
        // que deve ser uma dependência adiconada
        // ao projeto via "com.mysql"->"connector"
        // ou "mysql-connector-java"
        Long id = new PessoaFisicaDao().salvar(pf);

        // Verificando se a pessoa física foi salva
        PessoaFisica pessoaSalva
                = new PessoaFisicaDao().localizarPorId(id);

        System.out.println("-> " + pessoaSalva);

        ArrayList<PessoaFisica> pessoas
                = (ArrayList<PessoaFisica>) 
                new PessoaFisicaDao().localizarTodos();

        System.out.println("Todos os registros:");
        for (PessoaFisica pessoa : pessoas) {
            System.out.println(">> " + pessoa);
        }

    }
}
