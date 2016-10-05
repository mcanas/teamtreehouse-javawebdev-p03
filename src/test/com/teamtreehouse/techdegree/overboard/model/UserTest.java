package com.teamtreehouse.techdegree.overboard.model;

import com.teamtreehouse.techdegree.overboard.exc.VotingException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class UserTest {

    private Board board;
    private User alice;
    private User bob;
    private Question question;
    private Answer answer;

    @Before
    public void setUp() throws Exception {
        board = new Board("Java");
        alice = board.createUser("alice");
        bob = board.createUser("bob");
        question = alice.askQuestion("What is a String?");
        answer = bob.answerQuestion(question, "It is a series of characters, strung together...");
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void userReputationIncreasesWhenTheirQuestionIsUpvoted() throws Exception {
        bob.upVote(question);

        assertEquals("User reputation increases by 5 when their question is upvoted", 5, alice.getReputation());
    }

    @Test
    public void userReputationIncreasesWhenTheirAnswerIsUpvoted() throws Exception {
        alice.upVote(answer);

        assertEquals("User reputation increases by 10 when their answer is upvoted", 10, bob.getReputation());
    }

    @Test
    public void userReputationIncreasesWhenTheirAnswerIsAccepted() throws Exception {
        alice.acceptAnswer(answer);

        assertEquals("User reputation increases by 15 when their answer is accepted", 15, bob.getReputation());
    }

    @Test
    public void userCannotUpVoteTheirOwnQuestion() throws Exception {
        thrown.expect(VotingException.class);
        thrown.expectMessage("You cannot vote for yourself!");

        alice.upVote(question);
    }

    @Test
    public void userCannotDownVoteTheirOwnQuestion() throws Exception {
        thrown.expect(VotingException.class);
        thrown.expectMessage("You cannot vote for yourself!");

        alice.downVote(question);
    }

    @Test
    public void userCannotUpVoteTheirOwnAnswer() throws Exception {
        thrown.expect(VotingException.class);
        thrown.expectMessage("You cannot vote for yourself!");

        bob.upVote(answer);
    }

    @Test
    public void userCannotDownVoteTheirOwnAnswer() throws Exception {
        thrown.expect(VotingException.class);
        thrown.expectMessage("You cannot vote for yourself!");

        bob.downVote(answer);
    }
}