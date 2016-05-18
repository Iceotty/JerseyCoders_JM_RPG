/**
 * Created by Joseph on 18/05/2016.
 */
public class Initiative implements Comparable<Initiative> {
    Character character;
    int number;
    public Initiative(Character character, int number){
        this.character = character;
        this.number = number;

    }

    @Override
    public int compareTo(Initiative o) {
        if (this.number>o.number){
            return 1;
        }
        if (this.number<o.number){
            return -1;
        }
        return 0;
    }
}
