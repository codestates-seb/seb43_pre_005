import { useState } from "react";
import styled from "styled-components";

const QuestionButtonDesgin = styled.div`
  display: flex;
  flex-direction: wrap;
  width: 40px;
  height: 40px;
  margin-top: 100px;
  margin-left: 20px;

  box-sizing: border-box;
  .questionbutton {
    box-sizing: border-box;
    margin: 0;
    line-height: 0;
  }
  p {
    margin-left: 1px;
  }
`;
function QuestionButton() {
  const [number, setNumber] = useState(0);

  function handleIncreaseClick() {
    setNumber(number + 1);
  }

  function handleDecreaseClick() {
    setNumber(number - 1);
  }

  return (
    <QuestionButtonDesgin>
      <div className="questionbutton">
        <div onClick={handleIncreaseClick}>▲</div>
        <p>{number}</p>
        <div onClick={handleDecreaseClick}>▼</div>
      </div>
    </QuestionButtonDesgin>
  );
}

export default QuestionButton;
