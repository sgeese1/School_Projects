import logo from './logo.svg';
import './App.css';
import axios from 'axios';

var state = {
  articles: []
};

axios.get(`https://www.jalirani.com/files/barstool.json`)
      .then(res => {
        const articles = res.data;
        this.setState({ articles });
      })

function App() {

  return (
    <div>
      <ul>
      {
          this.state.articles
            .map(article =>
              <li key={article.id}>
                <div>
                  <img src={article.location + article.thumbnail.small}>Test</img>
                </div>
              </li>
            )
        }
      </ul>
    </div>
  );
}

export default App;
