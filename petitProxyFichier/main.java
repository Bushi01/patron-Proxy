package petitProxyFichier;

import java.io.*;
import java.nio.*;
/**
 * Affiche la liste des fichiers présents dans un répertoire spécifié.
 *
 * @author (DEMAISON)
 * @version (05-01-2021)
 */
public class main{
    public static void main(String[] args){
        String nomDossier = "dossier";
        
        System.out.println("sans passer par le proxy");
        RealSubject rsub = new RealSubject();
        File[] fileTab = rsub.loadFiles(nomDossier);
        System.out.println("Taille de la liste :"+fileTab.length);
        String names = rsub.toString(fileTab);
        System.out.println("Liste des fichiers présents dans le fichier "+nomDossier+" :\n"+names);
        
        System.out.println("en passant par le proxy");
        Proxy prox = new Proxy();
        File[] fileTabTxt = prox.loadFiles(nomDossier);
        System.out.println("Taille de la liste :"+fileTabTxt.length);
        String nameTxt = prox.toString(fileTabTxt);
        System.out.println("Liste des fichiers .txt présents dans le fichier "+nomDossier+" :\n"+nameTxt);
    }
}
