import java.util.HashMap;

public class AdminLogData {
    HashMap<String,String>  logininfo;

    AdminLogData(){
        logininfo = new HashMap<>();
        logininfo.put("admin1","pass123");
        logininfo.put("admin2","password");
        logininfo.put("","");

    }

    protected HashMap getLoginInfo(){
        return logininfo;
    }
}
