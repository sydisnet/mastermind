package eu.sydisnet.mastermind.application;

import eu.sydisnet.mastermind.domain.model.PinCombination;

import java.util.Deque;

/**
 * Defines the game concept.
 *
 * @author sydisnet
 *         Project: mastermind
 *         Copyright (C) 2018 SYDISNET
 * @version 1.0.0.0-SNAPSHOT
 * @since 1.0.0.0-SNAPSHOT
 */
public interface Game
{
    /**
     * A game has four states : NEW_GAME -> PLAYING -> LOST|WON.
     */
    enum Status
    {
        /**
         * Supported Status game.
         */
        NEW_GAME, PLAYING, LOST, WON
    }

    /**
     * Returns the status of the game.
     */
    Status getGameStatus();

    /**
     * Player submits a combination that will be tested with guess combination.
     *
     * @param combination the combination submitted by the player
     */
    void offer(PinCombination combination);

    /**
     * Returns the exactly fair pin count for last proposal located at the end of the queue.
     */
    int getLastFairPinCount();

    /**
     * Returns the fair but misplaced pin count for last proposal located at the end of the queue.
     */
    int getLastMisplacedPinCount();

    /**
     * Returns the number of attempts.
     */
    int getNumberOfAttempts();

    /**
     * Returns the maximum number of attempts.
     */
    int getMaxAttempts();

    /**
     * Returns the current proposals or an empty "stack" if no proposal has been submitted.
     */
    Deque<Proposal> getProposals();

    /**
     * Defines the value-object 'Proposal' that is a degenerated anemic structure, i.e. without business logic.
     *
     * @author sydisnet
     *         Project: mastermind
     *         Copyright (C) 2018 SYDISNET
     * @version 1.0.0.0-SNAPSHOT
     * @since 1.0.0.0-SNAPSHOT
     */
    class Proposal
    {
        /**
         * The associated pin combination provided by the user.
         */
        private PinCombination combination;

        /**
         * Contains the number of exactly fair (correct color and successfully located) pins.
         */
        private int fairPinCount;

        /**
         * Contains the number of fair (correct color but misplaced) pins.
         */
        private int misplacedPinCount;

        /**
         * Builds an instance.
         */
        public Proposal(final PinCombination combination, final int fairPinCount, final int misplacedPinCount)
        {
            this.setCombination(combination);
            this.setFairPinCount(fairPinCount);
            this.setMisplacedPinCount(misplacedPinCount);
            if (fairPinCount +  misplacedPinCount > 4)
            {
                throw new IllegalArgumentException("Sum of Fair Pins and Misplaced Pins cannot exceed 4 !");
            }
        }

        public PinCombination getCombination()
        {
            return this.combination;
        }

        private void setCombination(final PinCombination combination)
        {
            if (combination == null)
            {
                throw new IllegalArgumentException("Combination must be provided !");
            }
            this.combination = combination;
        }

        public int getFairPinCount()
        {
            return this.fairPinCount;
        }

        private void setFairPinCount(final int fairPinCount)
        {
            if (fairPinCount < 0 || fairPinCount > 4)
            {
                throw new IllegalArgumentException("Fair Pin Count must have a value between 0 and 4, included terminals !");
            }
            this.fairPinCount = fairPinCount;
        }

        public int getMisplacedPinCount()
        {
            return this.misplacedPinCount;
        }

        private void setMisplacedPinCount(final int misplacedPinCount)
        {
            if (misplacedPinCount < 0 || misplacedPinCount > 4)
            {
                throw new IllegalArgumentException("Misplaced Pin Count must have a value between 0 and 4, included terminals !");
            }
            this.misplacedPinCount = misplacedPinCount;
        }
    }
}
