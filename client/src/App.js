import { BrowserRouter, Routes, Route } from "react-router-dom";
import "./App.css";
import Home from "./pages/Home";
import Tags from "./pages/Tags";
import Users from "./pages/Users";
import Questions from "./pages/Questions";
import HomeWeek from "./pages/HomeWeek";
import HomeMonth from "./pages/HomeMonth";
import TagSearch from "./pages/TagSearch";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />}></Route>
        <Route path="/tab=week" element={<HomeWeek />}></Route>
        <Route path="/tab=month" element={<HomeMonth />}></Route>
        <Route path="/tags" element={<Tags />}></Route>
        <Route path="/users" element={<Users />}></Route>
        <Route path="/questions" element={<Questions />}></Route>
        <Route path="/questions/tag_name" element={<TagSearch />}></Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
