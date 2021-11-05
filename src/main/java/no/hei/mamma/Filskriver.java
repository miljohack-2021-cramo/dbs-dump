package no.hei.mamma;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import static no.hei.mamma.Egenskaper.EGENSKAP_DUMPFIL;
import static no.hei.mamma.Egenskaper.lesEgenskaper;

public class Filskriver {

    private Properties properties;
    private final File fil;

    Filskriver() throws Exception {
        properties = lesEgenskaper();
        fil = new File(properties.getProperty(EGENSKAP_DUMPFIL, "dump.csv"));
    }

    public void append(final String linje) throws IOException {
        final FileWriter fw = new FileWriter(fil, true);
        final BufferedWriter bw = new BufferedWriter(fw);
        bw.write(linje);
        bw.newLine();
        bw.close();
    }

}
