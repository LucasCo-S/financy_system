package view;
import controller.*;

import java.util.Scanner;

public class App {

    public static void main(String[] args){
        System.out.println("Iniciando Programa");

        Scanner scan = new Scanner(System.in);
        Control.iniciar(scan);
        boolean continuar = true;

        while(continuar){
            continuar = Control.rodarMenu();
        }

        Control.encerrar();
    }
}
