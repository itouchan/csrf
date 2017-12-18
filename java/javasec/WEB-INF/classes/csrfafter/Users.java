package csrfafter;

import java.sql.Timestamp;

public class Users {
    int id = 0;
    String userid = "";
    String password = "";
    Timestamp created  = null;
    Timestamp modified = null;

    public void setId(int _id) {
        id = _id;
    }
    public int getId() {
        return id;
    }

    public void setUserid(String _userid) {
        userid = _userid;
    }
    public String getUserid() {
        return userid;
    }

    public void setPassword(String _password) {
        password = _password;
    }
    public String getPassword() {
        return password;
    }

}
