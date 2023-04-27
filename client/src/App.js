import { BrowserRouter, Routes, Route } from "react-router-dom";
import "./App.css";
import Home from "./pages/Home/Home";
import Tags from "./pages/Tag/Tags";
import Users from "./pages/User/Users";
import Questions from "./pages/Question/Questions";
import HomeWeek from "./pages/Home/HomeWeek";
import HomeMonth from "./pages/Home/HomeMonth";
import TagSearch from "./pages/Tag/TagSearch";
import UserCreate from "./pages/User/UserCreate";
import UserLogin from "./pages/User/UserLogin";
import qsdummyData from "./data/qsdummyData";
import QuestionsRead from "./pages/Question/QuestionsRead";
import QuestionCreate from "./pages/Question/QuestionCreate";
import MyPage from "./pages/User/Mypage";
import SearchResult from "./components/common/SearchResult";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />}></Route>
        <Route path="/tab=week" element={<HomeWeek />}></Route>
        <Route path="/tab=month" element={<HomeMonth />}></Route>
        <Route path="/stackOverflow/tags" element={<Tags />}></Route>
        <Route path="/users" element={<Users />}></Route>
        <Route path="/users/signup" element={<UserCreate />}></Route>
        <Route path="/users/login" element={<UserLogin />}></Route>
        <Route path="/questions" element={<Questions />}></Route>
        <Route path="/questions/:id" element={<QuestionsRead />} />
        <Route path="/questions/ask" element={<QuestionCreate />} />

        <Route path="/members" element={<MyPage />} />

        <Route path="/questions/tag_name" element={<TagSearch />}></Route>
        <Route path="/search" element={<SearchResult />}></Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
