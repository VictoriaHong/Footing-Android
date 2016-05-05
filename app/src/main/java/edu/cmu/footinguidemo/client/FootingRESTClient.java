package edu.cmu.footinguidemo.client;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import edu.cmu.footinguidemo.model.User;

/**
 * Socket client for connecting the server
 * Created by Polyhedron on 2016/5/4.
 */
public class FootingRESTClient {

    private URL url; //"http://192.168.1.13:8080/base/user/add";  // To be used on AVD <-> local server connection
    private HttpURLConnection connection;

    public FootingRESTClient(String url) {
        try {
            this.url = new URL(url);
            connection = (HttpURLConnection) this.url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void sendUserData(User user) {


        try {
            ObjectOutputStream oout = new ObjectOutputStream(connection.getOutputStream());
            String outputStr =  URLEncoder.encode(user.getEmail(), "UTF-8") + "&" + user.getUsername() + "&" + user.getPassword() + "&" + "country list:"+user.getCountryList() + "&" + "total miles:"+user.getNumMiles() + "&" + "journal list:"+user.getJournalList() + "&" + "medal list:"+user.getMedalList();
            oout.writeObject(outputStr);
            oout.flush();
            oout.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public String getResponse() {
        try {
            ObjectInputStream oin = new ObjectInputStream(connection.getInputStream());
            return (String) oin.readObject();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return "Server error";
        }
    }

    public void disconnect() {
        connection.disconnect();
    }
}