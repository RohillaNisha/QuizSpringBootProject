package com.rohilla.quizapp.service;

import com.rohilla.quizapp.dao.QuestionDao;
import com.rohilla.quizapp.dao.QuizDao;
import com.rohilla.quizapp.model.Question;
import com.rohilla.quizapp.model.QuestionWrapper;
import com.rohilla.quizapp.model.Quiz;
import com.rohilla.quizapp.model.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Question> questions =  questionDao.findRandomQuestionsByCategory(category, numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
       return new ResponseEntity<>("Quiz created", HttpStatus.CREATED);
   }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questionsFromDb = quiz.get().getQuestions();
        List<QuestionWrapper> questionForUser = new ArrayList<>();
        for(Question q : questionsFromDb){
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(),q.getOption4());
            questionForUser.add(qw);
        }

        return new ResponseEntity<>(questionForUser,HttpStatus.OK);

    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<UserResponse> userResponses) {
        Quiz quiz = quizDao.findById(id).get();
        List<Question> questions = quiz.getQuestions();
        int questionsResponsedRight = 0;
        int i = 0;
        for(UserResponse userResponse: userResponses){
            if (userResponse.getUserResponse().equals(questions.get(i).getRightAnswer()))
                questionsResponsedRight++;
            i++;
        }
        return new ResponseEntity<>(questionsResponsedRight, HttpStatus.OK);
    }
}
