package flashcards;

import java.util.LinkedList;
import java.util.Random;

public class Cards {
    private LinkedList<Card> list;

    public LinkedList<Card> getList() {
        return list;
    }

    public void setList(LinkedList<Card> list) {
        this.list = list;
    }

    public Cards() {
        this.list = new LinkedList<>();
    }

    private int containsTerm(String term) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getTerm().compareToIgnoreCase(term) == 0) {
                return i;
            }
        }
        return -1;
    }

    private int containsDefinition(String definition) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getDefinition().compareToIgnoreCase(definition) == 0) {
                return i;
            }
        }
        return -1;
    }

    public void put(String term, String definition, String mistakes) {
        put(term, definition, Integer.parseInt(mistakes));
    }

    public void put(String term, String definition, int mistakes) {
        int index = containsTerm(term);
        if (index >= 0) {
            list.get(index).setDefinition(definition);
            list.get(index).setMistakes(mistakes);
        } else {
            list.add(new Card(term, definition, mistakes));
        }
    }

    public void add() {
        Input input = Input.getInstance();
        String term = input.getString("The card : ");
        if (containsTerm(term) >= 0) {
            input.addLog("The card \"" + term + "\" already exists.");
            return;
        }
        String definition = input.getString("The definition of the card : ");
        if (containsDefinition(definition) >= 0) {
            input.addLog("The definition \"" + definition + "\" already exists.");
            return;
        }
        list.add(new Card(term, definition, 0));
        input.addLog("The pair (\"" + term + "\":\"" + definition + "\") has been added.");
    }

    public void remove() {
        Input input = Input.getInstance();
        String term = input.getString("The card : ");
        int index = containsTerm(term);
        if (index >= 0) {
            list.remove(index);
            input.addLog("The card has been removed.");
        } else {
            input.addLog("Can`t remove \"" + term + "\" there is no such card.");
        }
    }

    public int size() {
        return list.size();
    }

    private LinkedList<Card> createAsk(int numAsk) {
        Random random = new Random();
        LinkedList<Card> out = new LinkedList<>(list);
        if (numAsk > list.size()) {
            while (numAsk > out.size()) {
                out.add(list.get(random.nextInt(list.size())));
            }
        } else {
            while (out.size() > numAsk) {
                out.remove(random.nextInt(out.size()));
            }
        }

        return out;
    }

    public void ask() {
        Input input = Input.getInstance();
        for (Card item : createAsk(input.getInt("How many times to ask? "))) {
            String answer = input.getString("Print the definition of \""
                    + item.getTerm()
                    + "\": ");
            if (answer.compareTo(item.getDefinition()) == 0) {
                input.addLog("Correct answer.");
            } else {
                item.incrementMistakes();
                int index = containsDefinition(answer);
                if (index > 0) {
                    input.addLog("Wrong answer. The correct one is \""
                            + item.getDefinition()
                            + "\" you've just written the definition of \""
                            + list.get(index).getTerm()
                            + "\""
                    );
                } else {
                    input.addLog("Wrong answer. The correct one is \""
                            + item.getDefinition()
                            + "\"."
                    );
                }
            }
        }
    }

    public void hardestCard() {
        Input input = Input.getInstance();
//        Find MAX value "Mistakes"
        int max = 0;
        for (Card card : list) {
            if (card.getMistakes() > max) {
                max = card.getMistakes();
            }
        }
        if (max == 0) {
            input.addLog("There are no cards with errors.");
        } else {
//            Find all "Mistakes" when "Mistakes" == MAX
            LinkedList<String> listHardestCard = new LinkedList<>();
            for (Card card : list) {
                if (card.getMistakes() == max) {
                    listHardestCard.add("\"" + card.getTerm() + "\"");
                }
            }
//            Create strOut to log
            StringBuilder strOut = new StringBuilder();

            if (listHardestCard.size() > 1) {
                strOut.append("The hardest cards are ");
            } else {
                strOut.append("The hardest card is ");
            }

            for (int i = 0; i < listHardestCard.size(); i++) {
                strOut.append(listHardestCard.get(i));
                if (i < listHardestCard.size() - 1) {
                    strOut.append(", ");
                }
            }

            strOut.append(". You have ");
            strOut.append(max);
            strOut.append(" errors answering ");

            if (listHardestCard.size() > 1) {
                strOut.append("them.");
            } else {
                strOut.append("it.");
            }

            input.addLog(strOut.toString());
        }
    }

    public void resetStats() {
        for (Card item : list) {
            item.setMistakes(0);
        }
        Input input = Input.getInstance();
        input.addLog("Card statistics has been reset.");
    }
}