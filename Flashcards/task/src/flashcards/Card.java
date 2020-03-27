package flashcards;

public class Card {
    String term;
    String definition;
    String mistakes;

    public Card(String term, String definition, String mistakes) {
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

    public String getMistakes() {
        return mistakes;
    }

    public void setMistakes(String mistakes) {
        this.mistakes = mistakes;
    }
}
