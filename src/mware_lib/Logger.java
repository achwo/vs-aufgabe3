package mware_lib;

public class Logger {

    private Object who;
    private boolean activated = true;

    public Logger(Object who) {
        this(who, true);
    }

    public Logger(Object who, boolean activated) {
        this.who = who;
        this.activated = activated;
    }

    public void log(String message) {
        if(activated) System.out.println(who + ": " + message);
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }
}
