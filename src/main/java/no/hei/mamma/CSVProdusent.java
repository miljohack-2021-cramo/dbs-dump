package no.hei.mamma;

import org.json.JSONException;
import org.json.JSONObject;

import static java.util.Objects.requireNonNull;

public class CSVProdusent {

    public static String oversettJsonMeldingTilCSV(final String melding) {
        JSONObject jo = null;
        try {
            jo = new JSONObject(melding.trim());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        long timestamp = System.currentTimeMillis();
        return oversettTilCSV(jo, timestamp);
    }

    static String oversettTilCSV(final JSONObject jo, final Long timestamp) {
        requireNonNull(timestamp, "timestamp var null, timestamp kan ikke være null.");
        return String.join(";",
                 timestamp + "",
                "A" + jo.optString("id"),
                jo.optString("db"),
                jo.optString("lon"),
                jo.optString("lat")
        );
    }

}
