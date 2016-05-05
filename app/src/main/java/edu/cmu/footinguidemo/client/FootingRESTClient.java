package edu.cmu.footinguidemo.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import edu.cmu.footinguidemo.model.User;

/**
 * Socket client for connecting the server
 * Created by Polyhedron on 2016/5/4.
 */
public class FootingRESTClient {

    private URL url; //"http://10.0.2.2:8080/base/user/add";  // To be used on AVD <-> local server connection
    private HttpURLConnection connection;

    public FootingRESTClient(String url) {
        try {
            this.url = new URL(url);
            connection = (HttpURLConnection) this.url   .openConnection();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void sendUserData(User user) {
        try {
            ObjectOutputStream oout = new ObjectOutputStream(connection.getOutputStream());
            String outputStr = user.getEmail() + "&" + user.getUsername() + "&" + user.getPassword() + "&" + user.getCountryList() + "&" + user.getNumMiles() + "&" + user.getJournalList() + "&" + user.getMedalList();
            oout.writeObject(outputStr);
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