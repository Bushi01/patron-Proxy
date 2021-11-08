package petitProxyFichier;

import java.io.*;
import java.nio.file.*;
/**
 * En passant par le proxy, le client obtient la liste des fichiers texte du dossier specifie.
 *
 * @author (DEMAISON)
 * @version (05-01-2021)
 */
public class Proxy implements Subject{
    Subject sujet = null;
    
    public Proxy(){
        this.sujet = new RealSubject();
    }
    
    public File[] loadFiles(String path){
        Path p = Paths.get(path);
        File f = p.toFile();
        FilenameFilter filter = new ProxFileNameFilter(".txt");
        if(f.isFile()){
            String f2 = f.getParent();
            if(f2!=null) loadFiles(f2);
        }
        return f.listFiles(filter);
    }
    
    public String toString(File[] tabFile){
        RealSubject rs = (RealSubject)sujet;
        return rs.toString(tabFile);
    }
    
    public String toStringTxt(File[] tabFile){
        StringBuffer sf = new StringBuffer();
        for(File f : tabFile){
            String s = f.getName();
            if(s.endsWith(".txt")){sf.append(s+"\n");}
        }
        return sf.toString();
    }
}
