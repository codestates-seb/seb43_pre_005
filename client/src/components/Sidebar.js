import styled from "styled-components";
import { useState } from "react";
import { IoEarth } from "react-icons/io5";

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
  const [currentTab, setCurrentTab] = useState(0);

  const selectMenuHandler = (index) => {
    setCurrentTab(index);
  };

  return (
    <LeftSidebar>
      <ul className="sidebar-container">
        <li
          className={currentTab === 0 ? "sidemenu-clicked" : ""}
          onClick={() => selectMenuHandler(0)}
        >
          <span> Home</span>
        </li>
        <li
          className={currentTab === 1 ? "sidemenu-clicked" : ""}
          onClick={() => selectMenuHandler(1)}
        >
          <span>Question</span>
          <IoEarth />
        </li>
        <li
          className={currentTab === 2 ? "sidemenu-clicked" : ""}
          onClick={() => selectMenuHandler(2)}
        >
          <span>Tags</span>
        </li>
        <li
          className={currentTab === 3 ? "sidemenu-clicked" : ""}
          onClick={() => selectMenuHandler(3)}
        >
          <span>Users</span>
        </li>
      </ul>
    </LeftSidebar>
  );
};

export default Sidebar;
