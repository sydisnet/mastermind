package eu.sydisnet.mastermind.presentation;

import eu.sydisnet.mastermind.application.Game;
import eu.sydisnet.mastermind.application.internal.DefaultGame;
import eu.sydisnet.mastermind.domain.model.GuessCombination;
import eu.sydisnet.mastermind.presentation.controller.GameBoardPresenter;
import eu.sydisnet.mastermind.presentation.view.GameBoard;

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

        // Model
//        Game model = new DefaultGame();
        Game model = new DefaultGame()
        {
            @Override
            protected void setGuess(final GuessCombination guess)
            {
                // We ignore guess combination
                super.setGuess(new GuessCombination("ROOJ"));
            }
        };

        // View
        GameBoard view = new GameBoard();

        // Controller
        GameBoardPresenter controller = new GameBoardPresenter(model, view);

        // Starts main process
        controller.listenToUserInput();
    }
}
