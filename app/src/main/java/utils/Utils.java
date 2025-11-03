package utils;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Utils {

	/**
	 * Read a text file located under the workspace `src` directory and return its contents as a String.
	 *
	 * @param relativePath path relative to the workspace `src` folder (for example: "main/resources/example.txt" or "test/resources/data.txt")
	 * @return file contents as a UTF-8 String
	 * @throws IOException if reading the file fails or file not found
	 */
	public static String readFileFromSrc(String relativePath) throws IOException {
		Path projectRoot = Paths.get(System.getProperty("user.dir"));
		Path file = projectRoot.resolve("app/src").resolve(relativePath);
		return Files.readString(file, StandardCharsets.UTF_8);
	}

	/**
	 * Convenience wrapper that throws an unchecked exception on IO errors.
	 * Useful for quick scripts or tests where you prefer runtime exceptions.
	 */
	public static String readFileFromSrcUnchecked(String relativePath) {
		try {
			return readFileFromSrc(relativePath);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

}
