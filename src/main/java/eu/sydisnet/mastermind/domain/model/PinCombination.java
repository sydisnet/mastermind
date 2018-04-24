package eu.sydisnet.mastermind.domain.model;

import eu.sydisnet.mastermind.domain.exception.UserInputException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Defines a pin combination which is composed of four color pins.
 *
 * @author sydisnet
 *         Project: mastermind
 *         Copyright (C) 2018 SYDISNET
 * @version 1.0.0.0-SNAPSHOT
 * @since 1.0.0.0-SNAPSHOT
 */
public class PinCombination
{
    /**
     * The 4 pins list.
     */
    private List<Pin> pins;

    /**
     * Builds a pin combination from a {@link String} input.
     *
     * <p>
     *     For instance, 'ROOJ' corresponds to following combination :
     *     <ol>
     *         <li><strong>'R'</strong> for the first pin corresponds to {@link Pin#RED},</li>
     *         <li><strong>'O'</strong> for the second pin corresponds to {@link Pin#ORANGE},</li>
     *         <li><strong>'O'</strong> for the third pin corresponds to {@link Pin#ORANGE},</li>
     *         <li><strong>'J'</strong> for the last pin corresponds to {@link Pin#YELLOW}.</li>
     *     </ol>
     * </p>
     *
     * @param input the user {@link String} input to be parsed
     */
    public PinCombination(final String input)
    {
        if (input == null || input.trim().length() != 4)
        {
            throw new UserInputException(UserInputException.ErrorCause.INCORRECT_LENGTH);
        }

        // Builds a fixed array of four pins
        this.pins = Arrays.asList(new Pin[4]);

        // Parses input as character
        for (int i = 0; i < input.length(); i++)
        {
            try
            {
                this.pins.set(i, new Pin.Builder(input.charAt(i)).build());
            }
            catch (IllegalArgumentException ex)
            {
                throw new UserInputException(UserInputException.ErrorCause.COLOR_NOT_SUPPORTED, ex);
            }
        }
    }

    /**
     * Getter on pins property.
     *
     * @return the pin list that corresponds to this combination instance.
     */
    public List<Pin> getPins()
    {
        return Collections.unmodifiableList(pins);
    }

    @Override
    public String toString()
    {
        return "PinCombination{"
            + "pins={"
                + this.getPins()
                    .stream()
                    .map(c -> String.format("[%c/%s]", c.getFrenchColorShortcut(), c.getFrenchColorName()))
                    .collect(Collectors.joining(", "))
                + '}'
            + '}';
    }

    @Override
    public boolean equals(final Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof PinCombination))
        {
            return false;
        }
        PinCombination that = PinCombination.class.cast(o);

        // Compares the specified object with this list for equality.  Returns <tt>true</tt> if and only if the
        // specified object is also a list, both lists have the same size, and all corresponding pairs of elements in
        // the two lists are <i>equal</i>.
        return Objects.equals(this.getPins(), that.getPins());
    }

    @Override
    public int hashCode()
    {
        // Returns the hash code value for this list.  The hash code of a list is defined to be the result of the
        // following calculation:
        // <pre>{@code
        //     int hashCode = 1;
        //     for (E e : list)
        //         hashCode = 31*hashCode + (e==null ? 0 : e.hashCode());
        // }</pre>
        return Objects.hash(this.getPins());
    }
}

