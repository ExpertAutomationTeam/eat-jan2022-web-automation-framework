package utility;

import org.jsoup.helper.StringUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class Utilities {

    public static Properties loadProperties(String path) {
        Properties prop = new Properties();
        InputStream ism;
        try {
            ism = new FileInputStream(path);
            prop.load(ism);
            ism.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

//    public static String projectPath(){
//        String fileSeparator = File.separator;
//        Properties prop = Utilities.loadProperties("../config.properties");
//        String userDir = System.getProperty("user.dir");
//        String projectName = prop.getProperty("project.name");
//        List<String> directories = Arrays.asList(userDir.split(fileSeparator));
//        List<String> newDirectories = new ArrayList<String>();
//        for (String dirName: directories){
//            newDirectories.add(dirName);
//            if(dirName.equalsIgnoreCase(projectName)){
//                break;
//            }
//        }
//        return StringUtil.join(newDirectories, fileSeparator);
//    }
}
