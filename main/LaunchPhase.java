package main;
public class LaunchPhase {
    public enum State { NOT_STARTED, ACTIVE, COMPLETED, CANCELLED }

    private final int number;
    private State state;

    /**
     * Constructs a new LaunchPhase with the given number.
     * The initial state is NOT_STARTED.
     * @param number the number of this phase
     */
    public LaunchPhase(int number) {
        this.number = number;
        this.state = State.NOT_STARTED;
    }

    /**
     * Starts this phase. The state becomes ACTIVE.
     */
    public void start() {
        this.state = State.ACTIVE;
        // Add code here to perform the actions of this phase.
    }

    /**
     * Waits for this phase to complete.
     * This is a placeholder method and does not actually pause execution.
     */
    public void join() {
        // Add code here to wait for this phase to complete.
    }

    /**
     * Cancels this phase. The state becomes CANCELLED.
     */
    public void cancel() {
        this.state = State.CANCELLED;
    }

    /**
     * Checks if this phase has been started.
     * @return true if the state is not NOT_STARTED, false otherwise
     */
    public boolean isStarted() {
        return this.state != State.NOT_STARTED;
    }

    /**
     * Checks if this phase has been completed.
     * @return true if the state is COMPLETED, false otherwise
     */
    public boolean isCompleted() {
        return this.state == State.COMPLETED;
    }

    /**
     * Gets the current state of this phase.
     * @return the current state
     */
    public State getState() {
        return this.state;
    }
}