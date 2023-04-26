package com.potatos.stackoverflow.domain.question.service;

import com.potatos.stackoverflow.domain.question.dto.QuestionResponseDto;
import com.potatos.stackoverflow.domain.question.entity.Question;
import com.potatos.stackoverflow.domain.question.entity.QuestionTag;
import com.potatos.stackoverflow.domain.question.repository.QuestionRepository;
import com.potatos.stackoverflow.domain.question.repository.QuestionTagRepository;
import com.potatos.stackoverflow.domain.tags.entity.Tag;
import com.potatos.stackoverflow.domain.tags.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.NoSuchMessageException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
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
    public Page<Question> getSearchTest(int page, int size, Long tagId){

        //PageRequest pageRequest = PageRequest.of(page, size, Sort.by("questionId").descending());
        List<QuestionTag> searchList = questionTagRepo.findAllByTagId(tagId);

        // questionIds를 얻기 위한 스트림 작업
        List<Long> questionIds = searchList.stream().map(questionTag -> questionTag.getQuestion().getQuestionId()).collect(Collectors.toList());


        //백업
//        List<Optional<Question>> optionalList = questionIds.stream()
//                .map(questionId -> questionRepository.findById(questionId)).collect(Collectors.toList());
        //백업


        //page 처리
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("questionId").descending());


        // questionIds를 이용하여 Question 객체를 얻기 위한 스트림 작업
        List<Optional<Question>> optionalList = questionIds.stream()
                .map(questionId -> questionRepository.findById(questionId)).collect(Collectors.toList());
        //repo에서 꺼내는 값 자체가 Optional이라서 question이 Optional로 감싸져야하지 List<Question>을 Optional로 감싸면 ide가 곤란해함


        //optional 꺼내기 ....
        List<Question> questionResponse = optionalList.stream().map(e -> e.get()).collect(Collectors.toList());

        //페이지네이션 밖에서 하는걸로

        //List<Question> response = questionRepository.findById().stream().map(e -> qustionsId).collect(Collectors.toList());

        return null;
    }


}
