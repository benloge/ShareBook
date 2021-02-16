package fr.sharebookstore.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.widget.TextView;

public class MainActivity extends Activity implements Runnable{

    private String hiduke="";
    private int price=0;
    private String errmsg="";
    private int ID_Genre=0;
    private String Name_Genre ="";

    public void run() {
        System.out.println("Select Records Example by using the Prepared Statement!");
        Connection con = null;
        int count = 0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection
                    ("jdbc:mysql://ls-0f927a463e6d389cf0f567dc4d5a58f8ca59fcd7.cq7na6hxonpd.eu-central-1.rds.amazonaws.com:3306/ShareBook","sharebookuser","uA?BL6P8;t=P-JKl)]Su>L3Gj$[mz0q]");
            try{
                String sql;
                //	  sql
                //	  = "SELECT `genre`.`ID_Genre`,
                //    `genre`.`Nom`
                //FROM `ShareBook`.`genre`;";
                sql
                        = "SELECT `genre`.`ID_Genre`, `genre`.`Nom` FROM `ShareBook`.`genre`;";
                PreparedStatement prest = con.prepareStatement(sql);
                //prest.setInt(1,1980);
                //prest.setInt(2,2004);
                ResultSet rs = prest.executeQuery();
                while (rs.next()){
                    ID_Genre = rs.getInt(1);
                    Name_Genre = rs.getString(2);
                    count++;
                    System.out.println(ID_Genre + "\t" + "- " + Name_Genre);
                }
                System.out.println("Number of records: " + count);
                prest.close();
                con.close();
            }
            catch (SQLException s){
                System.out.println("SQL statement is not executed!");
                errmsg=errmsg+s.getMessage();

            }
        }
        catch (Exception e){
            e.printStackTrace();
            errmsg=errmsg+e.getMessage();
        }

        handler.sendEmptyMessage(0);

    }
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {

            TextView textView = (TextView) findViewById(R.id.textView0);
            textView.setText("ID="+ID_Genre+" Name="+Name_Genre+" "+errmsg);
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Thread thread = new Thread(this);
        thread.start();




    }


}