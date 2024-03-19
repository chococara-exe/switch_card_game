import java.util.*;

public class Stock {
    private final Stack<Card> cards = new Stack<Card>();

    String[] ranks = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
    String[] suit = {"D", "C", "H", "S"};

    public Stock() {
        // creates and shuffles the stock
        for (int i = 0; i < suit.length; i++) {
            for (int j = 0; j < ranks.length; j++){
                this.cards.push(new Card(suit[i],ranks[j]));
            }
        }
        Collections.shuffle(this.cards);
    }

    public Stack<Card> getStock(){
        return cards;
    }

    public Card drawCard() {
        return cards.pop();
    }
}