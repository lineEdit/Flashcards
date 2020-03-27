package flashcards;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Input input = Input.getInstance();
//        input.logOff();
//        Cards cards = importCards("test.txt");
//        input.logOn();
        Cards cards = new Cards();
        while (true) {
            String action = input.getString("\nInput the action (" +
                    "add, " +
                    "remove, " +
                    "import, " +
                    "export, " +
                    "ask, " +
                    "exit, " +
                    "log, " +
                    "hardest card, " +
                    "reset stats" +
                    "): ");
            switch (action) {
                case "add": cards.add(); break;
                case "remove": cards.remove(); break;
                case "import": cards = importCards(input.getString("File name: ")); break;
                case "export": exportCards(cards, input.getString("File name: ")); break;
                case "ask": cards.ask(); break;
                case "exit": input.addLog("Bye bye!"); return;
                case "log": writeLogInFile(input.getString("File name: ")); break;
                case "hardest card": cards.hardestCard(); break;
                case "reset stats": cards.resetStats(); break;
                default:
                    throw new IllegalStateException("Unexpected value: " + action);
            }
        }
    }

    private static void exportCards(Cards cards, String fileName) {
        Input input = Input.getInstance();
        try(FileWriter fileWriter = new FileWriter(fileName)) {
            for (var entry : cards.getList()) {
                fileWriter.write(entry.getTerm()
                        + ":" + entry.getDefinition()
                        + ":" + entry.getMistakes()
                        + "\n");
            }
            input.addLog(cards.size() + " cards have been saved.");
        } catch (FileNotFoundException e) {
            input.addLog("File not found.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Cards importCards(String fileName) {
        Cards cards = new Cards();
        Input input = Input.getInstance();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] stringList = line.split(":");
                if (stringList.length == 3) {
                    cards.put(stringList[0], stringList[1], stringList[2]);
                }
            }
            input.addLog(cards.size() + " cards have been loaded.");
        } catch (FileNotFoundException e) {
            input.addLog("File not found.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cards;
    }

    public static void writeLogInFile(String fileName) {
        Input input = Input.getInstance();
        try(FileWriter fileWriter = new FileWriter(fileName)) {
            for (String item : Input.getInstance().getLog()) {
                fileWriter.write(item + "\n");
            }
            input.addLog("The log has been saved.");
        } catch (FileNotFoundException e) {
            input.addLog("File not found.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
