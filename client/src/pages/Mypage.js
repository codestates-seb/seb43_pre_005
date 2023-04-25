import Layout from "../components/common/Layout";
import styled from "styled-components";

const HeadContainer = styled.div`
  border: 1px solid black;
  /* display: flex;
  flex-direction: column; */
  width: 80vw;
  height: 80vh;

  .user-box {
    width: 80vw;
    height: 25vh;

    display: flex;

    > .picture-box {
      width: 40vw;

      > .name-box {
        border: 1px solid yellow;
        justify-content: center;
        align-items: center;
        display: flex;
        width: 40vw;
        height: 10vh;
        font-size: x-large;
      }

      > .explain-box {
        display: flex;
        align-items: center;
        justify-content: center;
        width: 40vw;
        height: 10vh;
        border: 1px solid pink;
      }
      > .button-box {
        display: flex;
        align-items: center;
        justify-content: center;
        button {
          border: none;
          border-radius: 10px;
          margin: 0.5rem;
          width: 6vw;
          height: 4vh;
          cursor: pointer;
        }
      }
    }
    .user-picture {
      width: 40vw;
      display: flex;
      align-items: center;
      justify-content: center;
      > .hamzzi {
        width: 20vw;
        height: 20vh;
        border-radius: 100px;
      }
    }
  }
`;

const ContentBox = styled.div`
  .second-box {
    width: 80vw;
    height: 25vh;
    border: 1px solid black;
    display: flex;

    > .status-box {
      width: 40vw;
      height: 25vh;
      border: 1px solid green;
      align-items: center;
      display: flex;
      justify-content: center;

      > .status-in {
        display: flex;
        align-items: center;
        justify-content: center;
        border: 1px solid gray;
        border-radius: 20px;
        width: 20vw;
        height: 10vh;
        font-size: large;
        word-spacing: 1rem;
        padding: 0.3rem;
      }
    }

    > .about-box {
      width: 40vw;
      height: 25vh;
      border: 1px solid green;
      align-items: center;
      display: flex;
      justify-content: center;
    }
  }
`;
const MyPage = () => {
  return (
    <Layout>
      <HeadContainer>
        <div className="user-box">
          <div className="picture-box">
            <div className="name-box">Digital - hamzzi</div>
            <div className="explain-box">i;m hamzziking</div>
            <div className="button-box">
              <button>회원정보수정</button>
              <button>회원탈퇴</button>
            </div>
          </div>
          <div className="user-picture">
            <img
              className="hamzzi"
              alt="hamster_picture"
              src="img/hamster.jpeg"
            />
          </div>
        </div>
        <ContentBox>
          <div className="second-box">
            <div className="status-box">
              <div className="status-in">
                answer : 0<br></br> questions: 0
              </div>
            </div>
            <div className="about-box">ABOUT</div>
          </div>
        </ContentBox>
      </HeadContainer>
    </Layout>
  );
};

export default MyPage;
