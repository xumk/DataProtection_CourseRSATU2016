import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Algo {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите P: ");
        String sP = reader.readLine();
        System.out.println("Введите G: ");
        String sQ = reader.readLine();
        Encryption encryption = new Encryption(Integer.parseInt(sP), Integer.parseInt(sQ));
        encryption.generationKey();
        System.out.println("Введите Hash: ");
        String hash = reader.readLine();
        encryption.signHash(Integer.parseInt(hash));
        try(FileWriter writer = new FileWriter("\\out.txt")) {
            // запись всей строки
            String text = encryption.getSignature();
            writer.write(text);
            // запись по символам
            writer.append('\n');
            writer.write(sP + "," + sQ + "," + encryption.getOpenKey());

            writer.flush();
            writer.close();
        } catch(IOException ex){
            System.out.println(ex.getMessage());
        }
  /*  Encryption encryption = new Encryption(23, 5);
            encryption.generationKey();
            encryption.signHash(3);
            String[] signature = encryption.getSignature().split(",");
            Decryption decryption = new Decryption(23, 5, encryption.getOpenKey());
            if (!decryption.decode(Integer.valueOf(signature[0]), Integer.valueOf(signature[1]), Integer.valueOf(signature[2]))) {
                System.out.println("Подпись: " + encryption.getSignature());
                System.out.println("Открытый ключь:" + encryption.getOpenKey());
                System.out.println("Закрытый ключ: " + encryption.getExitKey());
            }
            System.out.println("Подпись верна");*/

    }
}
