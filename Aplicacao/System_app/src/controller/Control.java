package controller;

import model.*;
import java.sql.Connection;

public class Control {
    protected static Connection conexao = null;

    public static void iniciar(){
        conexao = Model.connection_db();
    }

    public static void encerrar(){
        Model.close_connection(conexao);
    }
}
