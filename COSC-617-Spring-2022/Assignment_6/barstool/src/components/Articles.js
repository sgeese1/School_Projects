import React from "react";
import "../App.css"

function Articles(props) {

    const displayArticles = (props) => {
    const {articles} = props;

    if (articles.length > 0) {
    return(
        articles.map((article) => {
            return(
            <div className="article" key={article.id}>
                <img src={article.thumbnail.location + article.thumbnail.images.small} />
                <h3><a href={article.url}>{article.title}</a></h3>
                <h4><img src={article.author.avatar} width="50x50"/> Author: {article.author.name}, Commment Count = {article.comment_count}</h4>
            </div>
            )
        })
    )

} else {
    return(<h3>No articles found</h3>)
}
    }

    return(
        <>
       {displayArticles(props)} 
        </>
    )
}

export default Articles;