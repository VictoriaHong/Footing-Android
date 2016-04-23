package edu.cmu.footinguidemo.controller;

/**
 * String validator class
 * @author Qiaoyi Chen (qiaoyic)
 */
public class Validator {

    public static boolean isEmailValid(String email) {
        String[] tokens = email.split("@");
        if (tokens.length == 2 && !tokens[0].isEmpty() && !tokens[1].isEmpty()) {
            return !tokens[0].matches(".*[ @].*") && !tokens[1].matches(".*[ @].*") && tokens[1].matches(".+[\\.].+");
        } else {
            return false;
        }
    }

    public static boolean isPasswordValid(String password) {
        return password.length() >= 4;
    }

    public static boolean isPasswordConfirmValid(String password1, String password2) {
        return password1.equals(password2);
    }

}
