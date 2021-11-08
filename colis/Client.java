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
public class Client{
    public static void main (String[] args){
        try{ 
            IRelaisColis rc = new RelaisColis("ABonPort");
            System.out.println("creation d'un relais colis de nom : "+rc.getName());

            System.out.println("incription au relais colis de Anne Benois Colin Daphnée");
            rc.add("Anne");rc.add("Benois");rc.add("Colin");rc.add("Daphnée");

            System.out.println("Anne reçoit un colis pour son anniversaire");
            String colis = new String("bon anniversaire"); 
            rc.send(colis,"Anne");
            List<String> list = rc.withdrawal("Anne");
            System.out.println("Voici son colis : "+list.toString());
            list = rc.withdrawal("Anne");
            System.out.println("Quand Anne récupère son colis, il n'y a plus de colis au relais: "+list.toString());

            System.out.println("Benois reçoit un colis");
            rc.send("Benois");
            list = rc.withdrawal("Benois");
            System.out.println("Voici son colis : "+list.toString());

            list = rc.withdrawal("Benois");

            System.out.println("Benois reçoit 3 colis");
            rc.send("Benois");
            rc.send("Benois");
            rc.send("Benois");
            list = rc.withdrawal("Benois");
            System.out.println("Voici ses colis : "+list.toString());
            System.out.println("Quand Anne récupère son colis, il n'y a plus de colis au relais: "+list.toString());
            
            System.out.println("Eric reçoit un colis");
            rc.send("Eric");
            System.out.println("Ce message ne doit pas s'écrire car une exception est lancée, Eric n'est pas inscrit dans le relais");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
