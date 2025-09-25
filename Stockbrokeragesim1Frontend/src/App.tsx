import { useState } from 'react'
import reactLogo from './assets/react.svg'
import './App.css'
import {DropdownItem} from "react-bootstrap";
import { Route, Routes } from "react-router-dom";
import PostOrder from "./pages/stockOrder/PostOrder";
import Dashboard from "./pages/dashboard/Dashboard";
import NoMatch from "./pages/noMatch/NoMatch";

function App() {
  const [count, setCount] = useState(0);

    return (
        <>
            <Routes>
                <Route path="*" element={<Dashboard/>} />
                <Route path="/" element={<PostOrder/>} />
                <Route path="*" element={<NoMatch/>} />
            </Routes>
        </>
      /*
      <div className="App">
      <div>
        <a href="https://vitejs.dev" target="_blank">
          <img src="/vite.svg" className="logo" alt="Vite logo" />
        </a>
        <a href="https://reactjs.org" target="_blank">
          <img src={reactLogo} className="logo react" alt="React logo" />
        </a>
      </div>
      <h1>Vite + React</h1>
      <div className="card">
        <button onClick={() => setCount((count) => count + 1)}>
          count is {count}
        </button>
        <p>
          Edit <code>src/App.tsx</code> and save to test HMR
        </p>
      </div>
      <p className="read-the-docs">
        Click on the Vite and React logos to learn more
      </p>
    </div>
       */
  );
}

/*
function DropdwonMenu() {

    function DropDownItem(props) {
        return (
            <a href
        )
    }

    return (
        <div className="dropdown">

        </div>
    );
}
 */

export default App
