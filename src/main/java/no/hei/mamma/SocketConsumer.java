package no.hei.mamma;

import io.socket.client.IO;
import io.socket.client.Socket;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import static no.hei.mamma.Egenskaper.*;

public class SocketConsumer {

    private Properties properties;
    private Filskriver filskriver;

    SocketConsumer() throws Exception {
        properties = lesEgenskaper();
        filskriver = new Filskriver();
    }

    private Socket opprettSocket() throws Exception {
        return IO.socket(properties.getProperty(EGENSKAP_SOCKET_URL));
    }

    void start() throws Exception {
        final Socket socket = opprettSocket();
        socket.on("broadcast_siste_verdier", args -> {
            String linje = CSVProdusent.oversettJsonMeldingTilCSV(args[0].toString());
            try {
                filskriver.append(linje);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        socket.connect();
    }

    public static void main(String[] args) throws Exception {
        final SocketConsumer ls = new SocketConsumer();
        ls.start();
    }



}