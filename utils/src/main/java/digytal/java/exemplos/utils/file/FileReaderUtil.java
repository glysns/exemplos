package digytal.java.exemplos.utils.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileReaderUtil {
	public static String reader(File file) {
		return null;
	}
	
	public static Stream<String> stream(String filePath) throws IOException{
		return Files.lines(Paths.get(filePath));
	}
	public static String read(String parent, String fileName) throws IOException{
		return new String(bytes(parent, fileName));
	}
	
	public static byte[] bytes(String parent, String fileName) throws IOException{
		byte[] bytes = Files.readAllBytes(Paths.get(parent,fileName));
		 return bytes;
	}
	
	public static String read(String filePath) throws IOException{
		return new String(bytes(filePath));
	}
	
	public static byte[] bytes(String filePath )throws IOException {
		 byte[] bytes = Files.readAllBytes(Paths.get(filePath));
		 return bytes;
	}
	
	public static String read(File file) throws IOException{
		return new String(bytes(file));
	}
	
	public static byte[] bytes(File file )throws IOException {
		 byte[] bytes = Files.readAllBytes(file.toPath());
		 return bytes;
	}
}
