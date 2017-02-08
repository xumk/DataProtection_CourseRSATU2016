/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.login;

import encryptionMethods.base.SubstitutionCipher;
import encryptionMethods.bitrevers.BitReversCipher;
import encryptionMethods.monoAlphabet.MonoAlphabetCipher;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Алексей
 */
public class FXMLController implements Initializable {

    static Stage STAGE;
    static String nameMethod1;
    static String nameMethod2;
    private SubstitutionCipher bitRevers = new BitReversCipher();
    private SubstitutionCipher monoAlphabet = new MonoAlphabetCipher();

    @FXML
    private Pane pane;
    @FXML
    private TextArea textArea;
    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu fileMenu;
    @FXML
    private MenuItem open;
    @FXML
    private MenuItem clear;
    @FXML
    private MenuItem exit;
    @FXML
    private Menu encodeMenu;
    @FXML
    private MenuItem methodCodeOne;
    @FXML
    private MenuItem methodCodeTwo;
    @FXML
    private MenuItem methodCodeThree;
    @FXML
    private MenuItem methodCodeFour;
    @FXML
    private MenuItem methodCodeFive;
    @FXML
    private MenuItem methodCodeSix;
    @FXML
    private MenuItem methodCodeSeven;
    @FXML
    private MenuItem methodCodeEight;
    @FXML
    private Menu decodeMenu;
    @FXML
    private MenuItem methodDecodeOne;
    @FXML
    private MenuItem methodDecodeTwo;
    @FXML
    private MenuItem methodDecodeThree;
    @FXML
    private MenuItem methodDecodeFour;
    @FXML
    private MenuItem methodDecodeFive;
    @FXML
    private MenuItem methodDecodeSix;
    @FXML
    private MenuItem methodDecodeSeven;
    @FXML
    private MenuItem methodDecodeEight;
    @FXML
    private Menu help;
    @FXML
    private MenuItem help1;
    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private MenuItem saveas;
    EventHandler nonMethodHandler;
    Map<String, EventHandler> handlerMap = new HashMap<>();

