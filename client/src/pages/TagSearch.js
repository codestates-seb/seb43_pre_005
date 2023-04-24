import Layout from "../components/common/Layout";
import styled from "styled-components";
import Question from "../components/Question";
import qsdummydata from "../data/qsdummyData";
import { useState } from "react";

const HeadContainer = styled.div`
  .questions-number {
    font-size: 1.5rem;
    display: flex;
    justify-content: right;
    margin-right: 6rem;
  }

  .questions-box {
    width: 60vw;
    margin-left: 2rem;
    margin-top: 1rem;
  }
`;

const TagSearch = () => {
  const [questions, setQuestions] = useState(qsdummydata);

  return (
    <Layout>
      <HeadContainer>
        <div className="questions-number">{qsdummydata.length} questions</div>
        <div className="questions-box">
          {questions.map((el) => (
            <Question key={el.id} question={el} />
          ))}
        </div>
      </HeadContainer>
    </Layout>
  );
};

export default TagSearch;
