package bhooking.loader;

import java.io.FileNotFoundException;
import java.util.List;

import bhooking.model.Castle;
import bhooking.parser.BhookingParser;
import bhooking.util.FileUtil;

public class BhookingLoader {

	private final static String root_castillos="data/castillos.dat"; 

	public static List<Castle> load() {
		List<String> lines;
		try {
			lines = new FileUtil().readLines(root_castillos);
			return new BhookingParser().parse(lines);
		} catch (FileNotFoundException e) {
			return null;
		}
	}
	
	public static List<Castle> load(String actualLocale) {
		List<String> lines;
		try {
			lines = new FileUtil().readLines("data/castillos_"+actualLocale+".dat");
			return new BhookingParser().parse(lines);
		} catch (FileNotFoundException e) {
			return null;
		}
	}

}
