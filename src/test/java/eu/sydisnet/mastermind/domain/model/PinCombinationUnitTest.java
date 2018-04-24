package eu.sydisnet.mastermind.domain.model;

import eu.sydisnet.mastermind.domain.exception.UserInputException;
import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Rule;
import org.junit.Test;

import java.lang.invoke.MethodHandles;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Unit Testing class that validates constraints on {@link PinCombination} concept.
 *
 * @author sydisnet
 *         Project: mastermind
 *         Copyright (C) 2018 SYDISNET
 * @version 1.0.0.0-SNAPSHOT
 * @since 1.0.0.0-SNAPSHOT
 */
public class PinCombinationUnitTest
{
    private static final Logger LOG = Logger.getLogger(MethodHandles.lookup().getClass().getName());

    @Rule
    public final JUnitSoftAssertions softly = new JUnitSoftAssertions();

    @Test(expected = UserInputException.class)
    public void should_not_allow_null_constructor_userInput()
    {
        LOG.info("*************** should_not_allow_null_constructor_userInput() ***************");

        try
        {
            // When
            new PinCombination(null);
        }
        catch (Exception ex)
        {
            LOG.info("Message: " + ex.getMessage());

            if (ex instanceof UserInputException)
            {
                Optional.ofNullable(UserInputException.class.cast(ex).getErrorCause())
                    .ifPresent(errorCause ->
                        {
                            LOG.info("ErrorCause in french: " + errorCause.toString());

                            // Expects length problem
                            this.softly
                                .assertThat(errorCause)
                                .isSameAs(UserInputException.ErrorCause.INCORRECT_LENGTH);
                        }
                    );
            }

            // Expects
            throw ex;
        }
    }

    @Test(expected = UserInputException.class)
    public void should_not_allow_four_spaces_constructor_userInput()
    {
        LOG.info("*************** should_not_allow_four_spaces_constructor_userInput() ***************");

        try
        {
            // When
            new PinCombination("    ");
        }
        catch (Exception ex)
        {
            LOG.info("Message: " + ex.getMessage());

            if (ex instanceof UserInputException)
            {
                Optional.ofNullable(UserInputException.class.cast(ex).getErrorCause())
                    .ifPresent(errorCause ->
                        // Expects length problem
                        this.softly
                            .assertThat(errorCause)
                            .isSameAs(UserInputException.ErrorCause.INCORRECT_LENGTH)
                    );
            }

            // Expects
            throw ex;
        }
    }

    @Test(expected = UserInputException.class)
    public void should_not_allow_length_lesser_than_4_constructor_userInput()
    {
        LOG.info("*************** should_not_allow_length_lesser_than_4_constructor_userInput() ***************");

        try
        {
            // When
            new PinCombination("ROJ ");
        }
        catch (Exception ex)
        {
            LOG.info("Message: " + ex.getMessage());

            if (ex instanceof UserInputException)
            {
                Optional.ofNullable(UserInputException.class.cast(ex).getErrorCause())
                    .ifPresent(errorCause ->
                        // Expects length problem
                        this.softly
                            .assertThat(errorCause)
                            .isSameAs(UserInputException.ErrorCause.INCORRECT_LENGTH)
                    );
            }

            // Expects
            throw ex;
        }
    }

    @Test(expected = UserInputException.class)
    public void should_not_allow_length_greater_than_4_constructor_userInput()
    {
        LOG.info("*************** should_not_allow_length_greater_than_4_constructor_userInput() ***************");

        try
        {
            // When
            new PinCombination("ROJBV");
        }
        catch (Exception ex)
        {
            LOG.info("Message: " + ex.getMessage());

            if (ex instanceof UserInputException)
            {
                Optional.ofNullable(UserInputException.class.cast(ex).getErrorCause())
                    .ifPresent(errorCause ->
                        // Expects length problem
                        this.softly
                            .assertThat(errorCause)
                            .isSameAs(UserInputException.ErrorCause.INCORRECT_LENGTH)
                    );
            }

            // Expects
            throw ex;
        }
    }

    @Test
    public void should_allow_length_exactly_4_constructor_userInput()
    {
        LOG.info("*************** should_allow_length_exactly_4_constructor_userInput() ***************");

        try
        {
            // When
            new PinCombination("ROJB");
        }
        catch (Exception ex)
        {
            LOG.info("Message: " + ex.getMessage());

            // Does Not Expect
            this.softly.fail("Should accept a combination with four managed letters !", ex);
        }
    }

    @Test(expected = UserInputException.class)
    public void should_not_allow_length_exactly_4_containing_unsupported_color_constructor_userInput()
    {
        LOG.info("*************** should_not_allow_length_exactly_4_containing_unsupported_color_constructor_userInput() ***************");

        try
        {
            // When
            new PinCombination("RVNC"); // C is not supported
        }
        catch (Exception ex)
        {
            LOG.info("Message: " + ex.getMessage());

            if (ex instanceof UserInputException)
            {
                Optional.ofNullable(UserInputException.class.cast(ex).getErrorCause())
                    .ifPresent(errorCause ->
                        {
                            LOG.info("ErrorCause in french: " + errorCause.toString());

                            // Expects unsupported color letter problem
                            this.softly
                                .assertThat(errorCause)
                                .isSameAs(UserInputException.ErrorCause.COLOR_NOT_SUPPORTED);
                        }
                    );
            }

            // Expects
            throw ex;
        }
    }

    @Test
    public void should_allow_NBJV_constructor_userInput()
    {
        LOG.info("*************** should_allow_NBJV_constructor_userInput() ***************");

        // Given
        String userInput = "NBjV"; // We test downcase case

        // When
        PinCombination combination = new PinCombination(userInput);
        LOG.log(Level.INFO, "Built following combination {0}", combination);

        // Expects
        this.softly.assertThat(combination.getPins()).containsExactly(Pin.BLACK, Pin.BLUE, Pin.YELLOW, Pin.GREEN);
    }
}
