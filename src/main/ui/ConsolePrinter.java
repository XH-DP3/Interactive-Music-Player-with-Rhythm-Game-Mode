package ui;

import model.Event;
import model.EventLog;

// Represent a class that prints event log to the console after the user quits the program
public class ConsolePrinter {

    public void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next + "\n");
        }
    }
}
