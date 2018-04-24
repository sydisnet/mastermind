package eu.sydisnet.mastermind.application;

import eu.sydisnet.mastermind.domain.model.PinCombination;
import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Assume;
import org.junit.Rule;
import org.junit.Test;

import java.lang.invoke.MethodHandles;
import java.util.logging.Logger;

/**
 * Tests the game.
 *
 * @author sydisnet
 *         Project: mastermind
 *         Copyright (C) 2018 SYDISNET
 * @version 1.0.0.0-SNAPSHOT
 * @since 1.0.0.0-SNAPSHOT
 */
public class GameScenarioFunctionalTestCase
{
    private static final Logger LOG = Logger.getLogger(MethodHandles.lookup().getClass().getName());

    @Rule
    public final JUnitSoftAssertions softly = new JUnitSoftAssertions();

    /**
     * Game under test.
     */
    private Game game;

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
    public void apply_provided_scenario()
    {
        LOG.info("*************** apply_provided_scenario() ***************");

        // Given
        Assume.assumeNotNull(this.game);
        this.expectsValues(Game.Status.NEW_GAME, 0, 0, 0, 10);

        // 1. When player submits "NBJV"
        this.game.offer(new PinCombination("NBJV"));
        this.expectsValues(Game.Status.PLAYING, 0, 1, 1, 10);

        // 2. Then player submits "JJOR"
        this.game.offer(new PinCombination("JJOR"));
        this.expectsValues(Game.Status.PLAYING, 1, 2, 2, 10);

        // 3. Then player submits "RJRO"
        this.game.offer(new PinCombination("RJRO"));
        this.expectsValues(Game.Status.PLAYING, 1, 2, 3, 10);

        // 4. Then player submits "ORJO"
        this.game.offer(new PinCombination("ORJO"));
        this.expectsValues(Game.Status.PLAYING, 0, 4, 4, 10);

        // 5. Then player submits "ROOJ"
        this.game.offer(new PinCombination("ROOJ"));
        this.expectsValues(Game.Status.WON, 4, 0, 5, 10);
    }

    private void expectsValues(
        final Game.Status expectedStatus,
        final int expectedFair,
        final int expectedMisplaced,
        final int expectNumberOfAttemtps,
        @SuppressWarnings("SameParameterValue") final int expectedMaxRetries)
    {
        this.softly.assertThat(this.game.getGameStatus()).isEqualTo(expectedStatus);
        this.softly.assertThat(this.game.getLastFairPinCount()).isEqualTo(expectedFair);
        this.softly.assertThat(this.game.getLastMisplacedPinCount()).isEqualTo(expectedMisplaced);
        this.softly.assertThat(this.game.getNumberOfAttempts()).isEqualTo(expectNumberOfAttemtps);
        this.softly.assertThat(this.game.getMaxAttempts()).isEqualTo(expectedMaxRetries);
    }
}
