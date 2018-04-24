package eu.sydisnet.mastermind.domain.model;

import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Rule;
import org.junit.Test;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Unit Testing class that validates constraints on {@link Pin} concept.
 *
 * @author sydisnet
 *         Project: mastermind
 *         Copyright (C) 2018 SYDISNET
 * @version 1.0.0.0-SNAPSHOT
 * @since 1.0.0.0-SNAPSHOT
 */
public class PinUnitTest
{
    private static final Logger LOG = Logger.getLogger(MethodHandles.lookup().getClass().getName());

    @Rule
    public final JUnitSoftAssertions softly = new JUnitSoftAssertions();

    @Test
    public void should_support_well_defined_set_of_colors()
    {
        LOG.info("*************** should_support_well_defined_set_of_colors() ***************");

        // Given
        Map<Character, String> colorMap = new HashMap<>();
        colorMap.put('R', "Rouge");
        colorMap.put('J', "Jaune");
        colorMap.put('B', "Bleu");
        colorMap.put('O', "Orange");
        colorMap.put('V', "Vert");
        colorMap.put('N', "Noir");

        colorMap.forEach((shortcut, colorName) ->
            {
                // When
                Pin correspondingPin = new Pin.Builder(shortcut).build();

                LOG.log(Level.INFO, "Shortcut: {0} corresponds to following color: {1} and color name : {2}",
                    new String[]
                        {
                            Character.toString(shortcut),
                            correspondingPin.toString(),
                            correspondingPin.getFrenchColorName()
                        }
                );

                // Expects
                this.softly.assertThat(correspondingPin.getFrenchColorShortcut()).isEqualTo(shortcut);
                this.softly.assertThat(correspondingPin.getFrenchColorName()).isEqualTo(colorName);
            }
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_not_allow_unknown_color()
    {
        LOG.info("*************** should_not_allow_unknown_color() ***************");

        // Given
        char aquaColor = 'A';

        // When
        try
        {
            new Pin.Builder(aquaColor).build();
        }
        catch (Exception ex)
        {
            LOG.info("Message: " + ex.getMessage());

            // Expects exception
            throw ex;
        }
    }
}
