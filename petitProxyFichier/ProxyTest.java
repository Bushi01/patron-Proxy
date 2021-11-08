package petitProxyFichier;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.*;
import java.nio.file.*;

public class ProxyTest extends junit.framework.TestCase {

    public void testFichier(){
        Path path = Paths.get("petitProxyFichier\\dossier");
        System.out.println("nom :"+path.getFileName());
        System.out.println("nom :"+path.getFileName().toString());
        assertEquals(path.getFileName().toString(),"dossier");

        File fichier = path.toFile();
        assertEquals(fichier.getName(),"dossier");
        // System.out.println("nom :"+fichier.getName());
        File[] fileTab = fichier.listFiles();
        assertTrue(fileTab!=null);
        //System.out.println(fileTab!=null);
        assertFalse(fileTab.length==0);
        assertTrue(fileTab.length==3);
        //System.out.println(fileTab.length);

        String[] strFileTab = fichier.list();
        assertTrue(strFileTab!=null);
        //System.out.println(strFileTab!=null);
        assertFalse(strFileTab.length==0);
        assertTrue(strFileTab.length==3);
        //System.out.println(strFileTab.length);

        String s1="";
        for(File f : fileTab){
            s1+=f.getName();
            //System.out.println("nom :"+f.getName());
        }
        //System.out.println(s1);
        assertEquals("fichier1.txtfichier2.txtfichier3.sql",s1);
        String s2="";
        for(String str : strFileTab){
            s2+=str;
            //System.out.println("nom :"+str);
        }
        assertEquals("fichier1.txtfichier2.txtfichier3.sql",s2);
        assertEquals(s1,s2);
    }

    public void testRealSubject(){
        RealSubject rsub = new RealSubject();
        File[] fileTab = rsub.loadFiles("petitProxyFichier\\dossier");//"proxy\\dossier" "dossier"
        assertTrue(fileTab.length==3);
        //System.out.println("taille :"+fileTab.length);
        String names = rsub.toString(fileTab);
        //System.out.println(names);
        assertEquals("fichier1.txt"+"\n"+"fichier2.txt"+"\n"+"fichier3.sql"+"\n",names);
    }
    
    public void testProxy(){
        Proxy prox = new Proxy();
        File[] fileTab1 = prox.loadFiles("petitProxyFichier\\dossier");
        assertTrue(fileTab1.length==2);
        String names = prox.toString(fileTab1);
        assertEquals("fichier1.txt"+"\n"+"fichier2.txt"+"\n",names);
        
        RealSubject rsub = new RealSubject();
        File[] fileTab2 = rsub.loadFiles("petitProxyFichier\\dossier");
        assertTrue(fileTab2.length==3);
        String names2 = prox.toStringTxt(fileTab2);
        assertEquals("fichier1.txt"+"\n"+"fichier2.txt"+"\n",names2);
        String names3 = rsub.toString(fileTab2);
        assertEquals("fichier1.txt"+"\n"+"fichier2.txt"+"\n"+"fichier3.sql"+"\n",names3);
    }
}
