package StaticConstants;

import java.io.File;

/**
 * Created by MARKAN on 28.06.2017.
 */
public final class Files {
    public static final String myCity="city.json";
    public static boolean IsExistsFile(File mainDir, String path)
    {
        return new File(mainDir,path).exists();
    }
}
