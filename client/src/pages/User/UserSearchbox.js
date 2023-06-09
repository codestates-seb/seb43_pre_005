import React, { useState } from "react";
import styled from "styled-components";
import logoimg from "../../assets/images/logo_notext.png";
import { Link } from "react-router-dom";
import useDataFetch from "../../customhook/useDataFetch";

const SearchBoxDesign = styled.div`
  .userdata {
    padding: 6px 7px 8px 8px;
    color: #6a737c;
    display: flex;
    align-items: center;
    flex-direction: row;
    flex-wrap: wrap;
    .userbox {
      margin: 7px;
      padding: 16px 7px 8px 8px;
      border: 1px solid #4a4e51;
      border-radius: 3px;
      box-sizing: border-box;
      width: 270px;
      height: 130px;
      .userimg {
        float: left;
        width: 48px;
        height: 48px;

        .userpicture {
          width: 48px;
          height: 48px;
          border-radius: 2px;
          overflow: hidden;
          padding: 0;
          color: #0077cc;
          text-decoration: none;
          margin: 0 auto;
        }
      }
      .userinfo {
        margin-left: 9px;
        width: calc(100% - 64px);
        line-height: 1.3;
        float: left;
        display: block;

        .username {
          color: #5d52ee;
          text-decoration: none;
          font-size: 20px;
        }
      }
    }
  }
`;

const SearchBox = () => {
  const [searchTerm, setSearchTerm] = useState("");

  const handleChange = (e) => {
    setSearchTerm(e.target.value);
  };

  const { data, isLoading, error } = useDataFetch(
    "http://ec2-3-34-134-67.ap-northeast-2.compute.amazonaws.com:8080/users/group",{},{withCredentials:true}
  );

  if (isLoading) return <div>Loading...</div>;
  if (error) return <div>Error: {error.message}</div>;

  const filteredData =
    data &&
    data.filter((item) =>
      item.name
        .replace(" ", "")
        .toLocaleLowerCase()
        .includes(searchTerm.toLocaleLowerCase().replace(" ", ""))
    );

  return (
    <SearchBoxDesign>
      <div>
        <input type="text" onChange={handleChange} placeholder="search" />
        <div className="userdata">
          {filteredData &&
            filteredData.map((ele) => {
              return (
                <div className="userbox">
                  <div className="userimg">
                    <Link to={`/users/${ele.id}`}>
                      <img
                        className="userpicture"
                        src={logoimg}
                        alt="profile"
                      ></img>
                    </Link>
                  </div>
                  <div className="userinfo">
                    <div className="username">{ele.displayName}</div>
                  </div>
                </div>
              );
            })}
        </div>
      </div>
    </SearchBoxDesign>
  );
};

export default SearchBox;
