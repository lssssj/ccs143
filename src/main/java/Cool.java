import cool.CoolLexer;
import cool.CoolParser;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;


public class Cool {


  public static void main(String[] args) throws Exception {
      compile("comp_is_not_int.cl");
  }

  public static programc compile(String... files) throws IOException {
    Classes classes = new Classes(0);
    CoolAstBuilder coolAstBuilder = new CoolAstBuilder();

    for (String file : files) {
      String filePath = Cool.class.getClassLoader().getResource(file).getFile();
      Reader reader = new FileReader(filePath);
      CharStream c = CharStreams.fromReader(reader);
      CoolLexer coolLexer = new CoolLexer(c);
      CommonTokenStream commonTokenStream = new CommonTokenStream(coolLexer);
      CoolParser coolParser = new CoolParser(commonTokenStream);
      coolAstBuilder.setFileName(file);
      programc programTree = (programc) coolParser.program().accept(coolAstBuilder);
      for (int i = 0; i < programTree.classes.getLength(); i++) {
        classes.appendElement(programTree.classes.getNth(i));
      }
    }
    programc cool = new programc(0, classes);
    cool.semant();
    return cool;
  }
}
