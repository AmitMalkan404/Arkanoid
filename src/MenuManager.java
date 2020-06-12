import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * the class of the menu.
 * @param <T> the type of the class.
 */
public class MenuManager<T> implements Menu<T> {
    private AnimationRunner runner;
    private KeyboardSensor keyboardSensor;
    private String title;
    private List<T> menuValues;
    private List<String> menuNames;
    private List<String> menuKeys;
    private List<Boolean> isSubMenu;
    private List<Menu<T>> subMenus;
    private T status;
    private boolean stop;

    /**
     * the constructor.
     * @param title the title of the menu.
     * @param runner the animation runner type.
     * @param keyboardSensor the gui keyboard sensor.
     */
    public MenuManager(String title, AnimationRunner runner, KeyboardSensor keyboardSensor) {
        this.title = title;
        this.runner = runner;
        this.keyboardSensor = keyboardSensor;
        this.menuKeys = new ArrayList();
        this.menuNames = new ArrayList();
        this.menuValues = new ArrayList();
        this.isSubMenu = new ArrayList();
        this.subMenus = new ArrayList();
        this.reset();
    }

    /**
     * in charge of the process of the reset.
     */
    public void reset() {
        this.status = null;
        this.stop = false;
    }

    /**
     * the selection options that in the menu.
     *
     * @param key       the key.
     * @param message   the exhibit msg.
     * @param returnVal the value which we'll be returned.
     */
    @Override
    public void addSelection(String key, String message, T returnVal) {
        this.menuKeys.add(key);
        this.menuNames.add(message);
        this.menuValues.add(returnVal);
        this.isSubMenu.add(false);
        this.subMenus.add(null);
    }

    /**
     * the option that we chose.
     *
     * @return the option.
     */
    @Override
    public T getStatus() {
        return this.status;
    }

    /**
     * in charge of the logic.
     *
     * @param d the surface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.LIGHT_GRAY);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.BLACK);
        d.drawText(51, 50, this.title, 32);
        d.drawText(49, 50, this.title, 32);
        d.drawText(50, 51, this.title, 32);
        d.drawText(50, 49, this.title, 32);
        d.setColor(Color.RED);
        d.drawText(50, 50, this.title, 32);

        int i;
        for (i = 0; i < this.menuNames.size(); ++i) {
            int optionX = 100;
            int optionY = 120 + i * 40;
            String optionText = "(" + (String) this.menuKeys.get(i) + ") " + (String) this.menuNames.get(i);
            d.setColor(Color.BLACK);
            d.drawText(optionX + 1, optionY, optionText, 24);
            d.drawText(optionX - 1, optionY, optionText, 24);
            d.drawText(optionX, optionY + 1, optionText, 24);
            d.drawText(optionX, optionY - 1, optionText, 24);
            d.setColor(Color.GREEN);
            d.drawText(optionX, optionY, optionText, 24);
        }

        for (i = 0; i < this.menuValues.size(); ++i) {
            if (this.keyboardSensor.isPressed((String) this.menuKeys.get(i))) {
                if (!(Boolean) this.isSubMenu.get(i)) {
                    this.status = this.menuValues.get(i);
                    this.stop = true;
                } else {
                    Menu<T> subMenu = (Menu) this.subMenus.get(i);
                    this.runner.run(subMenu);
                    this.status = subMenu.getStatus();
                    this.stop = true;
                    subMenu.reset();
                }
                break;
            }
        }
    }

    /**
     * the sub-menu which we want to create.
     * @param key the button.
     * @param line the word which we want to exhibit.
     * @param subMenu the menu of the program.
     */
    public void addSubMenu(String key, String line, Menu<T> subMenu) {
        this.menuKeys.add(key);
        this.isSubMenu.add(true);
        this.subMenus.add(subMenu);
        this.menuNames.add(line);
        this.menuValues.add(null);
    }

    /**
     * in charge of stopping condition.
     *
     * @return bolean value if true or false.
     */
    @Override
    public boolean shouldStop() {
        return !(this.status == null);
    }

}
