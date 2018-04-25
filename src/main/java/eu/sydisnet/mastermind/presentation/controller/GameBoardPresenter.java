package eu.sydisnet.mastermind.presentation.controller;

import eu.sydisnet.mastermind.application.Game;
import eu.sydisnet.mastermind.domain.exception.UserInputException;
import eu.sydisnet.mastermind.domain.model.PinCombination;
import eu.sydisnet.mastermind.presentation.view.GameBoard;

import java.util.Deque;
import java.util.stream.Collectors;

/**
 * Controller for {@link GameBoard}.
 *
 * @author sydisnet
 *         Project: mastermind
 *         Copyright (C) 2018 SYDISNET
 * @version 1.0.0.0-SNAPSHOT
 * @since 1.0.0.0-SNAPSHOT
 */
public class GameBoardPresenter
{
    /**
     * The model boundary interface.
     */
    private Game modelBoundary;

    /**
     * The game board view.
     */
    private GameBoard view;


    /**
     * Builds the controller, i.e. the 'Presenter' element from Model-View-Presenter pattern.
     */
    public GameBoardPresenter(final Game modelBoundary, final GameBoard view)
    {
        this.setModel(modelBoundary);
        this.setView(view);

        // Displays Welcome Message
        this.view.display(
            "Ordinateur > J’ai choisi ma combinaison, à vous de deviner !",
            "Les couleurs possibles sont R, J, B, O, V et N.",
            "Tapez (RJBO) pour tenter les couleurs R,J,B et O dans l’ordre.",
            "Voici la grille actuelle :"
        );

        // Displays Board content
        this.showGridContent();
    }

    private void setModel(final Game model)
    {
        if (model == null)
        {
            throw new IllegalArgumentException("Model must be provided !");
        }
        this.modelBoundary = model;
    }

    private void setView(final GameBoard view)
    {
        if (view == null)
        {
            throw new IllegalArgumentException("View must be provided !");
        }
        this.view = view;
    }

    /**
     * Builds a {@link String} and gives it to the view for displaying.
     */
    private void showGridContent()
    {
        StringBuilder sb = new StringBuilder();
        // Head
        sb.append("|-------------------|");
        // Content
        Deque<Game.Proposal> proposals = this.modelBoundary.getProposals();
        int currentIndex = 0;
        for (Game.Proposal currentProposal : proposals)
        {
            sb.append(System.lineSeparator()).append(
                String.format(
                    "|%s| %d | %d |%2d/%2d |",
                    currentProposal.getCombination().getPins()
                        .stream()
                            .map(c -> String.format("%c", c.getFrenchColorShortcut()).toUpperCase())
                            .collect(Collectors.joining()),
                    currentProposal.getFairPinCount(),
                    currentProposal.getMisplacedPinCount(),
                    ++currentIndex,
                    this.modelBoundary.getMaxAttempts()
                )
            );
        }
        // Tail
        sb.append(System.lineSeparator()).append(
            String.format(
                "|....| . | . |%2d/%2d |%n",
                this.modelBoundary.getNumberOfAttempts() + 1,
                this.modelBoundary.getMaxAttempts()
            )
        ).append("|-------------------|");

        // Tells the view to display content
        this.view.display(sb.toString());
    }

    /**
     * Control logic.
     */
    public void listenToUserInput()
    {
        do
        {
            // Waits for user
            String userInput = this.view.waitForUserInput();

            // Offers pin combination
            try
            {
                // Invokes Application Logic
                PinCombination offered = new PinCombination(userInput);
                this.modelBoundary.offer(offered);

                // Updates View
                this.view.display("Ordinateur > ");
                this.showGridContent();
            }
            catch (UserInputException ex)
            {
                // Updates View
                this.view.displayError(ex.getErrorCause().toString());
            }
        }
        while (Game.Status.NEW_GAME == this.modelBoundary.getGameStatus() || Game.Status.PLAYING == this.modelBoundary.getGameStatus());

        // Processes result
        this.endGame();
    }

    /**
     * Checks if the game has reached its end, then tells the view to display success or failure.
     */
    private void endGame()
    {
        if (Game.Status.WON == this.modelBoundary.getGameStatus())
        {
            this.view.display(String.format("Bravo, vous avez gagné en %d tours !", this.modelBoundary.getNumberOfAttempts()));
        }
        else if (Game.Status.LOST == this.modelBoundary.getGameStatus())
        {
            this.view.display("Désolé, vous avez perdu ! Merci d'avoir joué...");
        }
        else
        {
            this.view.displayError("Erreur programme !");
            // System.exit(-2) warns FindBugs because it is a well known bad practice.
            Runtime.getRuntime().exit(-2);
        }
    }
}
