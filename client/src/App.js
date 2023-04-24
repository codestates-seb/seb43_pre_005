import { BrowserRouter, Routes, Route } from "react-router-dom";
import "./App.css";
import Home from "./pages/Home";
import Tags from "./pages/Tags";
import Users from "./pages/Users";
import Questions from "./pages/Questions";
import HomeWeek from "./pages/HomeWeek";
import HomeMonth from "./pages/HomeMonth";
import TagSearch from "./pages/TagSearch";
import TagSearch from "./pages/TagSearch";
import UserCreate from "./pages/UserCreate";
import UserLogin from "./pages/UserLogin";
import qsdummyData from "./data/qsdummyData";
import QuestionsRead from "./pages/QuestionsRead";
import QuestionCreate from "./pages/QuestionCreate";
import qsdummyData from "./data/qsdummyData";
import QuestionsRead from "./pages/QuestionsRead";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />}></Route>
        <Route path="/tab=week" element={<HomeWeek />}></Route>
        <Route path="/tab=month" element={<HomeMonth />}></Route>
        <Route path="/tags" element={<Tags />}></Route>
        <Route path="/users" element={<Users />}></Route>
        <Route path="/users/signup" element={<UserCreate />}></Route>
        <Route path="/users/login" element={<UserLogin />}></Route>
        <Route path="/questions" element={<Questions />}></Route>
        <Route path="/questions/create" element={<QuestionCreate />} />
        <Route path="/questions/tag_name" element={<TagSearch />}></Route>
        <Route
          path="/questions/:id"
          element={<QuestionsRead dummydata={qsdummyData} />}
        />
        <Route path="/questions/create" element={<QuestionCreate />} />
        <Route path="/questions/tag_name" element={<TagSearch />}></Route>
        <Route path="/questions/create" element={<QuestionCreate />} />
        <Route
          path="/questions/:id"
          element={<QuestionsRead dummydata={qsdummyData} />}
        />
        <Route path="/questions/tag_name" element={<TagSearch />}></Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
