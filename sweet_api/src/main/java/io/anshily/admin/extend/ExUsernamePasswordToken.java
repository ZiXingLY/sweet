package io.anshily.admin.extend;

import org.apache.shiro.authc.HostAuthenticationToken;
import org.apache.shiro.authc.RememberMeAuthenticationToken;

import java.util.Arrays;

public class ExUsernamePasswordToken implements HostAuthenticationToken, RememberMeAuthenticationToken {

    private String username;
    private char[] password;
    private boolean rememberMe;
    private String host;


    private int type = 1;
    private String openid;


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }




    public ExUsernamePasswordToken() {
        this.rememberMe = false;
    }

    public ExUsernamePasswordToken(String username, char[] password) {
        this(username, (char[])password, false, (String)null);
    }

    public ExUsernamePasswordToken(String username, String password) {
        this(username, (char[])(password != null ? password.toCharArray() : null), false, (String)null);
    }

    public ExUsernamePasswordToken(String username, char[] password, String host) {
        this(username, password, false, host);
    }

    public ExUsernamePasswordToken(String username, String password, String host) {
        this(username, password != null ? password.toCharArray() : null, false, host);
    }

    public ExUsernamePasswordToken(String username, char[] password, boolean rememberMe) {
        this(username, (char[])password, rememberMe, (String)null);
    }

    public ExUsernamePasswordToken(String username, String password, boolean rememberMe) {
        this(username, (char[])(password != null ? password.toCharArray() : null), rememberMe, (String)null);
    }

    public ExUsernamePasswordToken(String username, char[] password, boolean rememberMe, String host) {
        this.rememberMe = false;
        this.username = username;
        this.password = password;
        this.rememberMe = rememberMe;
        this.host = host;
    }

    public ExUsernamePasswordToken(String username, String password, boolean rememberMe, String host) {
        this(username, password != null ? password.toCharArray() : null, rememberMe, host);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public char[] getPassword() {
        return this.password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public Object getPrincipal() {
        return this.getUsername();
    }

    public Object getCredentials() {
        return this.getPassword();
    }

    public String getHost() {
        return this.host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public boolean isRememberMe() {
        return this.rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public void clear() {
        this.username = null;
        this.host = null;
        this.rememberMe = false;
        if (this.password != null) {
            for(int i = 0; i < this.password.length; ++i) {
                this.password[i] = 0;
            }

            this.password = null;
        }

    }

    @Override
    public String toString() {
        return "ExUsernamePasswordToken{" +
                "username='" + username + '\'' +
                ", password=" + Arrays.toString(password) +
                ", rememberMe=" + rememberMe +
                ", host='" + host + '\'' +
                ", type=" + type +
                ", openid='" + openid + '\'' +
                '}';
    }
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append(this.getClass().getName());
//        sb.append(" - ");
//        sb.append(this.username);
//        sb.append(", rememberMe=").append(this.rememberMe);
//        if (this.host != null) {
//            sb.append(" (").append(this.host).append(")");
//        }
//
//        return sb.toString();
//    }
}
