package tmcore.users;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class AuthorizationManager {

  private java.util.ArrayList<UserClass> userClasses;
  private static AuthorizationManager instance = null;

  private AuthorizationManager() {
    BufferedReader br;

    // TODO: Finish the loading of "auth.tmc"
    try {
      br = new BufferedReader(new FileReader(new File(UserManager.DEFAULT_CONFIG_NAME)));
      
    } catch(FileNotFoundException fnfx) {
      Logger.getLogger(AuthorizationManager.class.getName()).log(Level.SEVERE,
              "Unable to locate configuration file, exiting.", fnfx);
      JOptionPane.showMessageDialog(null, "Unable to locate configuration "
              + "file.\nPlease re-install or repair an existing installation.",
              "Unrecoverable Error", JOptionPane.OK_OPTION);
      System.exit(-1);
    }
  }

  public boolean userClassExists(String userClass) {
    for(UserClass uc : userClasses) {
      if(uc.getLabel().equals(userClass)) {
        return true;
      }
    }

    return false;
  }

  public boolean userClassCanPerform(String userClass, String userAction) {
    UserClass subject = null;
    
    for(UserClass uc : userClasses) {
      if(uc.getLabel().equals(userClass)) {
        subject = uc;
        break;
      }
    }
    
    return subject == null ? false : subject.hasAction(userAction);
  }

  public String[] getDefaultClasses() {
    String[] defaultClasses = {
      "SuperUserClass",
      "AdminUserClass",
      "SupportUserClass",
      "GeneralUserClass"
    };

    return defaultClasses;
  }

  public static AuthorizationManager instance() {
    if(instance == null) {
      instance = new AuthorizationManager();
    }

    return instance;
  }
}
