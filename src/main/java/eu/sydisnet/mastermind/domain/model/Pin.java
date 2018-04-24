package eu.sydisnet.mastermind.domain.model;

/**
 * Defines the 'PIN' concept.
 *
 * <p>
 *     A pin is associated with a color. There are 6 pin types, depending on the color:
 *     <ul>
 *         <li>The <strong>RED</strong> Pin,</li>
 *         <li>The <strong>YELLOW</strong> Pin,</li>
 *         <li>The <strong>BLUE</strong> Pin,</li>
 *         <li>The <strong>ORANGE</strong> Pin,</li>
 *         <li>The <strong>GREEN</strong> Pin,</li>
 *         <li>The <strong>BLACK</strong> Pin.</li>
 *     </ul>
 * </p>
 * <p>
 *     Each 'PIN' is obtained from a French shortcut, depending on the color: 'R'(ouge for Red), 'J'(aune for Yellow), 'B'(leu for Blue),
 *     'O'(range), 'V'(ert for Green) and 'N'(oir for Black). For further convenience, 'PINS' will be obtained with a Builder.
 *     (Cf. Builder Pattern).
 * </p>
 *
 * @author sydisnet
 *         Project: mastermind
 *         Copyright (C) 2018 SYDISNET
 * @version 1.0.0.0-SNAPSHOT
 * @since 1.0.0.0-SNAPSHOT
 */
public enum Pin
{
    /**
     * Supported pins.
     */
    RED('R', "Rouge"), YELLOW('J', "Jaune"), BLUE('B', "Bleu"), ORANGE('O', "Orange"), GREEN('V', "Vert"), BLACK('N', "Noir");

    /**
     * Color Shortcut in French.
     */
    private char frenchColorShortcut;

    /**
     * Color in French.
     */
    private String frenchColorName;

    /**
     * Builds an instance from a French Color Shortcut.
     *
     * @param frenchColorShortcut the corresponding letter in French that corresponds to a supported color.
     * @param frenchColorName the color's name in French language
     */
    Pin(final char frenchColorShortcut, final String frenchColorName)
    {
        this.frenchColorShortcut = frenchColorShortcut;
        this.frenchColorName = frenchColorName;
    }

    public char getFrenchColorShortcut()
    {
        return this.frenchColorShortcut;
    }

    public String getFrenchColorName()
    {
        return this.frenchColorName;
    }

    /**
     * Builder-Pattern implementation.
     */
    public static class Builder
    {
        /**
         * The pin being built.
         */
        private Pin pinUnderConstruction;

        /**
         * Builder pattern that determines correct color from French shortcut ignoring case.
         *
         * @param frenchShortcut the given shortcut from which we want to build the corresponding {@link Pin} instance
         *
         */
        public Builder(final char frenchShortcut)
        {
            switch (frenchShortcut)
            {
                case 'R':
                case 'r':
                    this.pinUnderConstruction = Pin.RED;
                    break;
                case 'J':
                case 'j':
                    this.pinUnderConstruction = Pin.YELLOW;
                    break;
                case 'B':
                case 'b':
                    this.pinUnderConstruction = Pin.BLUE;
                    break;
                case 'O':
                case 'o':
                    this.pinUnderConstruction = Pin.ORANGE;
                    break;
                case 'V':
                case 'v':
                    this.pinUnderConstruction = Pin.GREEN;
                    break;
                case 'N':
                case 'n':
                    this.pinUnderConstruction = Pin.BLACK;
                    break;
                default:
                    throw new IllegalArgumentException(String.format("The following shortcut [%c] is not supported !", frenchShortcut));
            }
        }

        /**
         * Finalizes construction.
         *
         * @return the corresponding color.
         */
        public Pin build()
        {
            return this.pinUnderConstruction;
        }
    }
}
