import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * decorator-class that will wrap an existing animation and add a "waiting-for-key" behavior to it.
 */
public class KeyPressStoppableAnimation implements Animation {
    private Animation decorated;
    private KeyboardSensor keyboardSensor;
    private String button;
    private boolean logic;
    private boolean isAlreadyPressed;
    private boolean advanceToNext;

    /**
     * the constructor.
     * @param decorated the animation.
     * @param ks the keyboard sensor.
     * @param string the wanted key.
     */
    public KeyPressStoppableAnimation(Animation decorated, KeyboardSensor ks, String string) {
        this.decorated = decorated;
        this.keyboardSensor = ks;
        this.button = string;
        this.logic = false;
        this.isAlreadyPressed = true;
        this.advanceToNext = false;
    }

    /**
     * in charge of the logic.
     *
     * @param d the surface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.isAlreadyPressed) {
            this.advanceToNext = this.keyboardSensor.isPressed(this.button);
            this.isAlreadyPressed = false;
        }
        this.decorated.doOneFrame(d);
        if (this.keyboardSensor.isPressed(this.button)) {
            if (!this.advanceToNext) {
                this.logic = true;
            }
        } else {
            this.advanceToNext = false;
        }
    }

    /**
     * in charge of stopping condition.
     *
     * @return bolean value if true or false.
     */
    @Override
    public boolean shouldStop() {
        return this.logic;
    }

}
