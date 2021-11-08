package colis;

import java.util.*;
import java.lang.*;

import java.util.Collection;
import java.text.SimpleDateFormat;

/**
 * Classe qui simule le depot d'un colis dans un relais.
 *
 * @author  (DEMAISON)
 * @version (18-01-2020)
 */
public class RelaisColis implements IRelaisColis{

    protected Map<String,List<String>> recu; //colis reçus
    protected Map<String,List<String>> recup; //colis recuperes
    protected String name = null;
    private Map<String,List<IDepotListener>> obs = null;//liste d'observateurs

    public RelaisColis(String name){
        this.recu = new HashMap<>();
        this.recup = new HashMap<>();
        this.name=name;
    } 

    public RelaisColis(){}

    public void setRelaisColis(Map<String,List<String>> map){
        this.recu = map;
        this.recup = map;
    }

    public void setNotif(){//init option notif
        this.obs = new HashMap<>();
    }

    /**
     * ajout d'un destinataire dans les relais
     */
    public void add(String dest) throws Exception{
        if(!recu.containsKey(dest)){
            List<String> listeRecu =  new ArrayList();
            recu.put(dest,listeRecu);
            List<String> listeRecup =  new ArrayList();
            recup.put(dest,listeRecup);
        }else {
            throw new Exception();
        }
    }

    public void send(String message, String dest) throws Exception{
        if(recu.containsKey(dest)){//si le destinataire est bien inscrit dans le relais
            List<String> liste = recu.get(dest);//recup liste des colis du destinataire
            Calendar calendar = new GregorianCalendar(Locale.FRANCE);
            Date date = calendar.getTime();
            String msg = "Colis deposé le : "+ new SimpleDateFormat().format(date)+", avec ce message : "+message;
            // String msg = message;
            liste.add(msg); //ajout du colis en fin de liste
            //recu.put(dest,liste);
            //option de notif :
            if(obs!=null && obs.containsKey(dest)){
                for(IDepotListener listener : obs.get(dest)){
                    listener.onReceive(msg);
                }
            }
        }else {
            throw new Exception();
        }
    }

    public void send(String dest) throws Exception{
        if(recu.containsKey(dest)){//si le destinataire est bien inscrit dans le relais
            List<String> liste = recu.get(dest);//recup liste des colis du destinataire
            Calendar calendar = new GregorianCalendar(Locale.FRANCE);
            Date date = calendar.getTime();
            String message = "Colis deposé le : "+ new SimpleDateFormat().format(date);
            // String message = "Colis deposé le : ";
            liste.add(message); //ajout du colis en fin de liste
            //option de notif :
            if(obs!=null && obs.containsKey(dest)){
                for(IDepotListener listener : obs.get(dest)){
                    listener.onReceive(message);
                }
            }
        }else {
            throw new Exception("le destinataire n'est pas inscrit dans le relais");
        }
    }

    public List<String>  withdrawal(String dest) throws Exception{
        if(recu.containsKey(dest)){
            List<String> liste = recu.get(dest);//recup liste colis reçus
            //List<String> list = new ArrayList<String>();
            List<String> list = new ArrayList<String>(liste);//copie de la liste
            recup.get(dest).addAll(list);//ajout aux colis reçus
            liste.clear();//vider la liste des colis reçus
            return list;
        }else {
            throw new Exception();
        }
    }

    public void addDepotListener(String destinataire, IDepotListener listener) throws Exception{
        if(this.obs==null) throw new Exception("Le relais "+name+ " n'est pas inscrit à l'option de notification");
        if(!recu.containsKey(destinataire)) throw new Exception(destinataire + " est inconnu ");
        List<IDepotListener> liste;
        if(obs.get(destinataire)!=null){
            liste = obs.get(destinataire);   
        }else{
            liste = new ArrayList<>();   
        }
        liste.add(listener);
        obs.put(destinataire,liste);
    }

    public String getName(){
        if(this.name==null) return "NomDefault";
        return name;
    }

}
