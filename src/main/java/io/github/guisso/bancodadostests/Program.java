package io.github.guisso.bancodadostests;

import io.github.guisso.bancodadostests.entity.Usuario;
import io.github.guisso.bancodadostests.repository.UsuarioDao;

/**
 * Ponto de entrada no sistema.
 *
 * @author Luis Guisso &lt;luis dot guisso at ifnmg dot edu dot br&gt;
 * @version 0.1, 18/04/2022
 */
public class Program {

    public static void main(String[] args) {

        Usuario u = new Usuario();
        u.setNomeSistema("cecilia@mail.com");
        u.setSenha("asdf123");
        u.setAdministrador(Boolean.TRUE);

        Long novaId = new UsuarioDao().salvar(u);
        System.out.println(">> " + novaId);

    }
}
