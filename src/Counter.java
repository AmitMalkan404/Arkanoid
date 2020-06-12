/**
 * this class counting the remaining number of blocks.
 */
public class Counter {
    private int counter;

    /**
     * the constructor.
     * @param number the number we want to set.
     */
    public Counter(int number) {
        this.counter = number;
    }
    /**
     * add number to current count.
     * @param number the number that we want to increase.
     */
    public void increase(int number) {
        this.counter = this.counter + number;
    }
    /**
     * subtract number from current count.
     * @param number the number that we want to decrease.
     */
    public void decrease(int number) {
        this.counter = this.counter - number;
    }

    /**
     * get current count.
     * @return the getter of the value.
     */
    public int getValue() {
        return this.counter;
    }

}
