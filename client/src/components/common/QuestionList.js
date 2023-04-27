import React from "react";
import styled from "styled-components";

const QuestionListContainer = styled.div`
  margin-top: 20px;
`;

const QuestionListItem = styled.div`
  background-color: white;
  padding: 16px;
  margin-bottom: 16px;
  box-shadow: 0px 1px 2px rgba(0, 0, 0, 0.1);
`;

const QuestionTitle = styled.h3`
  margin-top: 0;
`;

const QuestionBody = styled.p`
  margin-bottom: 0;
`;

const QuestionList = ({ questions }) => {
  return (
    <QuestionListContainer>
      {questions.map((question) => (
        <QuestionListItem key={question.id}>
          <QuestionTitle>{question.title}</QuestionTitle>
          <QuestionBody>{question.body}</QuestionBody>
        </QuestionListItem>
      ))}
    </QuestionListContainer>
  );
};

export default QuestionList;
