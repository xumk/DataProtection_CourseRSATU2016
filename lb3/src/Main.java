import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Алексей on 25.03.2016.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        MonoAlphabet monoAlphabet = new MonoAlphabet();
        boolean exitProgramme = false;

        System.out.println("Введите исходный текст");
        String originalText = reader.readLine();

        while (!exitProgramme) {
            System.out.println("Выберите действие");
            System.out.println("1 - Зашифровать");
            System.out.println("2 - Расшифровать");
            int act = Integer.valueOf(reader.readLine());
            boolean exitMethods = false;
            while (!exitMethods) {
                System.out.println("Выберите метод: ");
                System.out.println("1 - Моноалфавитная замена.");
                System.out.println("2 - Гомофоническая замена.");
                System.out.println("3 - Полиалфавитная замена.");
                System.out.println("4 - Полиграммная.");
                int method = Integer.valueOf(reader.readLine());
                switch (method) {
                    case 1:
                        System.out.println("Введите ключ: ");
                        int key = Integer.valueOf(reader.readLine());
                        monoAlphabet.calcExitAlphabet(key);
                        switch (act) {
                            case 1:
                                originalText = monoAlphabet.getCodeText(originalText);
                                System.out.println(originalText);
                                break;
                            case 2:
                                originalText = monoAlphabet.getDecodeText(originalText);
                                System.out.println(originalText);
                                break;
                            default:
                                System.out.println("Не известная команда");
                                break;
                        }
                        exitMethods = true;
                        break;
                    default:
                        String message = method >= 5 ? "Не известный метод" : "Метод не реализован!";
                        System.out.println(message);
                        break;
                }
            }
            System.out.println("Завершить? д/н: ");
            String select = reader.readLine();
            if (select.equalsIgnoreCase("д")) {
                System.out.println("Пока-пока");
                exitProgramme = true;
            }
        }
        reader.close();
    }
}