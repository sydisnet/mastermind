package eu.sydisnet.mastermind.presentation.view;

import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Rule;
import org.junit.Test;

import java.lang.invoke.MethodHandles;
import java.util.logging.Logger;

/**
 * Unit Testing class that validates constraints on {@link GameBoard} view.
 *
 * @author sydisnet
 *         Project: mastermind
 *         Copyright (C) 2018 SYDISNET
 * @version 1.0.0.0-SNAPSHOT
 * @since 1.0.0.0-SNAPSHOT
 */
public class GameBoardUnitTest
{
    private static final Logger LOG = Logger.getLogger(MethodHandles.lookup().getClass().getName());

    @Rule
    public final JUnitSoftAssertions softly = new JUnitSoftAssertions();

    @Test(expected = NullPointerException.class)
    public void should_not_allow_nullArray_display_messages()
    {
        LOG.info("*************** should_not_allow_nullArray_display_messages() ***************");

        // Given
        GameBoard view = new GameBoard();

        // When
        view.display((String[]) null);
    }

    @Test
    public void should_allow_nullEntry_display_messages()
    {
        LOG.info("*************** should_not_allow_nullEntry_display_messages() ***************");

        // Given
        GameBoard view = new GameBoard();

        // When
        try
        {
            view.display("Message 1", null);
        }
        catch (Exception ex)
        {
            LOG.info("Message: " + ex.getMessage());

            this.softly.fail("Should display 'null' as 'null' string");
        }
    }

    @Test(expected = NullPointerException.class)
    public void should_not_allow_nullArray_displayError_messages()
    {
        LOG.info("*************** should_not_allow_nullArray_displayError_messages() ***************");

        // Given
        GameBoard view = new GameBoard();

        // When
        view.displayError((String[]) null);
    }

    @Test
    public void should_allow_nullEntry_displayError_messages()
    {
        LOG.info("*************** should_allow_nullEntry_displayError_messages() ***************");

        // Given
        GameBoard view = new GameBoard();

        // When
        try
        {
            view.displayError("Message 1", null);
        }
        catch (Exception ex)
        {
            LOG.info("Message: " + ex.getMessage());

            this.softly.fail("Should display 'null' as 'null' string !");
        }
    }
}
