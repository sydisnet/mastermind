package eu.sydisnet.mastermind.presentation;

import java.lang.invoke.MethodHandles;
import java.util.logging.Logger;

/**
 * Launches the application in Console Mode.
 *
 * @author sydisnet
 *         Project: mastermind
 *         Copyright (C) 2018 SYDISNET
 * @version 1.0.0.0-SNAPSHOT
 * @since 1.0.0.0-SNAPSHOT
 */
public final class ConsoleLauncher
{
    private ConsoleLauncher()
    { }

    /**
     * Main-Class in executable JAR.
     *
     * @param args an array of params
     */
    public static void main(final String... args)
    {
        Logger log = Logger.getLogger(MethodHandles.lookup().getClass().getName());

        log.info("Lancement de Mastermind en mode Console");
    }
}
