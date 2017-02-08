import java.io.*;

/**
 * Created by alex on 28.04.16.
 */
public class Decod {
    public static void main(String[] args) throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();

        File file = exists("\\out.txt");

        try {
            //Объект для чтения файла в буфер
            BufferedReader in = new BufferedReader(new FileReader( file.getAbsoluteFile()));
            try {
                //В цикле построчно считываем файл
                String s;
                while ((s = in.readLine()) != null) {
                    sb.append(s);
                    sb.append("\n");
                }
            } finally {
                //Также не забываем закрыть файл
                in.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
        String[] res = sb.toString().split("[,\\n]");
        String text;
        Decryption decryption = new Decryption(Integer.valueOf(res[3]), Integer.valueOf(res[4]), Integer.valueOf(res[5]));
        if (decryption.decode(Integer.valueOf(res[0]), Integer.valueOf(res[1]), Integer.valueOf(res[2]))) {
            text = "Подпись верна";
        } else {
            text = "Подпись не верна";
        }
        System.out.println(text);
    }

    private static File exists(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        if (!file.exists()){
            throw new FileNotFoundException(file.getName());
        }
        return file;
    }
}
