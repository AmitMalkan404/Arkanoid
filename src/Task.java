/**
 * A task is something that needs to happen, or something that we can run() and return a value.
 * @param <T> the type of the task.
 */
public interface Task<T> {
    /**
     * the running of the Task.
     * @return a value.
     */
    T run();
}
