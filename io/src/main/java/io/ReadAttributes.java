package io;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class ReadAttributes
{
    public static void main(String[] args) throws IOException
    {
        final var home = System.getProperty("user.home");
        final var sourcesJar = Path.of(home, ".m2/repository/org/jboss/slf4j/slf4j-jboss-logging/1.2.0.Final/slf4j-jboss-logging-1.2.0.Final-sources.jar");
        final var filePath = Path.of("org/jboss/slf4j/JBossLoggerFactory.java");
        System.out.println(readAttributes(filePath, sourcesJar));
        System.out.println(isRegularFile(filePath, sourcesJar));
    }

    /**
     * Use Files.isRegularFile() to see if a file within a jar is a file or not.
     */
    private static boolean isRegularFile(Path clazz, Path jar) throws IOException
    {
        final var fs = FileSystems.newFileSystem(jar, null);
        List<Path> srcRoots = new ArrayList<>();
        for (Path root : fs.getRootDirectories()) {
            srcRoots.add(root);
        }

        for (Path root : srcRoots) {
            Path sourcePath = extendPath(root, clazz);
            return Files.isRegularFile(sourcePath);
        }

        return false;
    }

    private static Object readAttributes(Path clazz, Path jar) throws IOException
    {
        final var fs = FileSystems.newFileSystem(jar, null);
        List<Path> srcRoots = new ArrayList<>();
        for (Path root : fs.getRootDirectories()) {
            srcRoots.add(root);
        }

        for (Path root : srcRoots) {
            Path sourcePath = extendPath(root, clazz);
            return Files.readAttributes(sourcePath, BasicFileAttributes.class);
        }

        return null;
    }

    static Path extendPath(Path root, Path filePath) {
        String filePathString = filePath.toString();
        String fileSeparator = filePath.getFileSystem().getSeparator();
        String newSeparator = root.getFileSystem().getSeparator();
        if (!fileSeparator.equals(newSeparator)) {
            filePathString = filePathString.replace(fileSeparator, newSeparator);
        }
        return root.resolve(filePathString);
    }

}
