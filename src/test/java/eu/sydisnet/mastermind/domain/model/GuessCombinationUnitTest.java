package eu.sydisnet.mastermind.domain.model;

import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Rule;
import org.junit.Test;

import java.lang.invoke.MethodHandles;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Unit Testing class that validates constraints on {@link GuessCombination} concept.
 *
 * @author sydisnet
 *         Project: mastermind
 *         Copyright (C) 2018 SYDISNET
 * @version 1.0.0.0-SNAPSHOT
 * @since 1.0.0.0-SNAPSHOT
 */
public class GuessCombinationUnitTest
{
    private static final Logger LOG = Logger.getLogger(MethodHandles.lookup().getClass().getName());

    @Rule
    public final JUnitSoftAssertions softly = new JUnitSoftAssertions();

    @Test
    public void should_be_able_to_generate_riddle()
    {
        LOG.info("*************** should_be_able_to_generate_riddle() ***************");

        try
        {
            // When
            PinCombination pinCombination = new GuessCombination();
            LOG.log(Level.INFO, "Riddle: {0}", pinCombination);

            // Expects
            pinCombination.getPins()
                .forEach(c -> this.softly
                    .assertThat(c).isInstanceOfSatisfying(Pin.class,
                        pin ->
                        {
                            //noinspection ConfusingArgumentToVarargsMethod
                            this.softly.assertThat(pin).isIn((Object[])Pin.values());
                        })
                );
        }
        catch (Exception ex)
        {
            LOG.info("Message: " + ex.getMessage());

            // Does Not Expect
            this.softly.fail("Should have successfully generate a guess combination !", ex);
        }
    }

    @Test
    public void should_be_able_to_compare_pinCombination_with_guessCombination()
    {
        LOG.info("*************** should_be_able_to_compare_pinCombination_with_guessCombination() ***************");

        // Given a guess combination, i.e. with a auto-generated riddle
        PinCombination guessCombination = new GuessCombination();
        // Retrieving riddle
        String riddle = guessCombination.getPins()
            .stream()
                .map(c -> Character.toString(c.getFrenchColorShortcut()))
                .collect(Collectors.joining());
        LOG.log(Level.INFO, "Riddle: {0}", riddle);

        // When
        PinCombination correctCombination = new PinCombination(riddle);

        // Expects
        this.softly.assertThat(correctCombination).isEqualTo(guessCombination);
    }
}
