public class Person {
    protected String name;
    protected int[] preferences;
    protected int position;

/**
 * This class creates a person that holds it's name and its preferences.
 * @param name name of programmer
 * @param preferences preferences of that programmer
 */
public Person(String name, String[] preferences, int position, int numofProg){
        this.preferences = new int[numofProg];
        this.name = name;
        if(position > numofProg - 1)
            this.position = position - numofProg;
        else
            this.position = position;
        for (int i = 0; i < preferences.length; i++){
            this.preferences[i] = (Integer.valueOf(preferences[i]));
        }
    }
    public Person(){
    this.preferences = null;
    this.name = null;
    this.position = 0;
    }
/**
 * This program takes an int for the preference level and returns the position of the person in the opposite
 * group that matches that preference level
 * @param prefLevel Preference level entered
 * @return returns preference position.
 */
    public Integer getPreferencePosition(int prefLevel) throws Exception{
    for(int i = 0; i < this.preferences.length; i++){
        if (this.preferences[i] == prefLevel){
            return i;
        }
    }
    throw new Exception("Invalid Preference Level");
}

public String getName() {
    return name;
}

public int getPosition() {
    return position;
}
}

