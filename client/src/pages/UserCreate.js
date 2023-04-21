import { FcGoogle } from "react-icons/fc";
import { AiFillGithub } from "react-icons/ai";
import styled from "styled-components";
import Header from "../components/common/Header";
import { useNavigate, useLocation } from "react-router-dom";

const UserCreateDesign = styled.div`
  background-color: #ececec;
  width: 100vw;
  height: 100vh;

  .createall {
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;
    box-sizing: border-box;
    input {
      padding: 9px;
    }

    .googlebox {
      background-color: white;
      border-radius: 3px;
      height: 40px;
      width: 350px;
      margin: 90px 20px 10px 20px;
      text-align: center;
      font-size: 23px;
      line-height: 36px;
      border-radius: 5px;

      border: 1px solid #cccccc;
      cursor: pointer;
    }
    .googlebox svg {
      margin-right: 2px;
      margin-top: 5px;
      font-size: 25px;
    }
    .githubbox {
      background-color: black;
      border-radius: 3px;
      height: 40px;
      width: 350px;
      color: white;
      cursor: pointer;
      margin: 20px 20px 0px 20px;
      text-align: center;
      font-size: 23px;
      line-height: 0px;
      border-radius: 5px;
    }
    .githubbox svg {
      margin-right: 2px;
      margin-top: 5px;
      font-size: 25px;
    }
    input {
      width: 400px;
      margin-top: 5px;
      padding: 0 1em;
      border: 0;
      height: 38px;
      border-radius: 5px;
    }

    .info {
      margin: 30px;
      .DisplayName {
        margin: 30px;
      }
      .Email {
        margin: 30px;
      }
      .Password {
        margin: 30px;
      }
    }
  }
`;

const UserCreate = () => {
  return (
    <UserCreateDesign>
      <Header></Header>
      <div className="createall">
        <div className="googlebox">
          <FcGoogle fontSize={25} />
          Sign up with Google
        </div>
        <div className="githubbox">
          <AiFillGithub />
          Sign up With Github
        </div>
        <div className="info">
          <div className="DisplayName">
            Display Name
            <br />
            <input type="text"></input>
          </div>
          <div className="Email">
            Email
            <br />
            <input type="email"></input>
          </div>
          <div className="Password">
            Password
            <br />
            <input type="password"></input>
          </div>
        </div>
      </div>
    </UserCreateDesign>
  );
};

export default UserCreate;
