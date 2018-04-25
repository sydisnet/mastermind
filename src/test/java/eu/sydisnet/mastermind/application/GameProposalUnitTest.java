package eu.sydisnet.mastermind.application;

import eu.sydisnet.mastermind.domain.model.Pin;
import eu.sydisnet.mastermind.domain.model.PinCombination;
import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Rule;
import org.junit.Test;

import java.lang.invoke.MethodHandles;
import java.util.logging.Logger;

/**
 * Unit Testing class that validates constraints on {@link Game.Proposal} concept.
 *
 * @author sydisnet
 *         Project: mastermind
 *         Copyright (C) 2018 SYDISNET
 * @version 1.0.0.0-SNAPSHOT
 * @since 1.0.0.0-SNAPSHOT
 */
public class GameProposalUnitTest
{
    private static final Logger LOG = Logger.getLogger(MethodHandles.lookup().getClass().getName());

    @Rule
    public final JUnitSoftAssertions softly = new JUnitSoftAssertions();

    @Test(expected = IllegalArgumentException.class)
    public void should_not_allow_null_constructor_combination()
    {
        LOG.info("*************** should_not_allow_null_constructor_combination() ***************");

        new Game.Proposal(null, 0, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_not_allow_negative_constructor_fairPinCount()
    {
        LOG.info("*************** should_not_allow_negative_constructor_fairPinCount() ***************");

        new Game.Proposal(new PinCombination("JVBN"), -1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_not_allow_gt4_constructor_fairPinCount()
    {
        LOG.info("*************** should_not_allow_gt4_constructor_fairPinCount() ***************");

        new Game.Proposal(new PinCombination("JVBN"), 5, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_not_allow_negative_constructor_misplacedPinCount()
    {
        LOG.info("*************** should_not_allow_negative_constructor_fairPinCount() ***************");

        new Game.Proposal(new PinCombination("JVBN"), 0, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_not_allow_gt4_constructor_misplacedPinCount()
    {
        LOG.info("*************** should_not_allow_gt4_constructor_fairPinCount() ***************");

        new Game.Proposal(new PinCombination("JVBN"), 0, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_not_allow_gt4_constructor_fair_plus_misplaced_pin_count()
    {
        LOG.info("*************** should_not_allow_gt4_constructor_fair_plus_misplaced_pin_count() ***************");

        new Game.Proposal(new PinCombination("JVBN"), 3, 2);
    }

    @Test
    public void should_allow_not_null_and_consistent_constructor_params()
    {
        LOG.info("*************** should_allow_not_null_and_consistent_constructor_params() ***************");

        // When
        Game.Proposal proposal = new Game.Proposal(new PinCombination("JVBN"), 1, 2);

        // Expects
        this.softly.assertThat(proposal.getCombination()).isNotNull();
        this.softly.assertThat(proposal.getCombination().getPins()).containsExactly(Pin.YELLOW, Pin.GREEN, Pin.BLUE, Pin.BLACK);
        this.softly.assertThat(proposal.getFairPinCount()).isEqualTo(1);
        this.softly.assertThat(proposal.getMisplacedPinCount()).isEqualTo(2);
    }
}
