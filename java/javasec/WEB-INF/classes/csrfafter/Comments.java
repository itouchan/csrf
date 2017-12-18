package csrfafter;

import java.sql.Timestamp;

public class Comments {
    int id = 0;
    String msg = "";
    String speaker = "";
    Timestamp created  = null;
    Timestamp modified = null;

    public void setId(int _id) {
        id = _id;
    }
    public int getId() {
        return id;
    }

    public void setMsg(String _msg) {
        msg = _msg;
    }
    public String getMsg() {
        return msg;
    }

    public void setSpeaker(String _speaker) {
        speaker = _speaker;
    }
    public String getSpeaker() {
        return speaker;
    }

}
