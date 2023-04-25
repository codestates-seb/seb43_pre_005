import Layout from "../../components/common/Layout";
import styled from "styled-components";
import Question from "../../components/Question";
import qsdummydata from "../../data/qsdummyData";
import QuestionCreate from "./QuestionCreate";
import { useNavigate, useLocation } from "react-router-dom";
import { useState, useEffect } from "react";
import useDataFetch from "../../customhook/useDataFetch";

const HeadContainer = styled.div`
  .header-content {
    width: 50vw;
    height: 10vh;
    display: flex;
    justify-content: space-between;
    font-size: 2rem;
    align-items: center;
    margin-left: 5rem;

    button {
      height: 5vh;
      background-color: #0995ff;
      color: white;
      border: none;
      padding: 0.5rem 1rem;
      font-size: 1.2rem;
      margin-left: 1rem;
      border-radius: 5px;
      cursor: pointer;

      &:hover {
        opacity: 0.8;
        background-color: #1e82ff;
      }
    }
  }

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

const Questions = () => {
  const [questions, setQuestions] = useState(qsdummydata);
  const navigate = useNavigate();

  const selectMenuHandler = (path) => {
    navigate(path);
  };

  const { data, isLoading, error } = useDataFetch(
    "http://localhost:3001/qsdummyData"
  );

  if (isLoading) return <div>Loading...</div>;
  if (error) return <div>Error: {error.message}</div>;

  return (
    <Layout>
      <HeadContainer>
        <div className="header-content">
          All Questions
          <button onClick={() => selectMenuHandler("/questions/create")}>
            Ask Question
          </button>
        </div>
        <div className="questions-number">{qsdummydata.length} questions</div>
        <div className="questions-box">
          {data && data.map((el) => <Question key={el.id} question={el} />)}
        </div>
      </HeadContainer>
    </Layout>
  );
};

export default Questions;
