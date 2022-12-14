package concurrent.visibility;

import java.io.File;
import java.io.IOException;

public class FileTest {

    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\Fut\\Desktop\\test\\TestIn.txt");
        File fileToSave = new File("C:\\Users\\Fut\\Desktop\\test\\TestOut.txt");
        File fileToSaveUnicode = new File("C:\\Users\\Fut\\Desktop\\test\\TestOutUnicode.txt");
        ParseFile parse = new ParseFile(file);
        parse.getContent();
        parse.getContentWithoutUnicode();
        SaveFile save = new SaveToTXTFile();
        save.save(parse.getContent(), fileToSave);
        save.save(parse.getContentWithoutUnicode(), fileToSaveUnicode);
    }
}
