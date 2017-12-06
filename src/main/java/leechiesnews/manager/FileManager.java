package leechiesnews.manager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

public class FileManager {
	
//	public static File pickFirstRandomFolder (String directoryName) {
//         File[] fList = new File(directoryName).listFiles();
//         return fList[new Random().nextInt(fList.length)];
//	}
//	
//	// on récupere une liste de fichier d'un sous repertoire pris au hasard
//	public static List<File> listAllImageRandomFromSubDir (String directoryName) {
//		File repo = pickFirstRandomFolder(directoryName);
//		
//		// Get a random sub file
//		// list all file from this file's repo
//		return listAllFiles(repo.getAbsolutePath());
//	}
	
	private static List<File> listAllFiles(String directoryName) {
        File directory = new File(directoryName);

        List<File> resultList = new ArrayList<File>();

        // get all the files from a directory
        File[] fList = directory.listFiles();
        resultList.addAll(Arrays.asList(fList));
        for (File file : fList) {
            if (file.isFile()) {
                System.out.println(file.getAbsolutePath());
            } else if (file.isDirectory()) {
                resultList.addAll(listAllFiles(file.getAbsolutePath()));
            }
        }
        return resultList;
    } 
	
	public static String pickRandomFile (String folder) {
		File[] fileList = listAllFiles(folder).stream().toArray(File[]::new); 
		
		File randomFile =  fileList[new Random().nextInt(fileList.length)];
		while (randomFile.isDirectory()) {
			randomFile =  fileList[new Random().nextInt(fileList.length)];
		}	
		
		return randomFile.getAbsolutePath();
	}

	public static String uploadRandomImg (String folder) throws IOException {
		return uploadImg(pickRandomFile(folder));
	}
	
	public static String uploadImg (String file) throws IOException {
		Map<String, String> config = new HashMap<String, String>();
		config.put("cloud_name", "dl9wqrcoq");
		config.put("api_key", "412587243752429");
		config.put("api_secret", "vZxez4M0UeIcryHDj-7Oz0NR8LI");
		Cloudinary cloudinary = new Cloudinary(config);		
		
		Map<?, ?> rez = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
		System.out.println(rez.get("url"));
		return ""+rez.get("url");
	}	
	
	public static String randomFromStringArray(String[] part1) {
		return part1[new Random().nextInt(part1.length)];
	}
}
