package eu.sydisnet.mastermind.presentation.view;

import java.io.PrintStream;

/**
 * The game board is the main view.
 *
 * @author sydisnet
 *         Project: mastermind
 *         Copyright (C) 2018 SYDISNET
 * @version 1.0.0.0-SNAPSHOT
 * @since 1.0.0.0-SNAPSHOT
 */
public class GameBoard
{
    /**
     * The stdout stream.
     */
    private PrintStream out;

    /**
     * The stderr stream.
     */
    private PrintStream err;


    /**
     * Builds a view instance.
     */
    public GameBoard()
    {
        // Sets output stream
        this.out = System.out;

        // Sets error stream
        this.err = System.err;
    }

    /**
     * Displays messages on stdout.
     */
    public void display(final String... messages)
    {
        out.println(String.join(System.lineSeparator(), messages));
    }

    /**
     * Displays messages on stderr.
     */
    public void displayError(final String... messages)
    {
        err.println(String.join(System.lineSeparator(), messages));
    }

    /**
     * Waits for user.
     */
    public String waitForUserInput()
    {
        out.print("Vous > ");
        return System.console().readLine();
    }
}
