import styled from "styled-components";
import { ReactComponent as FooterLogo } from "../assets/images/footerlogo.svg";
import { Link } from "react-router-dom";

const FooterContainer = styled.footer`
  width: 100%;
  background-color: hsl(210, 8%, 15%);
  color: hsl(210, 8%, 60%);
  .footer-container {
    display: flex;
    justify-content: space-between;
    margin: 0;
    padding: 32px 12px 12px;
    width: 100%;
    max-width: 1500px;
    margin: 0 auto;
    .foot-logo {
      flex: 0 0 64px;
      margin: 32px 0 32px;
    }
    .bot-menu-container {
      display: flex;
      flex: 2 1 auto;
      flex-direction: row;
      flex-wrap: wrap;
      > ul {
        flex: 1 0 auto;
        padding: 0 12px 24px 0;
        > h5 {
          margin: 0 0 12px;
          color: hsl(210, 8%, 75%);
        }
        > li {
          line-height: 2;
          font-size: 13px;
        }
      }
    }
  }
`;

const Footer = () => {
  return (
    <FooterContainer>
      <ul className="footer-container">
        <li className="foot-logo">
          <Link to="/">
            <FooterLogo />
          </Link>
        </li>
        <li className="bot-menu-container">
          <ul>
            <h5>FE 염도경</h5>
            <li>이메일: kangs19951@gmail.com</li>
            <li>깃허브: yeomdogyeong</li>
            <li>블로그: https://velog.io/@ehrud1031 </li>
          </ul>
          <ul>
            <h5>FE 오준석</h5>
            <li>이메일: dhwnatjr678@gmail.com</li>
            <li>깃허브: JS2L</li>
            <li>블로그: https://velog.io/@dhwnatjr678 </li>
          </ul>
          <ul>
            <h5>FE 김성수</h5>
            <li>이메일: hamtoli5@gmail.com</li>
            <li>깃허브: ggggggggithub</li>
            <li>블로그: https://velog.io/@noway </li>
          </ul>
          <ul>
            <h5>BE 안윤아</h5>
            <li>이메일: waterlove1439@navr.com</li>
            <li>깃허브: digital-hamster</li>
            <li>블로그: https://clover-hamster.tistory.com</li>
          </ul>
          <ul>
            <h5>BE 진하늘</h5>
            <li>이메일: yunide2@naver.com</li>
            <li>깃허브: mewluee</li>
            <li>블로그: https://ld-luee.tistory.com/</li>
          </ul>
          <ul>
            <h5>BE 조만기</h5>
            <li>이메일: dokeycho@gmail.com</li>
            <li>깃허브: Sniij</li>
            <li>블로그: https://sniij.tistory.com</li>
          </ul>
        </li>
      </ul>
    </FooterContainer>
  );
};

export default Footer;
