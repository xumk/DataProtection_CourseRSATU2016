import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Алексей on 25.03.2016.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Введите исходный текст");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BitRevers bitRevers = new BitRevers();
        try {
            String originalText = reader.readLine();
            boolean exitProgramme = false;
            while (!exitProgramme) {
                System.out.println("Выберите действие");
                System.out.println("1 - Зашифровать");
                System.out.println("2 - Расшифровать");
                int act = Integer.valueOf(reader.readLine());
                boolean exitMethods = false;
                while (!exitMethods) {
                    System.out.println("Выберите метод: ");
                    System.out.println("1 - Вертикальная перестановка с ключом.");
                    System.out.println("2 - Побитовая перестановка.");
                    System.out.println("3 - Код Виженера.");
                    System.out.println("4 - Гоммирование с ключом.");
                    int method = Integer.valueOf(reader.readLine());
                    switch (method) {
                        case 1:
                            System.out.println("Метод не реализован!");
                            break;
                        case 2:
                            System.out.println("Введите ключ 5-ти значный ключ: ");
                            String  key = reader.readLine();
                            bitRevers.calcExitAlphabet(key);
                            if (act == 1) {
                                originalText = bitRevers.codText(originalText);
                                System.out.println(originalText);
                            } else {
                                originalText = bitRevers.decodText(originalText);
                                System.out.println(originalText);
                            }
                            exitMethods = true;
                            break;
                        case 3:
                            System.out.println("Метод не реализован!");
                            break;
                        case 4:
                            System.out.println("Метод не реализован!");
                            break;
                    }
                }
                System.out.println("Завершить? д/н: ");
                String select = reader.readLine();
                if (select.equals("д")) {
                    System.out.println("Пока-пока");
                    exitProgramme = true;
                }
            }
        } catch (IOException e) {
            System.out.println("Что-то пошло не так: " + e.toString());
        }
    }
}
