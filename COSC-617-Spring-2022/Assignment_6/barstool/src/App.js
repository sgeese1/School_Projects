import React, {useState, useEffect} from 'react';
import './App.css';
import Articles from "./components/Articles";
import axios from 'axios';

function App() {

  const [articles, getArticles] = useState('');

  const getAllArticles = () => { axios.get(`https://www.jalirani.com/files/barstool.json`)
        .then(res => {
          const allArticles = res.data;
          getArticles(allArticles);
        });
      }

      useEffect(() => {
        getAllArticles();
      }, []);

      console.log(articles);
  return (
    <div className="App">
      <Articles  articles={articles}/>
    </div>
  );
}

export default App;
