import { FcGoogle } from "react-icons/fc";
import { AiFillGithub } from "react-icons/ai";
import styled from "styled-components";
import Header from "../../components/common/Header";
import axios from "axios";

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

    .googlebox,
    .githubbox {
      border-radius: 3px;
      height: 40px;
      width: 350px;
      cursor: pointer;
      text-align: center;
      font-size: 23px;
      border-radius: 5px;
    }

    .googlebox {
      background-color: white;
      margin: 90px 20px 10px 20px;
      line-height: 36px;
      border: 1.3px solid #cccccc;
      &:hover {
        background-color: #f8f9fa;
      }
    }

    .githubbox {
      background-color: #212529;
      color: white;
      margin: 20px 20px 0px 20px;
      line-height: 0px;
      &:hover {
        background-color: black;
      }
    }
    .googlebox svg,
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
      &:focus {
        outline: none;
        box-shadow: 0 0 0 4px #d7f1fa;
        border: 1.4px solid #289aff;
      }
    }
    .info {
      margin: 30px;
      .DisplayName,
      .Email,
      .Password {
        margin: 20px;
      }
    }
    .logincenter {
      display: flex;
      align-items: center;
      justify-content: center;
      flex-direction: column;
      box-sizing: border-box;
    }
    .loginbutton {
      width: 420px;
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

const UserCreate = () => {
  const handleSubmit = async (e) => {
    e.preventDefault();
    const displayName = e.target.displayName.value;
    const email = e.target.email.value;
    const password = e.target.password.value;
    try {
      const response = await axios.post("http://localhost:8080/users/signup", {
        displayName,
        email,
        password,
      });
      console.log(response.data);
    } catch (error) {
      console.error(error);
    }
  };
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
          Sign up with Github
        </div>
        <div className="info">
          <div className="DisplayName">
            Display name
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
        <div className="logincenter">
          <button className="loginbutton" onClick={handleSubmit}>
            sign up
          </button>
        </div>
      </div>
    </UserCreateDesign>
  );
};

export default UserCreate;
