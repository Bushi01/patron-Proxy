package petitProxyFichier;

import java.io.*;
/**
 * @author (DEMAISON)
 * @version (05-01-2021)
 */
public class ProxFileNameFilter implements FilenameFilter{
    
    private String extension;
    
    public ProxFileNameFilter(String extension){
        this.extension = extension;
    }
    
    public boolean accept(File dir,String name){
        return name.endsWith(extension);
    }

}
