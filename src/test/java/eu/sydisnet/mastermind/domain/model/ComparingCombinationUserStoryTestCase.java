package eu.sydisnet.mastermind.domain.model;

import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Rule;
import org.junit.Test;

import java.lang.invoke.MethodHandles;
import java.util.logging.Logger;

/**
 * Tests different combination from provided samples by <strong>Product Owner</strong> with some corrections
 * because some provided samples are incorrect.
 *
 * <p>
 *     This kind of testing class would suit perfectly with Cucumber.
 * </p>
 *
 * @author sydisnet
 *         Project: mastermind
 *         Copyright (C) 2018 SYDISNET
 * @version 1.0.0.0-SNAPSHOT
 * @since 1.0.0.0-SNAPSHOT
 */
public class ComparingCombinationUserStoryTestCase
{
    private static final Logger LOG = Logger.getLogger(MethodHandles.lookup().getClass().getName());

    @Rule
    public final JUnitSoftAssertions softly = new JUnitSoftAssertions();

    /**
     * Riddle under test.
     *
     * <p>
     *     Calling package-private constructor. Other clients of {@link GuessCombination} are unable
     *     to call this constructor.
     * </p>
     */
    private GuessCombination riddle = new GuessCombination("ROOJ");

    /**
     * |-------------------|
     * |NBJV|​ ​ 0 ​ ​ | ​ ​ 1 ​ ​ | ​ ​ 1/10​ ​ |
     * |....|​ ​ . ​ ​ | ​ ​ . ​ ​ | ​ ​ 2/10​ ​ |
     * |-------------------|
     */
    @Test
    public void test_combination_NBJV()
    {
        LOG.info("*************** test_combination_NBJV() ***************");

        // Given
        PinCombination userProvided = new PinCombination("NBJV");

        // When
        boolean hasWon = riddle.equals(userProvided);

        // Expects
        this.softly.assertThat(hasWon).isFalse();
        this.softly.assertThat(riddle.getFairPinCount()).isEqualTo(0);
        this.softly.assertThat(riddle.getMisplacedPinCount()).isEqualTo(1);
    }

    /**
     * |-------------------|
     * |NBJV|​ ​ 0 ​ ​ | ​ ​ 1 ​ ​ | ​ ​ 1/10​ ​ |
     * |JJOR|​ ​ 0 ​ ​ | ​ ​ 3 ​ ​ | ​ ​ 2/10​ ​ | ==> Error in provided sample: should be |JJOR|​ ​ 1 ​ ​ | ​ ​ 2 ​ ​ | ​ ​ 2/10​ ​ |
     * |....|​ ​ . ​ ​ | ​ ​ . ​ ​ | ​ ​ 3/10​ ​ |
     * |-------------------|
     */
    @Test
    public void test_combination_JJOR()
    {
        LOG.info("*************** test_combination_JJOR() ***************");

        // Given
        PinCombination userProvided = new PinCombination("JJOR");

        // When
        boolean hasWon = riddle.equals(userProvided);

        // Expects
        this.softly.assertThat(hasWon).isFalse();
        this.softly.assertThat(riddle.getFairPinCount()).isEqualTo(1);
        this.softly.assertThat(riddle.getMisplacedPinCount()).isEqualTo(2);
    }

    /**
     * |-------------------|
     * |NBJV|​ ​ 0 ​ ​ | ​ ​ 1 ​ ​ | ​ ​ 1/10​ ​ |
     * |JJOR|​ ​ 1 ​ ​ | ​ ​ 2 ​ ​ | ​ ​ 2/10​ ​ |
     * |RJRO|​ ​ 1 ​ ​ | ​ ​ 2 ​ ​ | ​ ​ 3/10​ ​ |
     * |....|​ ​ . ​ ​ | ​ ​ . ​ ​ | ​ ​ 4/10​ ​ |
     * |-------------------|
     */
    @Test
    public void test_combination_RJRO()
    {
        LOG.info("*************** test_combination_RJRO() ***************");

        // Given
        PinCombination userProvided = new PinCombination("RJRO");

        // When
        boolean hasWon = riddle.equals(userProvided);

        // Expects
        this.softly.assertThat(hasWon).isFalse();
        this.softly.assertThat(riddle.getFairPinCount()).isEqualTo(1);
        this.softly.assertThat(riddle.getMisplacedPinCount()).isEqualTo(2);
    }

    /**
     * |-------------------|
     * |NBJV|​ ​ 0 ​ ​ | ​ ​ 1 ​ ​ | ​ ​ 1/10​ ​ |
     * |JJOR|​ ​ 1 ​ ​ | ​ ​ 2 ​ ​ | ​ ​ 2/10​ ​ |
     * |RJRO|​ ​ 1 ​ ​ | ​ ​ 2 ​ ​ | ​ ​ 3/10​ ​ |
     * |ORJO|​ ​ 0 ​ ​ | ​ ​ 4 ​ ​ | ​ ​ 4/10​ ​ |
     * |....|​ ​ . ​ ​ | ​ ​ . ​ ​ | ​ ​ 5/10​ ​ |
     * |-------------------|
     */
    @Test
    public void test_combination_ORJO()
    {
        LOG.info("*************** test_combination_ORJO() ***************");

        // Given
        PinCombination userProvided = new PinCombination("ORJO");

        // When
        boolean hasWon = riddle.equals(userProvided);

        // Expects
        this.softly.assertThat(hasWon).isFalse();
        this.softly.assertThat(riddle.getFairPinCount()).isEqualTo(0);
        this.softly.assertThat(riddle.getMisplacedPinCount()).isEqualTo(4);
    }

    /**
     * |-------------------|
     * |NBJV|​ ​ 0 ​ ​ | ​ ​ 1 ​ ​ | ​ ​ 1/10​ ​ |
     * |JJOR|​ ​ 1 ​ ​ | ​ ​ 2 ​ ​ | ​ ​ 2/10​ ​ |
     * |RJRO|​ ​ 1 ​ ​ | ​ ​ 2 ​ ​ | ​ ​ 3/10​ ​ |
     * |ORJO|​ ​ 0 ​ ​ | ​ ​ 4 ​ ​ | ​ ​ 4/10​ ​ |
     * |ROOJ|​ ​ 0 ​ ​ | ​ ​ 4 ​ ​ | ​ ​ 5/10​ ​ | ==> Error in provided sample: should be |ROOJ|​ ​ 4 ​ ​ | ​ ​ 0 ​ ​ | ​ ​ 5/10​ ​ |
     * |....|​ ​ . ​ ​ | ​ ​ . ​ ​ | ​ ​ 6/10​ ​ |
     * |-------------------|
     */
    @Test
    public void test_combination_ROOJ()
    {
        LOG.info("*************** test_combination_ROOJ() ***************");

        // Given
        PinCombination userProvided = new PinCombination("ROOJ");

        // When
        boolean hasWon = riddle.equals(userProvided);

        // Expects
        this.softly.assertThat(hasWon).isTrue();
        this.softly.assertThat(riddle.getFairPinCount()).isEqualTo(4);
        this.softly.assertThat(riddle.getMisplacedPinCount()).isEqualTo(0);
    }
}
