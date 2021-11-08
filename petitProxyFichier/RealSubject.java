package petitProxyFichier;

import java.io.File;
import java.nio.file.*;//Paths et Path

/**
 * @author (DEMAISON)
 * @version (05-01-2021)
 */
public class RealSubject implements Subject{
    public File[] loadFiles(String path){
        Path p = Paths.get(path);
        File f = p.toFile();
        if(f.isFile()){
            String f2 = f.getParent();
            if(f2!=null) loadFiles(f2);
        }
        return f.listFiles();
    }
    
    public String toString(File[] tabFile){
        StringBuffer sf = new StringBuffer();
        
        for(File f : tabFile){
            sf.append(f.getName()+"\n");
        }
        
        return sf.toString();
    }
}
