package ne0nx3r0.rareitemsreg;

import java.io.File;
import java.security.KeyPair;
import java.util.logging.Level;
import java.util.logging.Logger;
import ne0nx3r0.crypto.RSAIO;
import ne0nx3r0.crypto.RSAKeygen;
import ne0nx3r0.http.AdminApiMessenger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class RareItemsRegistration extends JavaPlugin
{    
    public RareItemsRegistration self;
    public static final Logger logger = Logger.getLogger("RareItemsRegistration");
    public static KeyPair keyPair;
    File RSA_DIRECTORY;
    public static AdminApiMessenger m;
  
    @Override
    public void onEnable()
    {
        self = this;

//Encryption
        RSA_DIRECTORY = new File(this.getDataFolder() + "/rsa");
        
        try
        {
            if(!RSA_DIRECTORY.exists())
            {
                RSA_DIRECTORY.mkdirs();

                logger.log(Level.INFO,"Generating keypair");
                logger.log(Level.INFO,"Ne0n probably did something that has caused a need to now generate a new key. This message is purposely long because I wanted to be sure to know when a new key is generated. KEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEY");

                RareItemsRegistration.keyPair = RSAKeygen.generate(2048);

                RSAIO.save(RSA_DIRECTORY, RareItemsRegistration.keyPair);
            }
            else
            {
                RareItemsRegistration.keyPair = RSAIO.load(RSA_DIRECTORY);
            }
        }
        catch (Exception ex)
        {
            Bukkit.getLogger().log(Level.SEVERE, "Error reading keys", ex);
        }

//HTTP messaging
        m = new AdminApiMessenger();
            
        //register events
        getServer().getPluginManager().registerEvents(new RareItemsRegistrationPlayerListener(), this);
    }
}