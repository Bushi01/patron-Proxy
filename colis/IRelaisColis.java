package colis;

import java.util.List;
/**
 * interface IRelaisColis.
 *
 * @author  (DEMAISON)
 * @version (18-01-2020)
 */

public interface IRelaisColis{
    /**
     * inscription d un destinataire
     */
   public void add(String dest) throws Exception;
   
   /**
    * depot d un colis
    */
   public void send(String dest) throws Exception;
   
   /**
    * depot d un colis avec message
    */
   public void send(String message, String dest) throws Exception;
   
   /**
    * retrait colis
    */
   public List<String> withdrawal(String dest) throws Exception;
   
   /**
    * pour les notifications
    */
   public void setNotif();
   public void addDepotListener(String destinataire, IDepotListener listener) throws Exception;
   
    /**
    * nom du relais
    */
   public String getName() throws Exception;
}
