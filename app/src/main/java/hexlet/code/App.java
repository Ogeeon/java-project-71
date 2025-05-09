package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 0.1",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<String> {

    @CommandLine.Parameters(index = "0", description = "path to first file")
    private String filePath1;

    @CommandLine.Parameters(index = "1", description = "path to second file")
    private String filePath2;

    @CommandLine.Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
    private String format = "stylish";

    @Override
    public String call() throws Exception {
        var diff = Differ.generate(filePath1, filePath2, format);
        System.out.println(diff);
        return "OK";
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
