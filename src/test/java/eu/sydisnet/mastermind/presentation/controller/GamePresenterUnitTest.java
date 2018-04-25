package eu.sydisnet.mastermind.presentation.controller;

import eu.sydisnet.mastermind.application.Game;
import eu.sydisnet.mastermind.application.internal.DefaultGame;
import eu.sydisnet.mastermind.domain.model.GuessCombination;
import eu.sydisnet.mastermind.domain.model.PinCombination;
import eu.sydisnet.mastermind.presentation.view.GameBoard;
import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Rule;
import org.junit.Test;

import java.lang.invoke.MethodHandles;
import java.util.logging.Logger;

/**
 * Unit Testing class that validates constraints on {@link GameBoardPresenter} controller.
 * @author sydisnet
 *         Project: mastermind
 *         Copyright (C) 2018 SYDISNET
 * @version 1.0.0.0-SNAPSHOT
 * @since 1.0.0.0-SNAPSHOT
 */
public class GamePresenterUnitTest
{
    private static final Logger LOG = Logger.getLogger(MethodHandles.lookup().getClass().getName());

    @Rule
    public final JUnitSoftAssertions softly = new JUnitSoftAssertions();

    @Test(expected = IllegalArgumentException.class)
    public void should_not_allow_null_constructor_model()
    {
        LOG.info("*************** should_not_allow_null_constructor_model() ***************");

        // When
        new GameBoardPresenter(null, new GameBoard());
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_not_allow_null_constructor_view()
    {
        LOG.info("*************** should_not_allow_null_constructor_model() ***************");

        // Given
        Game model = new DefaultGame();

        // When
        new GameBoardPresenter(model, null);
    }

    @Test
    public void should_allow_not_null_constructor_modelAndView()
    {
        LOG.info("*************** should_allow_not_null_constructor_modelAndView() ***************");

        // Given
        Game model = new DefaultGame()
        {
            @Override
            protected void setGuess(final GuessCombination guess)
            {
                // We ignore guess combination
                super.setGuess(new GuessCombination("ROOJ"));
            }
        };
        model.offer(new PinCombination("VJBN"));
        GameBoard view = new GameBoard();

        // When
        try
        {
            new GameBoardPresenter(model, view);
        }
        catch (Exception ex)
        {
            LOG.info("Message: " + ex.getMessage());

            this.softly.fail("Should be able to build controller when model and view are both non null !");
        }
    }
}
