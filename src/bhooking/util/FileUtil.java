package bhooking.util;



import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * A utility class to read/write text lines from/to a text file
 */
public class FileUtil extends Util {
	
	public List<String> readLines(String inFileName) throws FileNotFoundException {
		return super.readLines(inFileName, new BufferedReader (new FileReader(inFileName)));
	}

	public void writeLines(String outFileName, List<String> lines) throws IOException {
		super.writeLines(outFileName, lines);
	}
	
	public void removeLines(String outFileName, String toRemove) throws IOException {
		super.removeLine(outFileName,toRemove);
	}
	
	

}
