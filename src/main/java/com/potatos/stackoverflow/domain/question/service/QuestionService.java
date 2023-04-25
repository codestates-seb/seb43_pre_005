package com.potatos.stackoverflow.domain.question.service;

import com.potatos.stackoverflow.domain.question.entity.Question;
import com.potatos.stackoverflow.domain.question.entity.QuestionTag;
import com.potatos.stackoverflow.domain.question.repository.QuestionRepository;
import com.potatos.stackoverflow.domain.question.repository.QuestionTagRepository;
import com.potatos.stackoverflow.domain.tags.entity.Tag;
import com.potatos.stackoverflow.domain.tags.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.NoSuchMessageException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    //filter-Tag
    private final TagRepository tagRepository;
    private final QuestionTagRepository questionTagRepo;
    @Autowired
    public QuestionService(QuestionRepository questionRepository, QuestionTagRepository questionTagRepo, TagRepository tagRepository){
        this.questionRepository = questionRepository;
        this.questionTagRepo = questionTagRepo;
        this.tagRepository = tagRepository;
    }


    /*
     * 게시물 업로드하는 method 입니다.
     * 입력값 : Question
     * 출력값 : Question
     */
    @Transactional
    public Question createQuestion(Question question){

        return questionRepository.save(question);
    }

    /*
     * 게시물 하나를 조회하는 method 입니다.
     * 입력값 : questionId
     * 출력값 : Question
     */
    public Question getQuestion(Long questionId){

        return questionRepository.findById(questionId).orElseThrow(()-> new NoSuchMessageException("해당하는 게시물이 없습니다."));
    }

    /*
     * 게시물 리스트를 조회하는 method 입니다.
     * 입력값 : page, size
     * 출력값 : Question (page)
     */
    public Page<Question> getQuestions(int page, int size){

        List<Question> questionList = questionRepository.findAll();

        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("questionId").descending());

        return questionRepository.findAll(pageRequest);
    }

    /*
     * 게시물 Update method 입니다.
     * 입력값 : Question
     * 출력값 : Question
     */
    public Question updateQuestion(Question question){

        return questionRepository.save(question);
    }

    /*
     * 게시물 삭제 method 입니다.
     * 입력값 : questionId
     * 출력값 : void
     */
    public void deleteQuestion(Long questionId) {
        Question findQuestion = findVerifiedQuestion(questionId);
        questionRepository.delete(findQuestion);
    }



    /*
     * 게시물 존재 여부를 묻는 method 입니다.
     * 입력값 : questionId
     * 출력값 : Question
     */
    public Question findVerifiedQuestion(Long questionId){

        Optional<Question> optionalQuestion = questionRepository.findById(questionId);

        return optionalQuestion.orElseThrow(
                () -> new NoSuchMessageException("게시물이 없습니다."));
    }


    //tagFilterQuestion
    public List<Question> getSearchTest(Long tagId){

        //PageRequest pageRequest = PageRequest.of(page, size, Sort.by("questionId").descending());
        List<QuestionTag> searchList = questionTagRepo.findAllByTagId(tagId);

        List qustionsId = searchList.stream().map(questionTag -> questionTag.getQuestion().getQuestionId()).collect(Collectors.toList());

        //List<Question> response = questionRepository.findById()
                //.stream().map(e -> qustionsId).collect(Collectors.toList());






        return null;
    }
}
