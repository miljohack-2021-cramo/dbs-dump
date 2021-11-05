package no.hei.mamma;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Egenskaper {

    public static final String EGENSKAP_DUMPFIL = "dumpfil";
    public static final String EGENSKAP_SOCKET_URL = "socketUrl";
    private static String FILNAVN = "config.properties";

    public static Properties lesEgenskaper() throws FileNotFoundException {
        final Properties properties = new Properties();
        final InputStream inputStream = Egenskaper.class.getClassLoader().getResourceAsStream(FILNAVN);

        if (inputStream != null) {
            try {
                properties.load(inputStream);
            } catch (IOException e) {
                throw new FileNotFoundException("Kunne ikke lese inn config");
            }
        }
        return properties;
    }

}
