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

  .name-box {
    display: flex;
    align-items: right;
    justify-content: space-between;
    margin-top: 1rem;

    .question-tag {
      border: none;
      padding: 0.3rem;
      border-radius: 5px;
      background-color: #baaad7;
      color: white;
      cursor: pointer;
    }

    .question-name {
      margin-left: 30rem;
      font-weight: bold;
    }
  }
`;

const Question = ({ question }) => {
  //   const parsedDate = new Date(tag.createdAt).toLocaleDateString("ko-kr");

  return (
    <QuestionContainer>
      <div className="question-id">
        <div className="question-title">{question.title}</div>
        <div className="question-content">{question.content}</div>
        <div className="name-box">
          <div className="question-tag">{question.tag}</div>
          <div className="question-name">{question.name}</div>
          <div className="question-createAt">{question.createdAt}</div>
        </div>
      </div>
    </QuestionContainer>
  );
};

export default Question;
