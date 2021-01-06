package com.example.quakeinfo;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Network {

    private static final String EARTHQUAKE_SEARCH_QUERY = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2019-01-01&endtime=2020-01-02&minmagnitude=6";
/*
    public static final String SAMPLE_JSON_RESPONSE = "{\"type\":\"FeatureCollection\",\"metadata\":{\"generated\":1609940790000,\"url\":\"https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2019-01-01&endtime=2020-01-01&minmagnitude=7\",\"title\":\"USGS Earthquakes\",\"status\":200,\"api\":\"1.10.3\",\"count\":10},\"features\":[{\"type\":\"Feature\",\"properties\":{\"mag\":7.1,\"place\":\"138km E of Bitung, Indonesia\",\"time\":1573748260578,\"updated\":1601703531266,\"tz\":480,\"url\":\"https://earthquake.usgs.gov/earthquakes/eventpage/us60006bjl\",\"detail\":\"https://earthquake.usgs.gov/fdsnws/event/1/query?eventid=us60006bjl&format=geojson\",\"felt\":41,\"cdi\":5.8,\"mmi\":6.459,\"alert\":\"green\",\"status\":\"reviewed\",\"tsunami\":1,\"sig\":799,\"net\":\"us\",\"code\":\"60006bjl\",\"ids\":\",pt19318001,at00q0yvxj,us60006bjl,\",\"sources\":\",pt,at,us,\",\"types\":\",dyfi,finite-fault,general-text,geoserve,ground-failure,impact-link,impact-text,losspager,moment-tensor,origin,phase-data,shakemap,\",\"nst\":null,\"dmin\":1.271,\"rms\":1.15,\"gap\":11,\"magType\":\"mww\",\"type\":\"earthquake\",\"title\":\"M 7.1 - 138km E of Bitung, Indonesia\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[126.4156,1.6213,33]},\"id\":\"us60006bjl\"},\n" +
            "{\"type\":\"Feature\",\"properties\":{\"mag\":7.2,\"place\":\"93km NNE of Laiwui, Indonesia\",\"time\":1563095451523,\"updated\":1596115886346,\"tz\":540,\"url\":\"https://earthquake.usgs.gov/earthquakes/eventpage/us70004jyv\",\"detail\":\"https://earthquake.usgs.gov/fdsnws/event/1/query?eventid=us70004jyv&format=geojson\",\"felt\":17,\"cdi\":6.7,\"mmi\":6.81,\"alert\":\"green\",\"status\":\"reviewed\",\"tsunami\":1,\"sig\":809,\"net\":\"us\",\"code\":\"70004jyv\",\"ids\":\",us70004jyv,\",\"sources\":\",us,\",\"types\":\",dyfi,finite-fault,general-text,geoserve,ground-failure,impact-text,losspager,moment-tensor,origin,phase-data,poster,shakemap,\",\"nst\":null,\"dmin\":1.504,\"rms\":0.88,\"gap\":38,\"magType\":\"mww\",\"type\":\"earthquake\",\"title\":\"M 7.2 - 93km NNE of Laiwui, Indonesia\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[128.034,-0.5858,18.98]},\"id\":\"us70004jyv\"},\n" +
            "{\"type\":\"Feature\",\"properties\":{\"mag\":7.1,\"place\":\"2019 Ridgecrest Earthquake Sequence\",\"time\":1562383193040,\"updated\":1609870476649,\"tz\":-480,\"url\":\"https://earthquake.usgs.gov/earthquakes/eventpage/ci38457511\",\"detail\":\"https://earthquake.usgs.gov/fdsnws/event/1/query?eventid=ci38457511&format=geojson\",\"felt\":41335,\"cdi\":8.2,\"mmi\":8.73,\"alert\":\"yellow\",\"status\":\"reviewed\",\"tsunami\":1,\"sig\":1596,\"net\":\"ci\",\"code\":\"38457511\",\"ids\":\",ci38457511,us70004bn0,at00pu7alg,pt19187000,,\",\"sources\":\",ci,us,at,pt,,\",\"types\":\",dyfi,finite-fault,focal-mechanism,general-link,general-text,geoserve,ground-failure,impact-link,impact-text,losspager,moment-tensor,nearby-cities,oaf,origin,phase-data,poster,scitech-link,shakemap,\",\"nst\":77,\"dmin\":0.04616,\"rms\":0.22,\"gap\":43,\"magType\":\"mw\",\"type\":\"earthquake\",\"title\":\"M 7.1 - 2019 Ridgecrest Earthquake Sequence\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[-117.5993333,35.7695,8]},\"id\":\"ci38457511\"},\n" +
            "{\"type\":\"Feature\",\"properties\":{\"mag\":7.3,\"place\":\"292km NW of Saumlaki, Indonesia\",\"time\":1561344819830,\"updated\":1594402211502,\"tz\":540,\"url\":\"https://earthquake.usgs.gov/earthquakes/eventpage/us600044zz\",\"detail\":\"https://earthquake.usgs.gov/fdsnws/event/1/query?eventid=us600044zz&format=geojson\",\"felt\":323,\"cdi\":4.1,\"mmi\":5.64,\"alert\":\"green\",\"status\":\"reviewed\",\"tsunami\":1,\"sig\":952,\"net\":\"us\",\"code\":\"600044zz\",\"ids\":\",at00ptl1dg,pt19175001,us600044zz,\",\"sources\":\",at,pt,us,\",\"types\":\",dyfi,general-text,geoserve,ground-failure,impact-link,losspager,moment-tensor,origin,phase-data,poster,shakemap,\",\"nst\":null,\"dmin\":2.629,\"rms\":1.08,\"gap\":16,\"magType\":\"mww\",\"type\":\"earthquake\",\"title\":\"M 7.3 - 292km NW of Saumlaki, Indonesia\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[129.1692,-6.4078,212]},\"id\":\"us600044zz\"},\n" +
            "{\"type\":\"Feature\",\"properties\":{\"mag\":7.3,\"place\":\"116km NE of L'Esperance Rock, New Zealand\",\"time\":1560639304130,\"updated\":1567779911040,\"tz\":-720,\"url\":\"https://earthquake.usgs.gov/earthquakes/eventpage/us6000417i\",\"detail\":\"https://earthquake.usgs.gov/fdsnws/event/1/query?eventid=us6000417i&format=geojson\",\"felt\":12,\"cdi\":8.3,\"mmi\":6.465,\"alert\":\"green\",\"status\":\"reviewed\",\"tsunami\":1,\"sig\":830,\"net\":\"us\",\"code\":\"6000417i\",\"ids\":\",at00pt5wzj,us6000417i,pt19166001,\",\"sources\":\",at,us,pt,\",\"types\":\",dyfi,finite-fault,general-text,geoserve,impact-link,impact-text,losspager,moment-tensor,origin,phase-data,poster,shakemap,\",\"nst\":null,\"dmin\":1.402,\"rms\":0.81,\"gap\":11,\"magType\":\"mww\",\"type\":\"earthquake\",\"title\":\"M 7.3 - 116km NE of L'Esperance Rock, New Zealand\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[-178.0995,-30.6441,46]},\"id\":\"us6000417i\"},\n" +
            "{\"type\":\"Feature\",\"properties\":{\"mag\":8,\"place\":\"78km SE of Lagunas, Peru\",\"time\":1558856475073,\"updated\":1594213651238,\"tz\":-300,\"url\":\"https://earthquake.usgs.gov/earthquakes/eventpage/us60003sc0\",\"detail\":\"https://earthquake.usgs.gov/fdsnws/event/1/query?eventid=us60003sc0&format=geojson\",\"felt\":1782,\"cdi\":8.9,\"mmi\":7.945,\"alert\":\"orange\",\"status\":\"reviewed\",\"tsunami\":1,\"sig\":1890,\"net\":\"us\",\"code\":\"60003sc0\",\"ids\":\",at00ps3pco,pt19146001,us60003sc0,\",\"sources\":\",at,pt,us,\",\"types\":\",dyfi,finite-fault,general-text,geoserve,ground-failure,impact-link,impact-text,losspager,moment-tensor,origin,phase-data,poster,shakemap,\",\"nst\":null,\"dmin\":3.18,\"rms\":0.84,\"gap\":17,\"magType\":\"mww\",\"type\":\"earthquake\",\"title\":\"M 8.0 - 78km SE of Lagunas, Peru\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[-75.2697,-5.8119,122.57]},\"id\":\"us60003sc0\"},\n" +
            "{\"type\":\"Feature\",\"properties\":{\"mag\":7.6,\"place\":\"46km SSE of Namatanai, Papua New Guinea\",\"time\":1557838705939,\"updated\":1594162417609,\"tz\":600,\"url\":\"https://earthquake.usgs.gov/earthquakes/eventpage/us70003kyy\",\"detail\":\"https://earthquake.usgs.gov/fdsnws/event/1/query?eventid=us70003kyy&format=geojson\",\"felt\":58,\"cdi\":6.9,\"mmi\":7.252,\"alert\":\"green\",\"status\":\"reviewed\",\"tsunami\":1,\"sig\":929,\"net\":\"us\",\"code\":\"70003kyy\",\"ids\":\",pt19134000,us70003kyy,\",\"sources\":\",pt,us,\",\"types\":\",dyfi,finite-fault,general-link,general-text,geoserve,ground-failure,impact-link,impact-text,losspager,moment-tensor,origin,phase-data,poster,shakemap,\",\"nst\":null,\"dmin\":0.453,\"rms\":1,\"gap\":16,\"magType\":\"mww\",\"type\":\"earthquake\",\"title\":\"M 7.6 - 46km SSE of Namatanai, Papua New Guinea\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[152.5967,-4.051,10]},\"id\":\"us70003kyy\"},\n" +
            "{\"type\":\"Feature\",\"properties\":{\"mag\":7.1,\"place\":\"33km NW of Bulolo, Papua New Guinea\",\"time\":1557177577983,\"updated\":1594402818038,\"tz\":600,\"url\":\"https://earthquake.usgs.gov/earthquakes/eventpage/us70003hqb\",\"detail\":\"https://earthquake.usgs.gov/fdsnws/event/1/query?eventid=us70003hqb&format=geojson\",\"felt\":77,\"cdi\":6.5,\"mmi\":6.159,\"alert\":\"yellow\",\"status\":\"reviewed\",\"tsunami\":1,\"sig\":826,\"net\":\"us\",\"code\":\"70003hqb\",\"ids\":\",at00pr3pwp,us70003hqb,pt19126050,\",\"sources\":\",at,us,pt,\",\"types\":\",dyfi,finite-fault,general-text,geoserve,ground-failure,impact-link,impact-text,losspager,moment-tensor,origin,phase-data,poster,shakemap,\",\"nst\":null,\"dmin\":2.514,\"rms\":1.02,\"gap\":16,\"magType\":\"mww\",\"type\":\"earthquake\",\"title\":\"M 7.1 - 33km NW of Bulolo, Papua New Guinea\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[146.4494,-6.9746,146]},\"id\":\"us70003hqb\"},\n" +
            "{\"type\":\"Feature\",\"properties\":{\"mag\":7,\"place\":\"23km NNE of Azangaro, Peru\",\"time\":1551430242591,\"updated\":1594396965751,\"tz\":-300,\"url\":\"https://earthquake.usgs.gov/earthquakes/eventpage/us1000j96d\",\"detail\":\"https://earthquake.usgs.gov/fdsnws/event/1/query?eventid=us1000j96d&format=geojson\",\"felt\":252,\"cdi\":7.2,\"mmi\":4.423,\"alert\":\"green\",\"status\":\"reviewed\",\"tsunami\":1,\"sig\":935,\"net\":\"us\",\"code\":\"1000j96d\",\"ids\":\",at00pnoj8k,pt19060000,us1000j96d,\",\"sources\":\",at,pt,us,\",\"types\":\",dyfi,finite-fault,general-text,geoserve,ground-failure,impact-link,impact-text,losspager,moment-tensor,origin,phase-data,poster,shakemap,\",\"nst\":null,\"dmin\":2.501,\"rms\":1.13,\"gap\":42,\"magType\":\"mww\",\"type\":\"earthquake\",\"title\":\"M 7.0 - 23km NNE of Azangaro, Peru\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[-70.1546,-14.7131,267]},\"id\":\"us1000j96d\"},\n" +
            "{\"type\":\"Feature\",\"properties\":{\"mag\":7.5,\"place\":\"111km ESE of Palora, Ecuador\",\"time\":1550830643770,\"updated\":1594162356277,\"tz\":-300,\"url\":\"https://earthquake.usgs.gov/earthquakes/eventpage/us2000jlfv\",\"detail\":\"https://earthquake.usgs.gov/fdsnws/event/1/query?eventid=us2000jlfv&format=geojson\",\"felt\":418,\"cdi\":6.7,\"mmi\":7.281,\"alert\":\"yellow\",\"status\":\"reviewed\",\"tsunami\":1,\"sig\":1145,\"net\":\"us\",\"code\":\"2000jlfv\",\"ids\":\",at00pnbol0,pt19053000,us2000jlfv,\",\"sources\":\",at,pt,us,\",\"types\":\",dyfi,finite-fault,general-text,geoserve,ground-failure,impact-link,impact-text,losspager,moment-tensor,origin,phase-data,poster,shakemap,\",\"nst\":null,\"dmin\":2.045,\"rms\":1.12,\"gap\":15,\"magType\":\"mww\",\"type\":\"earthquake\",\"title\":\"M 7.5 - 111km ESE of Palora, Ecuador\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[-77.0505,-2.1862,145]},\"id\":\"us2000jlfv\"}],\"bbox\":[-178.0995,-30.6441,8,152.5967,35.7695,267]}";

*/
    public static URL buildUrl() {
        Uri builtUri = Uri.parse(EARTHQUAKE_SEARCH_QUERY).buildUpon().build();
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Log.d("URL: ", url.toString());
        return url;
    }

    public static String getResponseFromHttpRequest(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();
            return parseInputStream(in);
        } finally {
            urlConnection.disconnect();
        }
    }

    private static String parseInputStream(InputStream is) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            return sb.toString();
        }
    }
}
