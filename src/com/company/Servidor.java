package com.company;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor {

    private ServerSocket mServerSocket;
    private ArrayList<PrintStream> mCanaisClientes;

    public Servidor(int porta) throws IOException {
        mServerSocket = new ServerSocket(porta);
        mCanaisClientes = new ArrayList<>();
    }

    public void esperaConexao() throws IOException {

        Socket cliente;

        for(;;) {
            cliente = mServerSocket.accept();
            System.out.println("Nova conexão com o cliente " + cliente.getInetAddress().getHostAddress());

            PrintStream novoCanal = new PrintStream(cliente.getOutputStream());
            mCanaisClientes.add(novoCanal);

            DisparadorMensagens sender = new DisparadorMensagens(cliente.getInputStream(), this);
            new Thread(sender).start();
        }
    }

    public void dispara(String mensagem) {
        for (PrintStream canal : mCanaisClientes) {
            canal.println(mensagem);
        }
    }
}
