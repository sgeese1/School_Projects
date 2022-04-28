var express = require('express');
const { graphqlHTTP } = require('express-graphql');
var { buildSchema } = require('graphql');
var fs = require('fs');

var fighterData = JSON.parse(fs.readFileSync("./fighters.json"));

var schema = buildSchema(`
  type Query {
      fighter(name: String!): Fighter
      fighters(weight_class: String): [Fighter]
  }
type Fighter {
      name: String
      nickname: String
      wins: Int
      losses: Int
      height: String
      weight_class: String
      location: String
  }
`);

var getFighter = function(args) { 
    var name = args.name;
    return fighterData.filter(fighter => {
        return fighter.name == name;
    })[0];
}

var getFightersByWeightClass = function(args) {
    if (args.weight_class) {
        var weight_class = args.weight_class;
        return fighterData.filter(fighter => fighter.weight_class === weight_class);
    } else {
        return fighterData;
    }
}

var root = {
    fighter: getFighter,
    fighters: getFightersByWeightClass
};

var app = express();

app.use('/testgraphql', graphqlHTTP({
    schema: schema,
    rootValue: root,
    graphiql: true
}));

app.listen(3000, () => console.log('Express GraphQL Server Now Running On localhost:3000/testgraphql'));