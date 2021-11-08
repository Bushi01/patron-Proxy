package petitProxyFichier;

import java.io.File;

/**
 * @author  (DEMAISON)
 * @version (05-01-2021)
 */

public interface Subject {
    File[] loadFiles(String path);
}
