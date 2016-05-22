package reddit.springboot.ranking.cleaner;

import java.io.File;
import java.util.ArrayList;

public class FileUtil {


    public static ArrayList<String> listFilesForFolder(final File folder) {
        ArrayList<String> filenames = new ArrayList<String>();
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                filenames.add(fileEntry.getAbsolutePath());
            }
        }
        return filenames;
    }
}
