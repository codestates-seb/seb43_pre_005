import styled from "styled-components";

const QuestionContainer = styled.div`
  border: solid 1px gray;
  margin: 0.5rem;
  width: 50vw;
  height: 15vh;
  padding: 1rem;

  .question-title {
    font-weight: bold;
    display: flex;
    margin-bottom: 1rem;
  }
`;

const Question = ({ question }) => {
  //   const parsedDate = new Date(tag.createdAt).toLocaleDateString("ko-kr");

  return (
    <QuestionContainer>
      <div className="question-id">
        <div className="question-title">{question.title}</div>
        <div className="question-content">{question.content}</div>
      </div>
    </QuestionContainer>
  );
};

export default Question;
