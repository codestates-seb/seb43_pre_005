import Layout from "../../components/common/Layout";
import styled from "styled-components";
import qsdummydata from "../../data/qsdummyData";
import { useState, useEffect } from "react";
import Question from "../../components/Question";
import axios from "axios";
import { useNavigate, useLocation } from "react-router-dom";

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
    margin-left: 3rem;
    margin-top: 1rem;
  }

  .button-box {
    display: flex;
    justify-content: right;
    margin-right: 5rem;
    button {
      color: white;
      border: none;
      padding: 0.5rem 1rem;
      font-size: 1.2rem;
      margin-left: 0.2rem;
      border-radius: 5px;
      cursor: pointer;
    }
    button:first-child {
      &:hover {
        opacity: 0.8;
        background-color: #7878ff;
      }
    }

    button:nth-child(2) {
      background-color: #7878ff;
    }

    button:nth-child(3) {
      &:hover {
        opacity: 0.8;
        background-color: #7878ff;
      }
    }
  }

  .questions-box {
    width: 60vw;
    margin-left: 2rem;
    margin-top: 1rem;
  }
`;

const HomeWeek = () => {
  // const [questions, setQuestions] = useState(qsdummydata);
  const [questions, setQuestions] = useState([]);
  const navigate = useNavigate();

  const selectMenuHandler = (path) => {
    navigate(path);
  };

  useEffect(() => {
    axios
      .get("http://ec2-3-34-134-67.ap-northeast-2.compute.amazonaws.com:8080/questions")
      .then((response) => {
        setQuestions(response.data);
      })
      .catch((error) => {
        console.error(error);
      });
  }, []);

  return (
    <Layout>
      <HeadContainer>
        <div className="header-content">
          Top Questions
          <button onClick={() => selectMenuHandler("questions/ask")}>
            Ask Question
          </button>
        </div>
        <div className="button-box">
          <button
            onClick={() => {
              window.location.href = "http://ec2-3-34-134-67.ap-northeast-2.compute.amazonaws.com:8080/";
            }}
          >
            Hot
          </button>
          <button>Week</button>
          <button
            onClick={() => {
              window.location.href = "http://ec2-3-34-134-67.ap-northeast-2.compute.amazonaws.com:8080?tab=month";
            }}
          >
            Month
          </button>
        </div>

        <div className="questions-box">
          {questions.map((el) => (
            <Question key={el.id} question={el} />
          ))}
        </div>
      </HeadContainer>
    </Layout>
  );
};

export default HomeWeek;
