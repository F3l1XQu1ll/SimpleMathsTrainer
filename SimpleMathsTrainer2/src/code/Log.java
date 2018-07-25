package code;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Log<T extends Object> {

    private Path log_path = Paths.get(System.getProperty("user.home"), "SimpleMathsTrainer", "log.txt");
    private BufferedWriter writer;

    public Log() {
	if (Files.exists(log_path)) {
	    try {
		writer = Files.newBufferedWriter(log_path, StandardOpenOption.APPEND, StandardOpenOption.CREATE);
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	} else {
	    try {
		Files.createDirectory(log_path.getParent());
		writer = Files.newBufferedWriter(log_path, StandardOpenOption.APPEND, StandardOpenOption.CREATE);
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}

    }

    public void log(T t) {
	try {
	    writer.write(t.toString());
	    writer.newLine();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    public void close() {
	try {
	    writer.close();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

}
