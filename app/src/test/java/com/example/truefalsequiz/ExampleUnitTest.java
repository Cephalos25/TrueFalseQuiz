package com.example.truefalsequiz;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void buildQuiz_works() {
        Question q1 = new Question("q1", true);
        Question q2 = new Question("q2", false);
        Question q3 = new Question("q3", true);
        Question q4 = new Question("q4", false);
        Question q5 = new Question("q5", true);
        Question q6 = new Question("q6", false);
        Question q7 = new Question("q7", true);
        Question q8 = new Question("q8", false);
        Question q9 = new Question("q9", true);
        Question q10 = new Question("q10", false);

        List<Question> questions = new ArrayList<>();
        questions.add(q1);
        questions.add(q2);
        questions.add(q3);
        questions.add(q4);
        questions.add(q5);
        questions.add(q6);
        questions.add(q7);
        questions.add(q8);
        questions.add(q9);
        questions.add(q10);
        List<Question> quizQuestions = new ArrayList<>();

        Quiz quiz = new Quiz(quizQuestions, 0,0);
        quiz.build(questions, 5);

        assertEquals(5, quiz.getQuestions().size());
        assertEquals(0, quiz.getScore());
        assertEquals(0, quiz.getQuestionNumber());
    }

    @Test
    public void nextQuestion_isCorrect() {
        Question q1 = new Question("q1", true);
        Question q2 = new Question("q2", false);
        Question q3 = new Question("q3", true);

        List<Question> questions = new ArrayList<>();
        questions.add(q1);
        questions.add(q2);
        questions.add(q3);

        Quiz quiz = new Quiz(questions, 0, 0);
        quiz.setQuestionNumber(1);

        Question currentQuestion = quiz.getQuestions().get(quiz.getQuestionNumber()-1);
        assertEquals(true, currentQuestion.getAnswer());
    }

    @Test
    public void nextQuestion_isConsecutiveCorrect() {
        Question q1 = new Question("q1", true);
        Question q2 = new Question("q2", false);
        Question q3 = new Question("q3", true);

        List<Question> questions = new ArrayList<>();
        questions.add(q1);
        questions.add(q2);
        questions.add(q3);

        Quiz quiz = new Quiz(questions, 0, 0);

        quiz.setQuestionNumber(1);
        Question currentQuestion = quiz.getQuestions().get(quiz.getQuestionNumber()-1);
        assertEquals(true, currentQuestion.getAnswer());
        assertEquals(false, quiz.lastQuestion());

        quiz.setQuestionNumber(quiz.getQuestionNumber()+1);
        currentQuestion = quiz.getQuestions().get(quiz.getQuestionNumber()-1);
        assertEquals(false, currentQuestion.getAnswer());
        assertEquals(false, quiz.lastQuestion());

        quiz.setQuestionNumber(quiz.getQuestionNumber()+1);
        currentQuestion = quiz.getQuestions().get(quiz.getQuestionNumber()-1);
        assertEquals(true, currentQuestion.getAnswer());
        assertEquals(true, quiz.lastQuestion());
    }
}