package flashcards;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * <i>Singleton</i> class to assist with basic keyboard input operations.
 * Only a single <i>Input</i> object will ever be created.
 * The <i>Input</i> class clusters related input operations and will build one <i>Scanner</i>
 * object to use with input.
 * <i>Scanner</i> objects are big and complex;
 * thus, this approach reduces the overhead associated with the repeated creation of <i>Scanner</i> objects.
 *
 * @author Rex Woollard
 * @version Lab Assignment 1: <i>Lord of the Rings</i>
 */
final class Input {
    /** Keyword <i>static</i> makes this a <i>class</i> oriented variable,
     *  rather than <i>object</i> oriented variable;
     *  only one instance of an <i>Input</i> object will ever exist,
     *  and the instance is tracked by this reference
     variable. */
    private static Input referenceToSingleInputObject = null;
    /** Object-oriented instance variable,
     *  but since only one <i>Input</i> can ever be created,
     *  only one <i>Scanner</i> object will ever be created. */
    private Scanner scannerKeyboard;

    /**
     * Log all symbol write console
     */
    private LinkedList<String> log;
    private static boolean logIsOn;

    public LinkedList<String> getLog() {
        return log;
    }

    public void addLog(String sout) {
        if (logIsOn) {
            log.add(sout);
            System.out.println(sout);
        }
    }

    public void logOff() {
        logIsOn = false;
    }

    public void logOn() {
        logIsOn = true;
    }

    /** A <i>private</i> constructor guarantees that no <i>Input</i>
     * object can be created from outside the <i>Input</i> class;
     * this is essential for the <i>singleton</i> design pattern. */
    private Input() {
        scannerKeyboard = new Scanner(System.in);
        log = new LinkedList<>();
        logOn();
    }
    /** The <i>static</i> modifier means this method can be called without the existence of an <i>Input</i> object;
     * if no <i>Input</i> object exists one will be created; if one already exists, it will be re-used. */
    public static Input getInstance() {
        if (referenceToSingleInputObject == null)
            referenceToSingleInputObject = new Input();
        return referenceToSingleInputObject;
    } // end static Input getInstance()
    /**
     * Presents a prompt to the user and retrieves an <i>int</i> value.
     * @param sPrompt reference to a <i>String</i> object whose contents will be displayed to the user as a prompt.
     * @return <i>int</i> value input from keyboard
     */
    public int getInt(String sPrompt) {
        System.out.println(sPrompt);
        scannerKeyboard.useDelimiter("\r\n");
//        Setting this delimiter ensures that we capture everything up to the <Enter> key.
//        Without this, input stops at the next whitespace (space, tab, newline etc.).
        StringBuffer sInput;
        sInput = new StringBuffer(scannerKeyboard.nextLine());
        scannerKeyboard.reset(); // The preceding use of useDelimiter() changed the state of the Scanner object. reset() re-establishes the original state.
        if (logIsOn) {
            log.add(sPrompt);
            log.add(sInput.toString());
        }
        return Integer.parseInt(sInput.toString());
    } // end int getInt(String sPrompt)
    /**
     * Presents a prompt to the user and retrieves a <i>reference-to-String</i>.
     * @param sPrompt reference to a <i>String</i> object whose contents will be displayed to the user as a prompt.
     * @return <i>reference-to-String</i> object created by keyboard input
     */
    public String getString(String sPrompt) {
        System.out.println(sPrompt);
        scannerKeyboard.useDelimiter("\r\n"); // Setting this delimiter ensures that we capture everything up to the <Enter> key. Without this, input stops at the next whitespace (space, tab, newline etc.).
        StringBuffer sInput;
        sInput = new StringBuffer(scannerKeyboard.nextLine());
        scannerKeyboard.reset(); // The preceding use of useDelimiter() changed the state of the Scanner object. reset() re-establishes the original state.
        if (logIsOn) {
            log.add(sPrompt);
            log.add(sInput.toString());
        }
        return sInput.toString();
    } // end String getString(String sPrompt)

    public String getString() {
        return getString("");
    } // end String getString(String sPrompt)
} // end class Input()