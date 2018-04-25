package eu.sydisnet.mastermind.application.internal;

import eu.sydisnet.mastermind.application.Game;
import eu.sydisnet.mastermind.domain.model.GuessCombination;
import eu.sydisnet.mastermind.domain.model.PinCombination;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Defines a default {@link DefaultGame} implementation, i.e. an auto-generated guess combination and a stack (represented by a {@link java.util.Deque}
 * because {@link java.util.Stack} is deprecated.
 *
 * <p>
 *     The user interacts with an instance of this class, he can submit a {@link PinCombination} instance
 *     and the game instance checks that :
 *     <ul>
 *         <li>The combination is correct, i.e. the combination is equal to the guess combination: yes, we broke equality contract
 *             because we accept to test equality between a {@link GuessCombination} and a {@link PinCombination}
 *             assuming that a {@link GuessCombination} is a specialization of {@link PinCombination} just as Hibernate does,
*          </li>
 *         <li>
 *             If provided combination is not correct, the game instance checks if subsequent riddles are possible (10 attempts are possible).
 *         </li>
 *         <li>
 *             If provided combination is correct, the user has won.
 *         </li>
 *         <li>
 *             If no provided combination was found after 10 attempts, the user has lost.
 *         </li>
 *     </ul>
 * </p>
 * <p>
 *     Each combination and its comparison results are stored in a stack (in fact, a {@link Deque} of {@link Game.Proposal} instances.
 * </p>
 *
 * @author sydisnet
 *         Project: mastermind
 *         Copyright (C) 2018 SYDISNET
 * @version 1.0.0.0-SNAPSHOT
 * @since 1.0.0.0-SNAPSHOT
 */
public class DefaultGame implements Game
{
    /**
     * The combination to guess.
     */
    private GuessCombination guess;

    /**
     * Maximum of possible proposals.
     */
    private int maxAttempts;

    /**
     * The status game.
     */
    private Status gameStatus;

    /**
     * Proposals offered by player.
     */
    private Deque<Proposal> proposals;

    /**
     * Starts a game with a default maximum of 10 retries.
     */
    public DefaultGame()
    {
        this(10);
    }

    /**
     * Starts a game with {@code maxAttempts}.
     *
     * @param maxAttempts the maximum number of possible proposals that may be offered by player.
     */
    public DefaultGame(final int maxAttempts)
    {
        // Generates the riddle
        this.setGuess(new GuessCombination());

        // Stores as a private member the maximum retries
        this.setMaxAttempts(maxAttempts);

        // Game is now ready
        this.setGameStatus(Status.NEW_GAME);

        // Inits the proposals
        this.proposals = new ArrayDeque<>(maxAttempts);
    }

    /**
     * Sets the combination to guess.
     *
     * <p>
     *     For testing purpose, this method is protected.
     * </p>
     */
    protected void setGuess(final GuessCombination guess)
    {
        this.guess = guess;
    }

    /**
     * Returns the status of the game.
     */
    @Override
    public Status getGameStatus()
    {
        return this.gameStatus;
    }

    /**
     * Sets the status of the game.
     *
     * <p>
     *     Private setter means the {@link this#gameStatus} property is a read-only property.
     * </p>
     *
     * @param gameStatus the status of the game
     */
    private void setGameStatus(final Status gameStatus)
    {
        this.gameStatus = gameStatus;
    }

    /**
     * Player submits a combination that will be tested with guess combination.
     *
     * @param combination the combination submitted by the player
     */
    @Override
    public void offer(final PinCombination combination)
    {
        // Checks user input
        if (combination == null)
        {
            throw new IllegalArgumentException("Combination must be provided !");
        }

        // Check if we are allowed to play anymore
        if (this.getGameStatus() == Status.LOST)
        {
            throw new IllegalStateException("Game is over ! You cannot play anymore !");
        }

        // Is it first offer call ?
        if (this.proposals.size() == 0)
        {
            this.setGameStatus(Status.PLAYING);
        }

        // Testing
        boolean testingResult = this.guess.equals(combination);
        Proposal proposal = new Proposal(combination, this.guess.getFairPinCount(), this.guess.getMisplacedPinCount());
        if (this.proposals.offer(proposal))
        {
            // We can now check if the user has won
            if (testingResult)
            {
                this.setGameStatus(Status.WON);
            }
            else if (this.proposals.size() == this.maxAttempts)
            {
                this.setGameStatus(Status.LOST);
            }
        }
    }

    /**
     * Returns the exactly fair pin count for last proposal located at the end of the queue.
     */
    @Override
    public int getLastFairPinCount()
    {
        return 0;
    }

    /**
     * Returns the fair but misplaced pin count for last proposal located at the end of the queue.
     */
    @Override
    public int getLastMisplacedPinCount()
    {
        return 0;
    }

    /**
     * Returns the number of attempts.
     */
    @Override
    public int getNumberOfAttempts()
    {
        return 0;
    }

    /**
     * Returns the maximum number of attempts.
     */
    @Override
    public int getMaxAttempts()
    {
        return this.maxAttempts;
    }

    /**
     * Sets the maximum allowed attempts.
     *
     * <p>
     *     Private setter means the {@link this#maxAttempts} property is a read-only property.
     * </p>
     *
     * @param maxAttempts the maximum number of attempts that a player can offer {@link PinCombination}
     */
    private void setMaxAttempts(final int maxAttempts)
    {
        if (maxAttempts <= 0)
        {
            throw new IllegalArgumentException("Max Attempts must be a positive value !");
        }
        this.maxAttempts = maxAttempts;
    }
}
