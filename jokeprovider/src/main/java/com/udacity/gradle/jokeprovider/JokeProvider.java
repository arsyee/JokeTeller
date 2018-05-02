package com.udacity.gradle.jokeprovider;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Calendar;
import java.util.Locale;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class JokeProvider {
    public static String provideJoke() {
        try {
            JsonObject jsonObject = fetchJson("http://api.icndb.com/jokes/random/?escape=javascript");
            return getJoke(jsonObject);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JokeException e) {
            return e.getMessage();
        }
        return String.format(Locale.getDefault(), "The best joke so far: Internet connection works reliably on server side in %d", Calendar.getInstance().get(Calendar.YEAR));
    }

    private static String getJoke(JsonObject jsonObject) throws JokeException {
        try {
            String type = jsonObject.getString("type", null);
            if (!"success".equals(type))
                throw new JokeException(String.format(Locale.getDefault(), "Chuck Norris can read a joke from the API even if it's type is '%s'.", type));
            JsonObject value = jsonObject.getJsonObject("value");
            return value.getString("joke");
        } catch (Exception e) {
            throw new JokeException(e.toString());
        }
    }

    private static JsonObject fetchJson(String url) throws IOException {
        InputStream input = new URL(url).openStream();
        JsonReader reader = Json.createReader(input);
        return reader.readObject();
    }

    private static class JokeException extends Throwable {
        JokeException(String s) {
            super(s);
        }
    }
}
