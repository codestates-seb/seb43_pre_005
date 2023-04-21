import styled from "styled-components";
import { useNavigate, useLocation } from "react-router-dom";
import { IoEarth } from "react-icons/io5";

export const Menu = {
  home: "/",
  questions: "/questions",
  tags: "/tags",
  users: "/users",
};

const LeftSidebar = styled.nav`
  padding-inline-start: 1px;
  box-sizing: border-box;
  margin: 8px;
  display: block;
  width: 164px;
  height: 100vh;
  margin: 0;
  padding: 0;
  border: 0;
  font-size: 100%;
  font: inherit;
  vertical-align: baseline;

  .sidebar-container {
    > li {
      padding: 20px 2px 20px 2px;
      list-style-type: none;
      text-align: left;
      font-size: 20px;
      line-height: 20px;
      cursor: pointer;
    }
    .sidemenu-clicked {
      font-weight: bold;
      border-right: 3px solid rgb(244, 130, 37);
      background-color: #eaeaea;
    }
  }
`;

const Sidebar = () => {
  const { pathname } = useLocation();
  const navigate = useNavigate();
  const currentTab = pathname;

  const selectMenuHandler = (path) => {
    navigate(path);
  };

  return (
    <LeftSidebar>
      <ul className="sidebar-container">
        <li
          className={currentTab === Menu.home ? "sidemenu-clicked" : ""}
          onClick={() => selectMenuHandler(Menu.home)}
        >
          <span> Home</span>
        </li>
        <li
          className={currentTab === Menu.questions ? "sidemenu-clicked" : ""}
          onClick={() => selectMenuHandler(Menu.questions)}
        >
          <span>Question</span>
          <IoEarth />
        </li>
        <li
          className={currentTab === Menu.tags ? "sidemenu-clicked" : ""}
          onClick={() => selectMenuHandler(Menu.tags)}
        >
          <span>Tags</span>
        </li>
        <li
          className={currentTab === Menu.users ? "sidemenu-clicked" : ""}
          onClick={() => selectMenuHandler(Menu.users)}
        >
          <span>Users</span>
        </li>
      </ul>
    </LeftSidebar>
  );
};

export default Sidebar;