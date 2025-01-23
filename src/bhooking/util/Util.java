package bhooking.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * A utility class to read/write text lines from/to a text file
 */
public class Util {

	protected List<String> readLines(String inFileName, BufferedReader in) throws FileNotFoundException {
		ArgumentChecks.isTrue(inFileName!=null && !inFileName.isBlank(), "Error en nombre de fichero");
		List<String> res = new LinkedList<>();
		try {
			try {
				String line;
				while((line = in.readLine()) != null) {
					res.add(line);
				}} finally {
					in.close();
				}
		}catch(FileNotFoundException e) {
			throw e;
		}
		catch(IOException e) {
			throw new RuntimeException();
		}
		return res;
	}

	protected void writeLines(String outFileName, List<String> lines) throws IOException {
		ArgumentChecks.isTrue(outFileName != null && !outFileName.isBlank(), "El nombre del archivo de salida no puede estar vacío.");
		ArgumentChecks.isTrue(lines != null, "La lista de líneas no puede ser nula.");

		try (BufferedWriter out = new BufferedWriter(new FileWriter(outFileName, true))) {
			for (String line : lines) {
				out.write(line);
				out.newLine();
			}
		} catch (IOException e) {
			throw new RuntimeException("Error de escritura en el fichero "+outFileName+" : "+e.getMessage());
		}
	}

	protected void removeLine(String outFileName, String stringToRemove) throws IOException {
	    List<String> updatedLines = new ArrayList<>();
	    try (BufferedReader reader = new BufferedReader(new FileReader(outFileName))) {
	        String line;
	        while ((line = reader.readLine()) != null) {
	            if (!line.contains(stringToRemove)) {
	                updatedLines.add(line);
	            }
	        }
	    }
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(outFileName))) {
	        for (String updatedLine : updatedLines) {
	            writer.write(updatedLine);
	            writer.newLine();
	        }
	    }
	}



}
