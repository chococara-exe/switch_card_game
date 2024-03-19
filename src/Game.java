import java.util.*;

public class Game {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // getting player name
        System.out.println("What is your name?");
        String name = scanner.nextLine();

        // getting number of players
        int players = 0;
        do {
            System.out.println("How many players do you want to play against? [1-4]");
            players = Integer.valueOf(scanner.nextLine());
            if (players <= 0 || players >= 5){
                System.out.println("Please enter an integer between 1 to 4");
            }
        }
        while (players <= 0 || players >= 5);

        // create new stock of cards
        Stock stock = new Stock();

        // create player list
        HashMap<String, Hand> playermap = new HashMap<String, Hand>();
        ArrayList<String> playerlist = new ArrayList<>();

        // create user hand and discard pile
        Hand user = new Hand(stock);
        ArrayList<Card> discard = new ArrayList<Card>();

        // add players into player list and distribute cards
        playermap.put(name, user);
        playerlist.add(name);
        for (int i = 1; i <= players; i++){
            playermap.put("Player " + i, new Hand(stock));
            playerlist.add("Player " + i);
        }
        discard.add(stock.drawCard());

        // gameplay loop
        while (true) {
            for (String player : playerlist) {
                Hand currhand = playermap.get(player);
                Card lastCard = discard.getLast();
                System.out.println(player + "'s Turn");
                System.out.println("Open card: " + lastCard);

                boolean valid = false;
                String play;
                Card playCard;

                System.out.println("Please select a card to play: " + currhand.getHand());
                play = scanner.nextLine();
                playCard = new Card(Character.toString(play.charAt(0)), Character.toString(play.charAt(1)));

                valid = currhand.CheckInHand(play).a && (Objects.equals(playCard.getRank(), lastCard.getRank()) || Objects.equals(playCard.getSuit(), lastCard.getSuit()));
                System.out.println(player + " selected " + playCard);

                if (!valid){
                    System.out.println(player + " blunders, take card x 1");
                    currhand.addCard(stock.drawCard());
                }
                else {
                    discard.add(currhand.playCard(currhand.CheckInHand(play).b));
                }

                if (currhand.getHand().isEmpty()){
                    System.out.println(player + " has won!");
                    break;
                }
            }
        }
    }
}