package eu.sydisnet.mastermind.domain.model;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
     * Corresponds to the count of fair pins, i.e. pins that have the correct color and are correctly placed.
     */
    private int fairPinCount;

    /**
     * Corresponds to the count of fair (correct color) but misplaced pins.
     */
    private int misplacedPinCount;

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
    GuessCombination(final String answer)
    {
        super(answer);
    }

    public int getFairPinCount()
    {
        return this.fairPinCount;
    }

    private void setFairPinCount(final int fairPinCount)
    {
        this.fairPinCount = fairPinCount;
    }

    public int getMisplacedPinCount()
    {
        return misplacedPinCount;
    }

    private void setMisplacedPinCount(final int misplacedPinCount)
    {
        this.misplacedPinCount = misplacedPinCount;
    }

    @Override
    public boolean equals(final Object pinCombination)
    {
        boolean goodAnswer = super.equals(pinCombination);

        if (goodAnswer)
        {
            this.setFairPinCount(4);
            this.setMisplacedPinCount(0);
        }
        else if (pinCombination instanceof PinCombination)
        {
            // This guess combination is "comparable" with provided pin combination
            PinCombination comparable = PinCombination.class.cast(pinCombination);
            this.updateExactlyFairClue(comparable);
            this.updateMisplacedClue(comparable);
        }

        // Returns true if he answer is guessed, false otherwise
        return goodAnswer;
    }

    @Override
    public int hashCode()
    {
        // To avoid checkstyle warning !
        return super.hashCode();
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

    /**
     * Business-Method that updates {@link this#fairPinCount}.
     *
     * @param pinCombination the combination provided by user
     */
    private void updateExactlyFairClue(final PinCombination pinCombination)
    {
        List<Pin> userPins = pinCombination.getPins();
        List<Pin> riddlePins = this.getPins();

        assert userPins.size() == riddlePins.size() : "Combinations must have the same size !";

        int exactlyPlaced = 0;
        for (int i = 0; i < riddlePins.size(); i++)
        {
            Pin currentRiddlePin = riddlePins.get(i);

            // Exactly Placed
            if (currentRiddlePin.equals(userPins.get(i)))
            {
                exactlyPlaced++;
            }
        }
        this.setFairPinCount(exactlyPlaced);
    }

    /**
     * Business-Method that updates {@link this#misplacedPinCount}.
     *
     * @param pinCombination the combination provided by user
     */
    private void updateMisplacedClue(final PinCombination pinCombination)
    {
        Map<Pin, Long> countOccurrenceMap = super.getOccurencePinMap();
        Map<Pin, Long> countOccurrenceMapOther = pinCombination.getOccurencePinMap();

        // For each pin in both combination, we have to determine the minimum value between the two 'count value'
        Map<Pin, Long> countOccurrenceIntersect = new HashMap<>();
        countOccurrenceMap.forEach(
            (currentPin, occurrenceCount) -> countOccurrenceIntersect.put(
                currentPin, Math.min(occurrenceCount, countOccurrenceMapOther.get(currentPin))
            )
        );

        // Now, we are able to sum all count values in intersection map to know the count of correct pins in terms of colors
        int correctPins = countOccurrenceIntersect.values().stream().mapToInt(Long::intValue).sum();

        // And finally, the number of corrected pins but misplaced
        this.setMisplacedPinCount(correctPins - this.fairPinCount);
    }
}
