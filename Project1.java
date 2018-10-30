import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import Stack.*;
/**
 * @author Logan Dabney
 * 10/17/19
 */
public class Project1 {

public static void main(String[] args) {
    int progNum, position, nextPreference;
    String name;
    ArrayList<Person> groupB = new ArrayList<>();
    ArrayList<Person> combination = new ArrayList<>();
    Person current, possiblePair;
    ArrayStack groupA = new ArrayStack();

    position = 0;
    nextPreference = 0;
    combination.add(new Person());

    try{
        BufferedReader reader= new BufferedReader(new FileReader("Project1TestData.txt"));
        progNum = Integer.valueOf(reader.readLine());
        // Reads the people and their preferences into objects(Person) and places them into their groups.
        while ((name = reader.readLine()) != null ){
            if (position < progNum){
                groupA.push(new Person(name, reader.readLine().split("\t"), position, progNum));
                position++;
            } else {
                groupB.add(new Person(name, reader.readLine().split("\t"), position, progNum));
                position++;
            }
        }
        /*
         * This while loops takes the first programmer at the top of the stack and finds there top pick.
         * It uses the for loop to check if the top pick is already in the combination and if it is
         * it will check if they prefer the current or the one it is already paired with.
         */
        while (combination.size() < progNum * 2){
            current = (Person)groupA.top();
            groupA.pop();
            possiblePair = groupB.get(current.getPreferencePosition(nextPreference));

            if (combination.size() == 1){
                // Places first pair into combination ArrayList.
                combination.clear();
                combination.add(current);
                combination.add(possiblePair);
            } else {
                // Runs through the combination list checks if the possible pair already has a match
                for (int a = 0; a < combination.size(); a++){
                    if(possiblePair == combination.get(a)){
                        /*
                         * If there is a pair it checks possiblePair's preference for both the current and the
                         * one its already paired with, if current is a higher preference it replaces.
                         * If not it moves up the preference value. This code creates backtracking.
                         */
                        if(possiblePair.preferences[(current.getPosition())] <
                                possiblePair.preferences[(combination.get(a-1).getPosition())]) {
                            groupA.push(combination.get(a - 1));
                            combination.remove(a - 1);
                            combination.remove(a - 1);
                            combination.add(current);
                            combination.add(possiblePair);
                            nextPreference = 0;
                        } else {
                            // Pushes current back onto the stack and adds one to nextPreference.
                            groupA.push(current);
                            nextPreference++;
                            break;
                        }
                        nextPreference++;
                        break;
                    } else if (a == combination.size() - 1){
                        /*
                         * If the possiblePair does not have a match already it will pair
                         * current and possiblePair.
                         */
                        combination.add(current);
                        combination.add(possiblePair);
                        break;
                    }
                }
            }
        }
        // Prints out the combinations
        for(int b = 0, count = 1; b < combination.size() / 2; b++){
            System.out.println("Team " + b + ": " + combination.get(count - 1).getName() +
                    " and " + combination.get(count).getName());
            count = count + 2;
        }
    } catch (Exception e){
        e.printStackTrace();
    }
}
}
