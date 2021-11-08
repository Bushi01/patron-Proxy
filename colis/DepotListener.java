package colis;

import java.util.*;
import java.lang.*;

import java.util.Collection;
/**
 * exemple utilisation patron proxy
 *
 * @author  (DEMAISON)
 * @version (18-01-2020)
 */
public class DepotListener implements IDepotListener{
    private List<String> notifications = new ArrayList<>();

    public void onReceive(String msg) throws Exception{
      notifications.add(msg);
    }
    public List<String> getNotifications(){
      return notifications;
    }
}
