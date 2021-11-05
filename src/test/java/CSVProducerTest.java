import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class CSVProducerTest {

    @Test
    public void skal_oversette_json_til_csv() throws Exception {
        JSONObject jo = new JSONObject();
        jo.put("id", "1");
        jo.put("lat", "1.1234");
        jo.put("lon", "2.1234");
        jo.put("db", "60");

        String csv = CSVProducer.oversettTilCSV(jo, 123456789L);
        assertEquals("123456789;A1;60;2.1234;1.1234", csv);
    }

    @Test
    public void skal_oversette_csv_til_fem_kolonner() throws Exception {
        JSONObject jo = new JSONObject();
        jo.put("id", "1");
        jo.put("lat", "1.1234");
        jo.put("lon", "2.1234");
        jo.put("db", "60");

        String csv = CSVProducer.oversettTilCSV(jo, 123456789L);
        assertEquals(5, csv.split(";").length);
    }

    @Test
    public void skal_oversette_json_til_csv_selv_om_attributt_mangler() throws Exception {
        JSONObject jo = new JSONObject();
        jo.put("id", "2");
        jo.put("db", "60");

        String csv = CSVProducer.oversettTilCSV(jo, 123456789L);
        assertEquals("123456789;A2;60;;", csv);
    }

    @Test
    public void skal_prefixe_id_med_a_etter_oversetting() throws Exception {
        JSONObject jo = new JSONObject();
        jo.put("id", "99");

        String csv = CSVProducer.oversettTilCSV(jo, 123456789L);
        assertEquals("A99", csv.split(";")[1]);
    }

    @Test(expected = NullPointerException.class)
    public void skal_gi_feil_hvis_timestamp_mangler() throws Exception {
        JSONObject jo = new JSONObject();
        CSVProducer.oversettTilCSV(jo, null);
    }


}