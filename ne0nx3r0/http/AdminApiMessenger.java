package ne0nx3r0.http;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import ne0nx3r0.crypto.RSA;
import ne0nx3r0.rareitemsreg.RareItemsRegistration;
import org.json.simple.JSONObject;

public class AdminApiMessenger
{
    public AdminApiMessenger()
    {
        
    }
    
    public String sendEncryptedMessage(String sUrl,String plainMessage) throws Exception
    {
        byte[] bEncryptedMessage = RSA.encrypt(plainMessage.getBytes(),RareItemsRegistration.keyPair.getPublic());
        String sEncryptedMessage = Base64.encode(bEncryptedMessage);

        //known good decryption:
        //System.out.println(new String(RSA.decrypt(Base64.decode(sEncryptedMessage), RareItemsRegistration.keyPair.getPrivate())));
        //System.out.println(URLEncoder.encode(sEncryptedMessage, "UTF-8"));
        if(sEncryptedMessage != null)
        {
            try{
                URLConnection connection = new URL(sUrl).openConnection();
                connection.setDoOutput(true);
                connection.setDoInput(true);
                
                try(OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream()))
                {
                    writer.write("message="+URLEncoder.encode(sEncryptedMessage, "UTF-8"));
                    writer.flush();
                }

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                String line;
                String returnString = "";

                while ((line = reader.readLine()) != null)
                {
                    returnString += line;
                }
                
                System.out.println(returnString);
                
                return returnString;
            }
            catch (Exception ex)
            {
                RareItemsRegistration.logger.log(Level.SEVERE, null, ex);
            }
        }
        
        return null;
    }

    public boolean registerUser(String username, String password,String ip)
    {
        JSONObject json = new JSONObject();
        
        json.put("username", username);
        json.put("password", password);
        json.put("ip", ip);
        
        try
        {
            //System.out.println(this.sendEncryptedMessage("http://www.rareitemsplugin.tk/adminApi/registerUser/",json.toJSONString()));
            if(this.sendEncryptedMessage("http://www.rareitemsplugin.tk/adminApi/registerUser/",json.toJSONString()).equals("success"))
            {
                return true;
            }
        }
        catch(Exception ex)
        {
            Logger.getLogger(AdminApiMessenger.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public void fetchPlayerRareItems(String sPlayerName)
    {
        (new Thread(){
            public void run(){
              System.out.println("Thread Running");  
            }
        }).start();
    }
}
