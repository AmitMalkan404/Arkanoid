/**
 * the interface of the menu.
 * @param <T> the type of the key.
 */
public interface Menu<T> extends Animation {
    /**
     * the selection options that in the menu.
     * @param key the key.
     * @param message the exhibit msg.
     * @param returnVal the value which we'll be returned.
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * the option that we chose.
     * @return the option.
     */
    T getStatus();

    /**
     * reset the menu.
     */
    void reset();

    /**
     * another menu, in which he will be asked to select a level-set .
     * @param key the key.
     * @param message set name.
     * @param subMenu the menu.
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);
}