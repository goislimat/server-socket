package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {

            Servidor server = new Servidor(12345);
            System.out.println("Porta aberta!");
            server.esperaConexao();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
