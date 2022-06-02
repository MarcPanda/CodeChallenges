package assistant;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class OpenKattis {
	static final File SAMPLES_DIR = new File("samples");

	
	private static String samplesURL(String id) {
		return problemURL(id) + "/file/statement/samples.zip";
	}
	
	
	private static String problemURL(String id) {
		return "https://open.kattis.com/problems/" + id;
	}

	
	public static boolean problemExists(String id) {
		try {
			HttpURLConnection conn = (HttpURLConnection) new URL(problemURL(id)).openConnection();
			conn.connect();
			return conn.getResponseCode() == 200;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	
	
	
	public static File downloadSampleDataFile(String id) {
		File sampleDest = new File(SAMPLES_DIR, id);

		if (sampleDest.isDirectory()) {
			return sampleDest;
		}
		
		try {
			ZipInputStream unzip = new ZipInputStream(new URL(samplesURL(id)).openStream());
			byte[] buffer = new byte[2048];
			ZipEntry entry;
			while ((entry = unzip.getNextEntry()) != null) {
				Path filePath = sampleDest.toPath().resolve(entry.getName());
				filePath.toFile().getParentFile().mkdirs();
                try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath.toFile()))) {

                    int len;
                    while ((len = unzip.read(buffer)) > 0) {
                    	bos.write(buffer, 0, len);
                    }
                }
                System.out.println("Unzipped file to " + filePath);
			}
			return sampleDest;
		} catch (FileNotFoundException e) {
			System.err.println("Sample zip file not found on the server.");
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
