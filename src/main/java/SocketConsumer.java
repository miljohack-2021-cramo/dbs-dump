import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;

public class SocketConsumer {

    Socket mSocket() throws URISyntaxException {
        try {
            return IO.socket("https://www.heimamma.com");
        } catch (URISyntaxException e) {
            throw e;
        }
    }

    void connect() throws URISyntaxException {
        Socket socket = mSocket();
        socket.on("broadcast_siste_verdier", onNewMessage);
        socket.connect();
    }


    private Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            String linje = CSVProducer.oversettJsonMeldingTilCSV(args[0].toString());
            try {
                append(linje);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    public static void main(String[] args) throws URISyntaxException {
        SocketConsumer ls = new SocketConsumer();
        ls.connect();
    }

    public void append(final String linje) throws IOException {
        File file = new File("dump.csv");
        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(linje);
        bw.newLine();
        bw.close();
    }

}