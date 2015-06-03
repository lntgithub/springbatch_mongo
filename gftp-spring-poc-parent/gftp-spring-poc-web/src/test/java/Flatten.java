import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */

/**
 * @author ap16737
 *
 */
public class Flatten {

	public static void main(String args[]) throws Exception {
		if (args.length < 2) {
			usage();
		}
		File dir = new File(args[0]);
		File outputDir = new File(args[1]);
		if (dir.exists() && dir.canRead()) {
			List<File> children = getAllChildrenFiles(dir);
			for (File child : children) {
				copyFile(child, getDestFile(outputDir, child));
			}
		}
	}

	private static List<File> getAllChildrenFiles(File sourceDir) {
		List<File> files = new ArrayList<File>();
		File[] children = sourceDir.listFiles();
		for (File child : children) {
			if (child.isFile()) {
				files.add(child);
			} else {
				files.addAll(getAllChildrenFiles(child));
			}
		}
		return files;
	}

	private static File getDestFile(File outputDir, File child) {
		String childName = child.getName();
		File dest = new File(outputDir, childName);
		int count = 1;
		while (dest.exists()) {
			dest = new File(outputDir, childName + count++);
		}
		return dest;
	}

	private static void copyFile(File source, File dest) throws IOException {

		/*
		 * FileChannel inputChannel = null; FileChannel outputChannel = null;
		 * try { inputChannel = new FileInputStream(source).getChannel();
		 * outputChannel = new FileOutputStream(dest).getChannel();
		 * outputChannel.transferFrom(inputChannel, 0, inputChannel.size()); }
		 * finally { inputChannel.close(); outputChannel.close(); }
		 */
		Files.copy(source.toPath(), dest.toPath(),
				StandardCopyOption.COPY_ATTRIBUTES);
		System.out.println("File " + source.getAbsolutePath() + " copied to "
				+ dest.getAbsolutePath());
	}

	private static void usage() {
		System.err.println("USAGE: \njava " + Flatten.class.getName()
				+ " <source dir> <destination dir>");
		System.exit(1);
	}
}
