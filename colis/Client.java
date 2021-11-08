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

            System.out.println("incription au relais colis de Anne Benois Colin Daphn�e");
            rc.add("Anne");rc.add("Benois");rc.add("Colin");rc.add("Daphn�e");

            System.out.println("Anne re�oit un colis pour son anniversaire");
            String colis = new String("bon anniversaire"); 
            rc.send(colis,"Anne");
            List<String> list = rc.withdrawal("Anne");
            System.out.println("Voici son colis : "+list.toString());
            list = rc.withdrawal("Anne");
            System.out.println("Quand Anne r�cup�re son colis, il n'y a plus de colis au relais: "+list.toString());

            System.out.println("Benois re�oit un colis");
            rc.send("Benois");
            list = rc.withdrawal("Benois");
            System.out.println("Voici son colis : "+list.toString());

            list = rc.withdrawal("Benois");

            System.out.println("Benois re�oit 3 colis");
            rc.send("Benois");
            rc.send("Benois");
            rc.send("Benois");
            list = rc.withdrawal("Benois");
            System.out.println("Voici ses colis : "+list.toString());
            System.out.println("Quand Anne r�cup�re son colis, il n'y a plus de colis au relais: "+list.toString());
            
            System.out.println("Eric re�oit un colis");
            rc.send("Eric");
            System.out.println("Ce message ne doit pas s'�crire car une exception est lanc�e, Eric n'est pas inscrit dans le relais");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
