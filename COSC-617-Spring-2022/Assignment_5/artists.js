var cheerio = require('cheerio');
var nodemailer = require('nodemailer');
var axios = require('axios');
// var chalk = require('chalk');
var fs = require('fs');

if(process.argv.length < 3){
    throw Error("Must provide the name of an artist");
}

function get_credentials(){
   var cred_obj = JSON.parse(fs.readFileSync("credentials.json"));
   return cred_obj;
}

function artist_iterator() {
    var artists = process.argv.slice(2);
    var song_list = [];
    artists.forEach((element) => {
        song_list.concat(get_artists(element));
    })

    return song_list;
}

function artist_name_list() {
    var artists = process.argv.slice(2);
    var artist_list = artists.join(',');
    return artist_list;
}

async function get_artists(artist_name) {
    const artist_url = "http://www.popvortex.com/music/charts/top-rap-songs.php";
    var song_list = [];
    const song = {artist:"", title:""};

    const {data} = await axios.get(artist_url)

    var $ = cheerio.load(data);

    $('p.title-artist').each(function(i, element) {
        if($(this).text().includes(artist_name)){
            song.artist = $(element).children("em").text();
            song.title = $(element).children("cite").text();
            song_list.push(song);
        }
    });

    let message = await list_builder(song_list);
    send_email(song_list, message);
    console.log(message);
    
    return song_list;
    

}

function list_builder(song_list) {
    var message = `<ul style="list-style: none;">`;
    console.log(song_list);
    var flat_song_list = song_list.flat();
    flat_song_list.forEach((element) => {
            message += 
            `<li><strong>${element.artist}:</strong> <em>${element.title}</em></li>`
        });

    message += `</ul>`;
    return message;
}

function send_email(songs, message) {
    var creds = get_credentials();
    console.log(songs);
    if(songs.length == 0){
        throw Error("No songs found for artist");
    }
    let transporter = nodemailer.createTransport({
        host: 'smtp.gmail.com',
        port: 465,
        secure: true,
        auth: {
            user: creds.sender_email,
            pass: creds.sender_password
        }
    });
    let mailOptions = {
        from: creds.from,
        to: creds.to,
        subject: "Your artist(s) are: " + artist_name_list(),
        html: message
    };

    transporter.sendMail(mailOptions, (error, info) => {
        if(error) {
            return console.log(error);
        }
        console.log('Message sent');
    })
}

async function run_program() {
    await artist_iterator();
}

run_program();
