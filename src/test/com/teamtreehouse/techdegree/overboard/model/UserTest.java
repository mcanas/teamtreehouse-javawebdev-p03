package com.teamtreehouse.techdegree.overboard.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    private Board board;

    @Before
    public void setUp() throws Exception {
        board = new Board("Java");
    }

    @Test
    public void userReputationIncreasesWhenTheirQuestionIsUpvoted() throws Exception {
        User alice = board.createUser("alice");
        User bob = board.createUser("bob");
        Question question = alice.askQuestion("What is a String?");

        bob.upVote(question);

        assertEquals("User reputation increases by 5 when their question is upvoted", 5, alice.getReputation());
    }

    @Test
    public void userReputationIncreasesWhenTheirAnswerIsUpvoted() throws Exception {
        User alice = board.createUser("alice");
        User bob = board.createUser("bob");
        Question question = alice.askQuestion("What is a String?");
        Answer answer = bob.answerQuestion(question, "It is a series of characters, strung together...");

        alice.upVote(answer);

        assertEquals("User reputation increases by 10 when their answer is upvoted", 10, bob.getReputation());
    }
}