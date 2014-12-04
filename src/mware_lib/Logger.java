package mware_lib;

public class Logger {

    private Object who;

    public Logger(Object who) {
        this.who = who;
    }

    public void log(String message) {
        System.out.println(who + ": " + message);
    }
}
