package flashcards;

public class Card {
    String term;
    String definition;
    int mistakes;

    public Card(String term, String definition, int mistakes) {
        this.term = term;
        this.definition = definition;
        this.mistakes = mistakes;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public int getMistakes() {
        return mistakes;
    }

    public void setMistakes(int mistakes) {
        this.mistakes = mistakes;
    }

    public void incrementMistakes() {
        ++mistakes;
    }

    @Override
    public String toString() {
        return "Card{" +
                "term='" + term + '\'' +
                ", definition='" + definition + '\'' +
                ", mistakes=" + mistakes +
                '}';
    }
}
