import cheerio from 'cheerio';
import nodemailer from 'nodemailer';
import axios from 'axios';
import chalk from 'chalk';
import fs from 'fs';

if(process.argv[2] == undefined){
    throw Error(chalk.red("Must provide the name of an artist"));
}

function get_credentials(){
   var cred_obj = JSON.parse(fs.readFileSync("credentials.json"));
   return cred_obj;
}

async function get_artists() {
    const artist_url = "http://www.popvortex.com/music/charts/top-rap-songs.php";
    var each_artist = process.argv.slice(2);
    console.log(each_artist);
    var song_list = [];
    each_artist.forEach(async function(element) {
    var artist_name = await element;
    console.log(artist_name);
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

});
    console.log(song_list);
    return song_list;
}

async function send_email() {
    var creds = get_credentials();
    var songs = await get_artists();

    if(songs.length == 0){
        throw Error(chalk.red("No songs found for artist"));
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
        subject: "Your artist(s) are: " + process.argv[2],
        text: JSON.stringify(Object.values(songs),null,'\t')
    };

    transporter.sendMail(mailOptions, (error, info) => {
        if(error) {
            return console.log(error);
        }
        console.log('Message sent');
    })
}

send_email();

//get_artists();




