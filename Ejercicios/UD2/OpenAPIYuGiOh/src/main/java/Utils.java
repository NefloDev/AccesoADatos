import entities.Card;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Utils {

    private static final int SEARCHBYTYPE = 1;
    private static final int SEARCHBYARCHETYPE = 2;
    private static final int SEARCHBYRACE = 3;
    private static ListCardManager manager;

    public static void loadCards(){
        manager = new ListCardManager();
    }

    public static void startMenu(){
        int menu;
        boolean finished = false;
        List<Card> tempList;

        do {
            tempList = null;

            menu = integerMenu("""
                    #################### YU-GI-OH! CARD DATABASE ####################
                    1. Show all cards
                    2. Show all types
                    3. Show all archetypes
                    4. Show all races
                    5. Show all cards with a specific type
                    6. Show all cards with a specific archetype
                    7. Show all cards with a specific race
                    8. Show a card by name
                    9. Exit
                    """, 1, 9);

            switch (menu){
                case 1-> {manager.getCards().forEach(c -> System.out.println(c.toString()));}
                case 2-> {manager.getAllTypes().forEach(System.out::println);}
                case 3-> {manager.getAllArchetypes().forEach(System.out::println);}
                case 4-> {manager.getAllRaces().forEach(System.out::println);}
                case 5-> {tempList = searchCardsBy(SEARCHBYTYPE);}
                case 6-> {tempList = searchCardsBy(SEARCHBYARCHETYPE);}
                case 7-> {tempList = searchCardsBy(SEARCHBYRACE);}
                case 8-> {System.out.println(searchCardByName().toString());}
                case 9-> {finished = true;}
            }

            if (menu>4 && menu<9){
                if(tempList != null){
                    tempList.forEach(c -> System.out.println(c.toString()));
                }else{
                    System.out.println("No cards found.");
                }
            }

            if (menu != 9) {
                Utils.waitToContinue();
            }
        }while (!finished);
    }

    public static List<Card> searchCardsBy(int instruction){
        StringBuilder sb = new StringBuilder();
        String message = "Select a";

        switch (instruction){
            case 1:
                manager.getAllTypes().forEach(c -> sb.append("- ").append(c).append("\n"));
                message += " type:\n";
                return manager.getCardByType(textMenu(message + sb + "\n"));
            case 2:
                manager.getAllArchetypes().forEach(c -> sb.append("- ").append(c).append("\n"));
                message += "n archetype:\n";
                return manager.getCardByArchetype(textMenu(message + sb + "\n"));
            case 3:
                manager.getAllRaces().forEach(c -> sb.append("- ").append(c).append("\n"));
                message += " race:\n";
                return manager.getCardByRace(textMenu(message + sb + "\n"));
            default:
                return null;
        }
    }

    public static Card searchCardByName(){
        StringBuilder sb = new StringBuilder();
        String message = "Select a card";
        manager.getNames().forEach(c -> sb.append("- ").append(c).append("\n"));
        return manager.getCardByName(textMenu(message + sb + "\n"));
    }

    public static int integerMenu(String message, int min, int max){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int input = 0;
        boolean repeat;

        do {
            repeat = false;
            try{
                System.out.println(message);
                input = Integer.parseInt(reader.readLine());

                if(input < min || input > max){
                    System.err.println("Input out of bounds.");
                    repeat = true;
                }
            }catch (IOException e){
                System.err.println("Error reading input.");
                repeat = true;
                Utils.waitToContinue();
            }catch (NumberFormatException e){
                System.err.println("Invalid input.");
                repeat = true;
                Utils.waitToContinue();
            }finally {
                reader = new BufferedReader(new InputStreamReader(System.in));
            }

        }while (repeat);

        return input;
    }
    
    public static String textMenu(String message){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        boolean repeat;

        do {
            repeat = false;
            try{
                System.out.println(message);
                input = reader.readLine();
                if(input.isEmpty()){
                    System.err.println("Input cannot be blank.");
                    repeat = true;
                }
            }catch (IOException e){
                System.err.println("Error reading input.");
                repeat = true;
                Utils.waitToContinue();
            }finally {
                reader = new BufferedReader(new InputStreamReader(System.in));
            }

        }while (repeat);

        return input;
    }

    public static void waitToContinue(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("\nPress enter to continue...");
        try{
            reader.readLine();
        }catch (IOException e){
            System.err.println("Error reading input.");
        }
        System.out.println("\n".repeat(10));
    }

}
