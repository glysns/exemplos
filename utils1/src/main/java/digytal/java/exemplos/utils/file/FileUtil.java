package digytal.java.exemplos.utils.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtil {
	public static File file(String root, String ... filePath) {
		
		return new File(root,"");
	}
	public static File file(String filePath) {
		return new File(filePath);
	}
	public static InputStream inputStream(String filePath) throws FileNotFoundException {
		return new FileInputStream(filePath);	
	}
	public static InputStream inputStream(File file) throws FileNotFoundException {
		return new FileInputStream(file);	
	}
	
	public static Path path(String root, String fileName) {
		return Paths.get(root, fileName);
	}
	public static Path path(String filePath) {
		return Paths.get(filePath);
	}
}
