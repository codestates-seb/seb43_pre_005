import { useState } from "react";
import styled from "styled-components";

const QuestionButtonDesgin = styled.div`
  display: flex;
  flex-direction: wrap;
  width: 40px;
  height: 40px;
  margin-top: 80px;
  margin-left: 10px;
  font-size: 40px;

  color: #888888;

  box-sizing: border-box;
  .questionbutton {
    box-sizing: border-box;
    margin: 0;
    line-height: 0;
  }
  div {
    cursor: pointer;
  }
  p {
    margin-left: 3px;
    color: black;
    cursor: default;
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
        <div className="upbutton" onClick={handleIncreaseClick}>
          ▲
        </div>
        <p>{number}</p>
        <div className="downbutton" onClick={handleDecreaseClick}>
          ▼
        </div>
      </div>
    </QuestionButtonDesgin>
  );
}

export default QuestionButton;