    {
        nonMethodHandler = event -> {
            Stage dialog = new Stage();
            dialog.initStyle(StageStyle.UTILITY);
            dialog.setTitle("Ввод ключа");
            VBox box = new VBox();
            box.setAlignment(Pos.CENTER);
            HBox buttons = new HBox();
            buttons.setAlignment(Pos.CENTER);
            Button buttonOk = new Button("Ok");
            buttonOk.setOnAction((ActionEvent evt) -> {
                dialog.close();
            });

            buttons.getChildren().addAll(buttonOk);
            box.getChildren().addAll(new Label("Метод не реализован"), buttons);
            Scene scene = new Scene(box, 300, 100);
            dialog.setScene(scene);
            dialog.show();
        };

        handlerMap.put("encodeMonoAlphabet", event -> {
            Stage dialog = new Stage();
            dialog.initStyle(StageStyle.UTILITY);
            dialog.setTitle("Окно ввода ключа");
            VBox box = new VBox();
            box.setAlignment(Pos.CENTER);
            HBox buttons = new HBox();
            TextField txtField = new TextField();
            buttons.setAlignment(Pos.CENTER);
            Button buttonOk = new Button("Ok");
            buttonOk.setOnAction((ActionEvent evt) -> {
                int key = Integer.valueOf(txtField.getText());
                monoAlphabet.calculationPrivateAlphabet(key);
                dialog.close();
                String codeText = monoAlphabet
                        .encodeText(textArea.getText().replaceAll("\n", " "));
                textArea.clear();
                textArea.setText(codeText);
            });
            Button buttonEx = new Button("Cancel");
            buttonEx.setOnAction(evt -> {
                dialog.close();
            });
            buttons.getChildren().addAll(buttonOk, buttonEx);
            box.getChildren().addAll(new Label("Введите ключ"), txtField, buttons);
            Scene scene = new Scene(box, 300, 100);
            dialog.setScene(scene);
            dialog.show();
        });

        handlerMap.put("encodeBitRevers", event -> {
            Stage dialog = new Stage();
            dialog.initStyle(StageStyle.UTILITY);
            dialog.setTitle("Окно ввода ключа");
            VBox box = new VBox();
            box.setAlignment(Pos.CENTER);
            HBox buttons = new HBox();
            TextField txtField = new TextField();
            buttons.setAlignment(Pos.CENTER);
            Button buttonOk = new Button("Ok");
            buttonOk.setOnAction((ActionEvent evt) -> {
                bitRevers.calculationPrivateAlphabet(txtField.getText());
                dialog.close();
                String codeText = bitRevers
                        .encodeText(textArea.getText().replaceAll("\n", " "));
                textArea.clear();
                textArea.setText(codeText);
            });
            Button buttonEx = new Button("Cancel");
            buttonEx.setOnAction(evt -> {
                dialog.close();
            });
            buttons.getChildren().addAll(buttonOk, buttonEx);
            box.getChildren().addAll(new Label("Введите ключ"), txtField, buttons);
            Scene scene = new Scene(box, 300, 100);
            dialog.setScene(scene);
            dialog.show();
        });

        handlerMap.put("decodeMonoAlphabet", event -> {
            Stage dialog = new Stage();
            dialog.initStyle(StageStyle.UTILITY);
            dialog.setTitle("Окно ввода ключа");
            VBox box = new VBox();
            box.setAlignment(Pos.CENTER);
            HBox buttons = new HBox();
            TextField txtField = new TextField();
            buttons.setAlignment(Pos.CENTER);
            Button buttonOk = new Button("Ok");
            buttonOk.setOnAction((ActionEvent evt) -> {
                int key = Integer.valueOf(txtField.getText());
                monoAlphabet.calculationPrivateAlphabet(key);
                dialog.close();
                String decodeText = monoAlphabet
                        .decodeText(textArea.getText().replaceAll("\n", " "));
                textArea.clear();
                textArea.setText(decodeText);
            });
            Button buttonEx = new Button("Cancel");
            buttonEx.setOnAction(evt -> {
                dialog.close();
            });
            buttons.getChildren().addAll(buttonOk, buttonEx);
            box.getChildren().addAll(new Label("Введите ключ"), txtField, buttons);
            Scene scene = new Scene(box, 300, 100);
            dialog.setScene(scene);
            dialog.show();
        });

        handlerMap.put("decodeBitRevers", event -> {
            Stage dialog = new Stage();
            dialog.initStyle(StageStyle.UTILITY);
            dialog.setTitle("Окно ввода ключа");
            VBox box = new VBox();
            box.setAlignment(Pos.CENTER);
            HBox buttons = new HBox();
            TextField txtField = new TextField();
            buttons.setAlignment(Pos.CENTER);
            Button buttonOk = new Button("Ok");
            buttonOk.setOnAction((ActionEvent evt) -> {
                bitRevers.calculationPrivateAlphabet(txtField.getText());
                dialog.close();
                String decodeText = bitRevers
                        .decodeText(textArea.getText().replaceAll("\n", " "));
                textArea.clear();
                textArea.setText(decodeText);
            });
            Button buttonEx = new Button("Cancel");
            buttonEx.setOnAction(evt -> {
                dialog.close();
            });
            buttons.getChildren().addAll(buttonOk, buttonEx);
            box.getChildren().addAll(new Label("Введите ключ"), txtField, buttons);
            Scene scene = new Scene(box, 300, 100);
            dialog.setScene(scene);
            dialog.show();
        });
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        encodeMenu.getItems().stream().filter(menuItem -> {
            String id = menuItem.getId();
            return id.equals("encode" + FXMLController.nameMethod1) || id.equals("encode" + FXMLController.nameMethod2);
        }).forEach(it -> {
            String id = it.getId();
            it.setOnAction(getHandlerByMethodName(id));
            it.setDisable(false);
        });
        decodeMenu.getItems().stream().filter(menuItem -> {
            String id = menuItem.getId();
            return id.equals("decode" + FXMLController.nameMethod1) || id.equals("decode" + FXMLController.nameMethod2);
        }).forEach(it -> {
            String id = it.getId();
            it.setOnAction(getHandlerByMethodName(id));
            it.setDisable(false);
        });
    }

