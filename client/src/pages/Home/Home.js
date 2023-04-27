import Layout from "../../components/common/Layout";
import styled from "styled-components";
import qsdummydata from "../../data/qsdummyData";
import { useState, useEffect } from "react";
import Question from "../../components/Question";
import { useNavigate, useLocation } from "react-router-dom";
import axios from "axios";

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
      background-color: #1e82ff;
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
      background-color: #7878ff;
    }

    button:not(:first-child):hover {
      opacity: 0.8;
      background-color: #a696cd;
    }
  }

  .questions-box {
    width: 60vw;
    margin-left: 2rem;
    margin-top: 1rem;
  }
`;

const Home = () => {
  // const [questions, setQuestions] = useState(qsdummydata);
  const navigate = useNavigate();
  const [questions, setQuestions] = useState([]);

  useEffect(() => {
    axios
      .get(
        "http://seb-pre-project-005.s3-website.ap-northeast-2.amazonaws.com/"
      )
      .then((response) => {
        setQuestions(response.data);
      })
      .catch((error) => {
        console.error(error);
      });
  }, []);

  const selectMenuHandler = (path) => {
    navigate(path);
  };

  return (
    <Layout>
      <HeadContainer>
        <div className="header-content">
          Top Questions
          <button
            onClick={() =>
              selectMenuHandler(
                "http://seb-pre-project-005.s3-website.ap-northeast-2.amazonaws.com/questions/ask"
              )
            }
          >
            Ask Question
          </button>
        </div>
        <div className="button-box">
          <button>Hot</button>
          <button
            onClick={() => {
              window.location.href =
                "http://seb-pre-project-005.s3-website.ap-northeast-2.amazonaws.com?tab=week";
            }}
          >
            Week
          </button>
          <button
            onClick={() => {
              window.location.href =
                "http://seb-pre-project-005.s3-website.ap-northeast-2.amazonaws.com/?tab=month";
            }}
          >
            Month
          </button>
        </div>

        <div className="questions-box">
          {questions.map((el) => (
            <Question key={el.id} question={el}></Question>
          ))}
        </div>
      </HeadContainer>
    </Layout>
  );
};

export default Home;
