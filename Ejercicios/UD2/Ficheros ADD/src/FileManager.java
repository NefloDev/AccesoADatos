import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.stream.Stream;

public class FileManager {
    private final Path file;
    public FileManager(String url) {
        file = Path.of(url);
    }

    public void flattenDirectory(){
        try (Stream<Path> f = Files.walk(file).skip(1).sorted(Comparator.reverseOrder())){
            f.forEach(p -> {
                try {
                    if(Files.isRegularFile(p)){
                        Files.move(p.toAbsolutePath(), Path.of(file.toAbsolutePath() + "\\" + p.getFileName()));
                    }else{
                        Files.delete(p);
                    }
                } catch (IOException e) {
                    e.printStackTrace(System.out);
                }
            });
        }catch (IOException e){
            System.err.println("Couldn't manage directory flattening");
        }
    }

}