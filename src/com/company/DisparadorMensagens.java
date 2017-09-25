package com.company;

import java.io.InputStream;
import java.util.Scanner;

public class DisparadorMensagens implements Runnable {

    private InputStream mFluxoMensagensParaClientes;
    private Servidor mServer;

    public DisparadorMensagens(InputStream fluxoMensagensParaClientes, Servidor server) {
        mFluxoMensagensParaClientes = fluxoMensagensParaClientes;
        mServer = server;
    }

    @Override
    public void run() {
        Scanner s = new Scanner(mFluxoMensagensParaClientes);
        while(s.hasNextLine()) {
            mServer.dispara(s.nextLine());
        }
        s.close();
    }
}
