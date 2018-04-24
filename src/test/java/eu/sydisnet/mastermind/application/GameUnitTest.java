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
 * @author shebert
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

        // Given default game
        Game game = new DefaultGame(5);

        // Expects x attempts
        this.softly.assertThat(game.getMaxAttempts()).isEqualTo(5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_not_allow_negative_constructor_maxAttempts()
    {
        LOG.info("*************** should_have_x_maxAttempts_constructor_x() ***************");

        // Given default ame
        new DefaultGame(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_not_allow_zero_constructor_maxAttempts()
    {
        LOG.info("*************** should_not_allow_zero_constructor_maxAttempts() ***************");

        // Given default ame
        new DefaultGame(0);
    }

    @Test
    public void should_be_NEW_GAME_at_the_beginning()
    {
        LOG.info("*************** should_be_NEW_GAME_at_the_beginning() ***************");

        // Given default ame
        Game game = new DefaultGame();

        // Expects 10 attempts
        this.softly.assertThat(game.getGameStatus()).isEqualTo(Game.Status.NEW_GAME);
    }

    @Test
    public void should_be_PLAYING_when_offering_with_fail_combination()
    {
        LOG.info("*************** should_be_PLAYING_when_offering_with_fail_combination() ***************");

        // Given game with hacked guess combination
        Game game = new DefaultGame(5)
        {
            @Override
            protected void setGuess(final GuessCombination guess)
            {
                new GuessCombination("ROOJ");
            }
        };

        // When the player offers
        game.offer(new PinCombination("NBJV"));


        // Expects 10 attempts
        this.softly.assertThat(game.getGameStatus()).isEqualTo(Game.Status.PLAYING);
    }

    @Test
    public void should_be_WON_when_offering_with_correct_combination()
    {
        LOG.info("*************** should_be_PLAYING_when_offering_with_fail_combination() ***************");

        // Given game with hacked guess combination
        Game game = new DefaultGame(5)
        {
            @Override
            protected void setGuess(final GuessCombination guess)
            {
                new GuessCombination("ROOJ");
            }
        };

        // When the player offers
        game.offer(new PinCombination("ROOJ"));


        // Expects 10 attempts
        this.softly.assertThat(game.getGameStatus()).isEqualTo(Game.Status.WON);
    }

    @Test(expected = IllegalStateException.class)
    public void should_not_allow_more_than_x_attempts_offer()
    {
        LOG.info("*************** should_not_allow_more_than_x_attempts_offer() ***************");

        // Given game with hacked guess combination
        Game game = new DefaultGame(5)
            {
                @Override
                protected void setGuess(final GuessCombination guess)
                {
                    new GuessCombination("ROOJ");
                }
            };

        // When the player offers 5 times
        try
        {
            for (int i = 0; i < 5; i++)
            {
                game.offer(new PinCombination("VBNN"));
            }
        }
        catch (Exception ex)
        {
            LOG.info("Message: " + ex.getMessage());

            // Does Not Expect
            this.softly.fail("Should accept 10 attempts !", ex);
        }

        // And then offer one more time
        // Expected IllegalStateException
        try
        {
            game.offer(new PinCombination("VBNN"));
        }
        catch (Exception ex)
        {
            LOG.info("Message: " + ex.getMessage());

            throw ex;
        }
    }
}
