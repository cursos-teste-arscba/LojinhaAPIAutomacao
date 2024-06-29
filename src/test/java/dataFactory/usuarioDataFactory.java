package dataFactory;

import pojo.UsuarioPojo;

public class usuarioDataFactory {
    public static UsuarioPojo criaUsuarioComumComSenhaA(String usuarioSenha) {
        UsuarioPojo usuario = new UsuarioPojo();
        usuario.setUsuarioLogin("andersonrezendeptg");
        usuario.setUsuarioSenha("anderson123");

        return usuario;
    }
}
