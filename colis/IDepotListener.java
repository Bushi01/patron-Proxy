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

public interface IDepotListener{
    public void onReceive(String msg) throws Exception;
    public List<String> getNotifications();
}
