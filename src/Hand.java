import java.util.*;

public class Hand {
    private ArrayList<Card> hand = new ArrayList<Card>();

    public Hand(Stock stock){
        for (int i = 0; i < 5; i++){
            this.hand.add(stock.drawCard());
        }
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public Pair CheckInHand(String play) {
        for (Card i : hand){
            if (Objects.equals(play, i.toString())){
                return new Pair(true, hand.indexOf(i));
            }
        }
        return new Pair(false, -1);
    }

    public Card playCard(int index){
        return hand.remove(index);
    }

    public void addCard(Card card){
        hand.add(card);
    }
}