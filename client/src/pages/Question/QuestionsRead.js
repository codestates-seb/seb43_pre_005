import Layout from "../../components/common/Layout";
import styled from "styled-components";
import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import QuestionButton from "./QuestionButton";
import userimg from "../../assets/images/logo_notext.png";
import axios from "axios";
import { useNavigate } from "react-router-dom";

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
    grid-gap: 0px;
    margin-top: 20px;
    border: 1.3px solid #cccccc;

    .questiontags {
      grid-column: 2 / 3;
      grid-row: 3 / 4;
    }
    .questionperson {
      grid-column: 3 / 4;
      grid-row: 3 / 4;
    }

    .userimg {
      grid-column: 4 / 5;
      grid-row: 3 / 4;
      width: 20px;
      height: 20px;
      border: none;
    }
    .justgrid {
      grid-column: 5 / 6;
      grid-row: 3 / 4;
    }
  }
  .questionanswer {
    box-sizing: border-box;
    width: 80vw;
    height: 260px;
    display: flex;
    margin-top: 20px;
    border: 1.3px solid #cccccc;

    .content-box {
      display: flex;
      justify-content: left;
      align-items: left;
      margin-left: 3rem;
      width: 60vw;
      padding: 1rem;
      border: 1px solid black;
    }
  }

  .questionanswercreated {
    box: border-box;
    width: 80vw;
    height: 260px;
    display: flex;
    flex-direction: wrap;
    margin-top: 20px;
    border: 1.3px solid #cccccc;
  }
  .anscre {
    width: 80%;
    height: 50%;
    margin-left: 5px;
    margin-top: 5px;
    border: none;
  }
  .questiondelete {
    margin-top: 200px;
    margin-right: 40px;
  }
`;

const Content = styled.div`
  grid-column: 2 / 20;
  grid-row: 1 / 2;

  textarea {
    border: none;
    width: 700px;
    height: 200px;
    font-size: x-large;
    word-spacing: 1rem;
  }
`;

const QuestionButtonDesign = styled(QuestionButton)`
  grid-column: 1 / 2;
  grid-row: 1 / 2;
`;

function QuestionsRead() {
  const { id } = useParams();
  const navigate = useNavigate(); // useNavigate hook 추가
  //답변을 작성할 input에 들어갈 msg
  const [msg, setMsg] = useState("");
  //답변이 등록될 div
  const [content, setContent] = useState([]);
  const [data, setData] = useState([]);

  const [answerId, setAnswerId] = useState(1);
  const [edited, setEdited] = useState(false);
  const [editInputValue, setEditInputValue] = useState(data && data.content);

  const countUp = () => {
    setAnswerId(answerId + 1);
  };

  const onEditBtn = () => {
    setEdited(!edited);
  };

  const onSaveBtn = () => {
    setEdited(!edited);
    handleEditValue(editInputValue);
  };

  const handleEditValue = (value) => {
    setEditInputValue(value);
  };
  useEffect(() => {
    axios
      .get(`http://localhost:3001/qsdummydata/${id}`)
      .then((response) => setData(response.data))
      .catch((error) => console.log(error));
  }, [id]);
  //질문삭제 부분 API
  const handleDelete = async () => {
    try {
      const response = await axios.delete(
        `http://localhost:8080/questions/${id}`
      );
      if (response.status === 200) {
        navigate("/");
      }
    } catch (error) {
      console.error(error);
    }
  };
  //답변삭제 부분 API
  const handleAnswerDelete = async (questionId, answerId) => {
    try {
      const response = await axios.delete(
        `http://localhost:3001/questions/${questionId}/answers/${answerId}`
      );
      if (response.status === 200) {
        navigate("/");
      }
    } catch (error) {
      console.error(error);
    }
  };
  //eventhandler를 눌렀을 때, content의 내용을 수정할 수 있게 focus를 맞춰야함
  //question.content의 내용을 수정 한 후
  //수정한 내용을 patch로 보내야함

  const handleEditPatch = () => {};

  const handleonChange = (e) => {
    setMsg(e.target.value);
    console.log(e.target.value);
  };

  const handleButtonClick = async (e) => {
    try {
      const response = await axios.get(
        `http://localhost:3001/qsdummydata/${id}`
      );
      const data = response.data;

      data.answers.push(msg);

      const patchResponse = await axios.patch(
        `http://localhost:3001/qsdummydata/${id}`,
        {
          answers: data.answers,
        }
      );
      countUp();
      setContent(patchResponse.data.answers);
      setMsg("");
      console.log(patchResponse);
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <Layout>
      <QuestionsReadDesign>
        <h2 className="questiontitle">{data && data.title}</h2>
        <div className="infobox">
          <div className="questioncreatedat">
            Asked <span>{data && data.createdAt}</span>
          </div>
          <div className="questionlook">
            Viewed <span>{data && data.look} times</span>
          </div>
          <div class="spacer"></div>
          {edited ? (
            <button onClick={onSaveBtn}>확인</button>
          ) : (
            <button className="questionretouch" onClick={onEditBtn}>
              질문수정
            </button>
          )}
          <button className="questiondelete" onClick={handleDelete}>
            질문삭제
          </button>
        </div>
        <hr></hr>
        <div className="questioncontent">
          <QuestionButtonDesign />
          <Content>
            <textarea
              value={editInputValue}
              onChange={(e) => {
                if (edited) {
                  setEditInputValue(e.target.value);
                }
              }}
            />
          </Content>
          <div className="questiontags">tags</div>
          <img className="userimg" src={userimg} alt="userimg"></img>
          <div className="questionperson">{data && data.person}</div>
          <div className="justgrid">ss</div>
          <div>
            {data &&
              data.answers &&
              data.answers.map((answer, index) => (
                <div key={index}>{answer}</div>
              ))}
          </div>
        </div>

        {content.map((el, index) => (
          <div className="questionanswer" id={id}>
            {el}
            <button onClick={() => handleAnswerDelete(id, index)}>
              {index}
              delete
            </button>
          </div>
        ))}
        <div className="questionanswercreated">
          <input
            className="anscre"
            type="text"
            value={msg}
            onChange={handleonChange}
          ></input>
          <button className="questiondelete" onClick={handleButtonClick}>
            답변등록
          </button>
        </div>
      </QuestionsReadDesign>
    </Layout>
  );
}

export default QuestionsRead;