    private EventHandler getHandlerByMethodName(String methodName) {
        if (handlerMap.containsKey(methodName)) {
            return handlerMap.get(methodName);
        }
        return nonMethodHandler;
    }

    @FXML
    private void onActionProperty(ActionEvent event) {
        STAGE.close();
    }

    @FXML
    private void onClearTextArea(ActionEvent event) {
        textArea.clear();
    }

    @FXML
    private void openFile(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();//Класс работы с диалогом выборки и сохранения 
        fileChooser.setTitle("Open Document");//Заголовок диалога 
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("txt files (*.txt)", "*.txt");//Расширение 
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(STAGE);

        if (file != null) {
            try {
                //создаем объект FileReader для объекта File
                FileInputStream fis = new FileInputStream(file); //создаем BufferedReader с существующего FileReader для построчного считывания
                BufferedReader reader = new BufferedReader(new InputStreamReader(fis, "UTF-8")); // считаем сначала первую строку
                String line = reader.readLine();
                StringBuilder sb = new StringBuilder();
                while (line != null) {
                    sb.append(line);
                    // считываем остальные строки в цикле
                    line = reader.readLine();
                }
                textArea.setText(sb.toString());
            } catch (FileNotFoundException e) {
                System.out.println("Файл не найден");
            } catch (IOException e) {
                System.out.println("Исключение: " + e.getMessage());
            }
        }

    }

    @FXML
    private void saveFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();//Класс работы с диалогом выборки и сохранения 
        fileChooser.setTitle("Save Document");//Заголовок диалога 
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("txt files (*.txt)", "*.txt");//Расширение 
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(STAGE);//Указываем текущую сцену 
        if (file != null) {
            try {
                FileWriter fw = new FileWriter(file);
                fw.append(textArea.getText());
                fw.append("\n"); //переходим на новую строку
                fw.flush();
                fw.close();
            } catch (Exception ex) {
                System.out.println(ex.toString()); //чтобы хоть что-то знать о возможной ошибке
            }

        }
    }

    @FXML
    private void getHelpMethod(ActionEvent event) {
        Stage dialog = new Stage();
        dialog.initStyle(StageStyle.UTILITY);
        dialog.setTitle("Справка");
        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        TextArea txtAreaHelp = new TextArea();
        txtAreaHelp.appendText("Моноалфавитная замена \n");
        txtAreaHelp.appendText(
                "При Моноалфавитной замене каждой букве открытого текста ставится соответствие одна буква \n"
                + "закрытого текста из этого же алфавита. y_i=(K_1 X_i+K_2 ) mod n, где: n-длинна алфавита\n"
                + "К_1 и К_2- это константы. К_1=1,К_2- это смещение \n"
                + "сиволов закрытого алфавита относительно открытого алфавита, \n"
                + "если К_1=1,К_2=3, то это так называемый код Цезаря. X_i- это код \n"
                + "i символа открытого алфавита Y_i- это код i символа закрытого алфавита. \n"
        );
        txtAreaHelp.appendText("Побитовая перестановка\n");
        txtAreaHelp.appendText(
                "Несколько более сложной является побитовая перестановка, при которой в \n"
                + "соответствии с вектором перестановки изменяются позиции разрядов двоичного кода \n"
                + "символов открытого текста, обычно берутся ASCII коды. \n"
        );

        HBox buttons = new HBox();
        buttons.setAlignment(Pos.CENTER);
        Button buttonOk = new Button("Ok");
        buttonOk.setOnAction((ActionEvent evt) -> {
            dialog.close();
        });

        buttons.getChildren().addAll(buttonOk);
        box.getChildren().addAll(txtAreaHelp, buttons);
        Scene scene = new Scene(box, 600, 200);
        dialog.setScene(scene);
        dialog.show();
    }

}
