package eu.sydisnet.mastermind.domain.model;

import java.security.SecureRandom;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Defines the {@link PinCombination} to guess.
 *
 * @author sydisnet
 *         Project: mastermind
 *         Copyright (C) 2018 SYDISNET
 * @version 1.0.0.0-SNAPSHOT
 * @since 1.0.0.0-SNAPSHOT
 */
public class GuessCombination extends PinCombination
{
    /**
     * Contains a string containing all supported characters.
     *
     * <p>
     *     In this way if {@link Pin} definition changes, refactoring will be easier.
     * </p>
     */
    private static final String SUPPORTED_CHARS = Stream
        .of(Pin.values())
            .map(c -> Character.toString(c.getFrenchColorShortcut()))
            .collect(Collectors.joining());

    /**
     * Builds an instance with an auto-generated answer to guess.
     */
    public GuessCombination()
    {
        this(generateRiddle());
    }

    /**
     * Builds an instance with the answer to guess.
     *
     * @param answer the correct {@link PinCombination} to guess.
     */
    private GuessCombination(final String answer)
    {
        super(answer);
    }

    /**
     * Helper-Method that generates a supported user-input.
     *
     * <p>
     *     This method is static because we cannot reference a non-static member method in the same class when
     *     invoking super method.
     * </p>
     */
    private static String generateRiddle()
    {
        // Builds a buffer
        StringBuilder riddle = new StringBuilder();
        Random random = new SecureRandom();
        for (int i = 0; i < 4; i++)
        {
            // Generates a random int based on supportedChars length
            int charPos = random.nextInt(SUPPORTED_CHARS.length());

            // Selects a random char
            char correspondingChar = SUPPORTED_CHARS.charAt(charPos);
            riddle.append(correspondingChar);
        }

        return riddle.toString();
    }
}
