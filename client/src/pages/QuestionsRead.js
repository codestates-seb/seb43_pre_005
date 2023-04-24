import Layout from "../components/common/Layout";
import styled from "styled-components";
import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import QuestionButton from "./QuestionButton";
import userimg from "../assets/images/logo_notext.png";

const QuestionsReadDesign = styled.div`
  width: calc(100vw - 167px);
  margin-left: 20px;

  hr {
    border: 0;
    height: 2px;
    background: black;
  }

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
  }

  .questiontitle {
    font-size: 27px;
  }
  .infobox {
    display: flex;
    justify-content: space-between;
    align-items: center;
    width: 100%;
    margin: 0 auto;
    margin-bottom: 1rem;

    .questioncreatedat,
    .questionlook {
      width: 10%;
      color: #777777;
    }
    .spacer {
      width: 55%;
    }
    .questionretouch,
    .questiondelete {
      width: 8%;
    }
    .questiondelete {
      margin-right: 2rem;
    }
    span {
      color: black;
    }
  }

  .questioncontent {
    box: border-box;
    width: 80vw;
    height: 260px;
    display: grid;
    grid-template-columns: 5% 90%;
    grid-template-rows: 1fr 1fr 1fr;
    grid-gap: 0px;
    margin-top: 20px;
    border: 1.3px solid #cccccc;

    .questiontags {
      grid-column: 2 / 3;
      grid-row: 3 / 4;
    }
    .questionperson {
      grid-column: 5 / 8;
      grid-row: 3 / 4;
    }
    .userimg {
      grid-column: 4 / 5;
      grid-row: 3 / 4;
      width: 20px;
      height: 20px;
      border: none;
    }
  }
  .questionanswer {
    box: border-box;
    width: 80vw;
    height: 260px;
    display: flex;
    margin-top: 20px;
    border: 1.3px solid #cccccc;
  }

  .questionanswercreated {
    box: border-box;
    width: 80vw;
    height: 260px;
    display: flex;
    margin-top: 20px;
    border: 1.3px solid #cccccc;
  }
`;

const Content = styled.div`
  width: 100%;
  grid-column: 2 / 20;
  grid-row: 1 / 2;
`;

const QuestionButtonDesign = styled(QuestionButton)`
  grid-column: 1 / 2;
  grid-row: 1 / 2;
`;

function QuestionsRead({ dummydata }) {
  const { id } = useParams();
  const question = dummydata.find((q) => q.id === parseInt(id));

  return (
    <Layout>
      <QuestionsReadDesign>
        <h2 className="questiontitle">{question.title}</h2>
        <div className="infobox">
          <div className="questioncreatedat">
            Asked <span>{question.createdAt}</span>
          </div>
          <div className="questionlook">
            Viewed <span>{question.look} times</span>
          </div>
          <div class="spacer"></div>
          <button className="questionretouch">질문수정</button>
          <button className="questiondelete">질문삭제</button>
        </div>
        <hr></hr>
        <div className="questioncontent">
          <QuestionButtonDesign />
          <Content>{question.content}</Content>
          <div className="questiontags">{question.tags}</div>
          <img className="userimg" src={userimg} alt="userimg"></img>
          <div className="questionperson">{question.person}</div>
        </div>
        <div className="questionanswer">
          <QuestionButtonDesign />
          <div>alsjkdaodisjaosdi</div>
        </div>
        <div className="questionanswercreated">
          <div>asd</div>

          <button className="questiondelete">답변등록</button>
        </div>
      </QuestionsReadDesign>
    </Layout>
  );
}

export default QuestionsRead;
