/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.connect;

import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.List;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.Map;



/**
 *
 * @author CAROL
 */
public class Update1 extends Form {
   // TextField txt = new TextField();
    // private Resources theme;

    public Update1(Resources res){ 
   // Form i = new Form ("USERS");
    TextField F_NAME = new TextField("","firstname", 20, TextField.ANY);
    add("Firstname").add(F_NAME);
    
    TextField L_NAME = new TextField("","latstname",20, TextField.NUMERIC);
    add("Lastname").add(L_NAME);
        
    TextField USR_CODE = new TextField("","user code",20, TextField.DECIMAL);
    add("usercode").add(USR_CODE);
    
    TextField USR_DOB = new TextField("","Date of Birth",20, TextField.DECIMAL); 
    add("date Of Birth").add(USR_DOB);
    
    TextField MARITAL_STATUS = new TextField("","Marital Status",20, TextField.ANY);
    add("Marital Status").add(MARITAL_STATUS);

    TextField POSITION = new TextField("","Postion",20, TextField.ANY); 
    add("Position").add(POSITION);
    
    Label lni = new Label(" ");
        add(lni);
    Button addtext = new Button("add");
   add(addtext);
    
    addtext.addActionListener(e ->{
        String name = F_NAME.getText();
        add(name);
        String amount = L_NAME.getText();
        add(amount);
        String monthly = USR_CODE.getText();
        add(monthly);
        String pay = USR_DOB.getText();
        add(pay);
        String date = MARITAL_STATUS.getText();
        add(date);
        String end = POSITION.getText();
        add(end);
        Connect(name,amount,monthly,pay,date,end,lni);
        System.out.println("Loan Information: "+name+", "+amount+", "+monthly+", "+pay+", "+date+", "+end);
            });
    }

    private void Connect(String user_id, String comp_id, String monthly, String pay, String date, String end, Label total) {
        try {

            ConnectionRequest req9 = new ConnectionRequest();
            req9.setUrl("https://static-pos.com/microloans/Dashboard/count_overdue.php");
            req9.setPost(false);
            req9.addArgument("user_id", user_id);
            req9.addArgument("comp_id", comp_id);
            //  req3.addArgument("firstname", firstname);
            //  req3.addArgument("phone", phone);
            //  req3.addArgument("password", password);
            NetworkManager.getInstance().addToQueueAndWait(req9);
            byte[] data = req9.getResponseData();
            if (data == null) {
                throw new IOException("Network Err");
            }
            JSONParser parser = new JSONParser();
            System.out.println("THIS IS THE DATA: "+data);
            Map response = parser.parseJSON(new InputStreamReader(new ByteArrayInputStream(data), "UTF-8"));
            java.util.List<Map<String, Object>> content = (java.util.List<Map<String, Object>>) response.get("root");
            for (Map<String, Object> obj : content) {
                String url = (String) obj.get("result");
                System.out.println("NAYI URL: "+url);
                switch (url) {
                    case "success":
                        System.out.println(url);
                    //    String total= (String) obj.get("total");

                        
                    case "failed":
                      //  return "0";
                    //ToastBar.showInfoMessage("Error! Authentication failed check credentials.");
                    //break;
                }
            }
            System.out.println("res" + response);
            List items = (List) response.get("root");
            String item = "" + response.get("root").equals("result");
            System.out.print(item);
            System.out.print("Items: " + items);
        } catch (Exception e) {
        }
 
    }
}
