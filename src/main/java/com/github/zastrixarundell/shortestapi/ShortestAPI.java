/*
 *             DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE
 *                     Version 2, December 2004
 *
 * Copyright (C) 2019, Zastrix Arundell, https://github.com/ZastrixArundell
 *
 *  Everyone is permitted to copy and distribute verbatim or modified
 *  copies of this license document, and changing it is allowed as long
 *  as the name is changed.
 *
 *             DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE
 *    TERMS AND CONDITIONS FOR COPYING, DISTRIBUTION AND MODIFICATION
 *
 *   0. You just DO WHAT THE FUCK YOU WANT TO.
 *
 *
 */

package com.github.zastrixarundell.shortestapi;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;

public class ShortestAPI
{

    private String link;
    private String token;
    private String responseAsJson, shortenedURL, statusMessage;

    public ShortestAPI(String token)
    {
        this.token = token;
    }

    public ShortestAPI(String token, String link)
    {
        this.token = token;
        this.link = link;
    }

    public void sendRequest() throws IOException
    {
        link = "urlToShorten=" + link;

        URL url = new URL("https://api.shorte.st/v1/data/url");
        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
        con.setRequestMethod("PUT");
        con.setRequestProperty("Host", "api.shorte.st");
        con.setRequestProperty("User-Agent", "curl/7.52.1");
        con.setRequestProperty("Accept", "*/*");
        con.setRequestProperty("public-api-token", token);
        con.setRequestProperty("Content-Length", String.valueOf(link.length()));
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        con.setDoOutput(true);
        con.setDoInput(true);

        OutputStream stream = con.getOutputStream();
        stream.write(link.getBytes());

        InputStream input = con.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));

        stream.flush();
        stream.close();

        responseAsJson = reader.readLine();

        JsonObject jsonObject = new Gson().fromJson(responseAsJson, JsonObject.class);

        statusMessage = jsonObject.get("status").getAsString();
        shortenedURL = jsonObject.get("shortenedUrl").getAsString();

        input.close();

        con.disconnect();
    }

    public String getReponseJson()
    {
        return responseAsJson;
    }

    public void setLink(String link)
    {
        this.link = link;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    public String getLink()
    {
        return link;
    }

    public String getToken()
    {
        return token;
    }

    public String getResponseAsJson()
    {
        return responseAsJson;
    }

    public String getShortenedURL()
    {
        return shortenedURL;
    }

    public String getStatusMessage()
    {
        return statusMessage;
    }
}
