package eu.sydisnet.mastermind.application;

import eu.sydisnet.mastermind.application.internal.DefaultGame;
import eu.sydisnet.mastermind.domain.model.GuessCombination;
import eu.sydisnet.mastermind.domain.model.PinCombination;
import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Rule;
import org.junit.Test;

import java.lang.invoke.MethodHandles;
import java.util.logging.Logger;

/**
 * Unit Testing class that validates constraints on {@link Game.Proposal} concept.
 *
 * @author sydisnet
 *         Project: mastermind
 *         Copyright (C) 2018 SYDISNET
 * @version 1.0.0.0-SNAPSHOT
 * @since 1.0.0.0-SNAPSHOT
 */
public class GameUnitTest
{
    private static final Logger LOG = Logger.getLogger(MethodHandles.lookup().getClass().getName());

    @Rule
    public final JUnitSoftAssertions softly = new JUnitSoftAssertions();

    @Test
    public void should_allow_10_maxAttempts_constructor_default()
    {
        LOG.info("*************** should_allow_10_maxAttempts_constructor_default() ***************");

        // Given default game
        Game game = new DefaultGame();

        // Expects 10 attempts
        this.softly.assertThat(game.getMaxAttempts()).isEqualTo(10);
    }

    @Test
    public void should_allow_x_maxAttempts_constructor_x()
    {
        LOG.info("*************** should_allow_x_maxAttempts_constructor_x() ***************");

        // Given custom game with 5 retries
        Game game = new DefaultGame(5);

        // Expects x attempts
        this.softly.assertThat(game.getMaxAttempts()).isEqualTo(5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_not_allow_negative_constructor_maxAttempts()
    {
        LOG.info("*************** should_have_x_maxAttempts_constructor_x() ***************");

        // Given custom game with negative retries
        new DefaultGame(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_not_allow_zero_constructor_maxAttempts()
    {
        LOG.info("*************** should_not_allow_zero_constructor_maxAttempts() ***************");

        // Given custom game with zero retry
        new DefaultGame(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_not_allow_null_offer_combination()
    {
        LOG.info("*************** should_not_allow_null_offer_combination() ***************");

        // Given default game
        Game game = new DefaultGame();

        // When
        game.offer(null);
    }

    @Test
    public void should_be_NEW_GAME_at_the_beginning()
    {
        LOG.info("*************** should_be_NEW_GAME_at_the_beginning() ***************");

        // Given default game
        Game game = new DefaultGame();

        // Expects
        this.softly.assertThat(game.getGameStatus()).isEqualTo(Game.Status.NEW_GAME);
    }

    @Test
    public void should_be_PLAYING_when_offering_with_fail_combination()
    {
        LOG.info("*************** should_be_PLAYING_when_offering_with_fail_combination() ***************");

        // Given game with hacked guess combination
        Game game = new DefaultGame()
        {
            @Override
            protected void setGuess(final GuessCombination guess)
            {
                // We ignore auto-generated combination
                super.setGuess(new GuessCombination("ROOJ"));
            }
        };

        // When the player offers
        game.offer(new PinCombination("NBJV"));


        // Expects
        this.softly.assertThat(game.getGameStatus()).isEqualTo(Game.Status.PLAYING);
    }

    @Test
    public void should_be_WON_when_offering_with_correct_combination()
    {
        LOG.info("*************** should_be_PLAYING_when_offering_with_fail_combination() ***************");

        // Given game with hacked guess combination
        Game game = new DefaultGame()
        {
            @Override
            protected void setGuess(final GuessCombination guess)
            {
                // We ignore auto-generated combination
                super.setGuess(new GuessCombination("ROOJ"));
            }
        };

        // When the player offers
        game.offer(new PinCombination("ROOJ"));


        // Expects
        this.softly.assertThat(game.getGameStatus()).isEqualTo(Game.Status.WON);
    }

    @Test
    public void should_be_LOST_when_offering_with_last_incorrect_combination()
    {
        LOG.info("*************** should_be_PLAYING_when_offering_with_fail_combination() ***************");

        // Given game with hacked guess combination and two retries
        Game game = new DefaultGame(2)
        {
            @Override
            protected void setGuess(final GuessCombination guess)
            {
                // We ignore auto-generated combination
                super.setGuess(new GuessCombination("ROOJ"));
            }
        };

        // When the player offers
        game.offer(new PinCombination("NBJV"));
        // Then offers
        game.offer(new PinCombination("JJOR"));


        // Expects
        this.softly.assertThat(game.getGameStatus()).isEqualTo(Game.Status.LOST);
    }

    @Test(expected = IllegalStateException.class)
    public void should_not_allow_offer_when_game_is_LOST_yet()
    {
        LOG.info("*************** should_not_allow_offer_when_game_is_LOST_yet() ***************");

        // Given game with hacked guess combination and two retries
        Game game = new DefaultGame(1)
        {
            @Override
            protected void setGuess(final GuessCombination guess)
            {
                // We ignore auto-generated combination
                super.setGuess(new GuessCombination("ROOJ"));
            }
        };

        // When the player offers
        game.offer(new PinCombination("NBJV"));
        // Then offers
        game.offer(new PinCombination("JJOR"));
    }
}
