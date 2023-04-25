import { FcGoogle } from "react-icons/fc";
import { AiFillGithub } from "react-icons/ai";
import styled from "styled-components";
import Header from "../../components/common/Header";
import logo from "../../assets/images/logo_notext.png";

const UserLoginDesgin = styled.div`
  background-color: #ececec;
  width: 100vw;
  height: 100vh;

  .logoimg {
    height: 150px;
    margin: auto;
    margin-top: 30px;
    margin-bottom: 2px;
    display: block;
  }

  .createall {
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;
    box-sizing: border-box;

    .googlebox,
    .githubbox {
      border-radius: 5px;
      height: 40px;
      width: 350px;
      text-align: center;
      font-size: 23px;
      cursor: pointer;
    }

    .googlebox {
      background-color: white;
      margin: 10px 20px 10px 20px;
      line-height: 36px;
      border: 1.3px solid #cccccc;
      &:hover {
        background-color: #f8f9fa;
      }
    }

    .githubbox {
      background-color: #212529;
      color: white;
      margin: 10px 20px 30px 20px;
      line-height: 0px;
      &:hover {
        background-color: black;
      }
    }
    .githubbox svg,
    .googlebox svg {
      margin-right: 2px;
      margin-top: 5px;
      font-size: 25px;
    }

    .logininfo {
      width: 450px;
      height: 270px;
      border: 1.5px solid #5f5f5f;
      background-color: white;
      border-radius: 5px;
    }
    input {
      width: 370px;
      margin-top: 5px;
      padding: 0 1em;
      border: 1.4px solid #5f5f5f;
      height: 38px;
      border-radius: 5px;
      &:focus {
        outline: none;
        box-shadow: 0 0 0 4px #d7f1fa;
        border: 1.4px solid #289aff;
      }
    }
    .Email,
    .Password {
      margin: 30px;
    }
    .logincenter {
      display: flex;
      align-items: center;
      justify-content: center;
      flex-direction: column;
      box-sizing: border-box;
    }
    .loginbutton {
      width: 395px;
      height: 40px;
      border-radius: 5px;
      background-color: #1ea4ff;
      font-size: 15px;
      color: white;
      border: none;
      cursor: pointer;
      &:hover {
        background-color: #003399;
      }
    }
  }
`;

const UserLogin = () => {
  return (
    <UserLoginDesgin>
      <Header></Header>
      <img className="logoimg" alt="logo" src={logo} />
      <div className="createall">
        <div className="googlebox">
          <FcGoogle fontSize={25} />
          log in with Google
        </div>
        <div className="githubbox">
          <AiFillGithub />
          log in with Github
        </div>
        <div className="logininfo">
          <div className="loginbox">
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
          <div className="logincenter">
            <button className="loginbutton">log in</button>
          </div>
        </div>
      </div>
    </UserLoginDesgin>
  );
};

export default UserLogin;
