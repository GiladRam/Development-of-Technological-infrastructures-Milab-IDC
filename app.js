const MongoClient = require('mongodb').MongoClient;
const mongoUrl = 'mongodb://giladram:giladram@ds259897.mlab.com:59897/exercise11';
const express = require('express');
const ObjectId = require('mongodb').ObjectId;
const app = express();
let port = process.env.PORT || 5000;
// create application/json parser 
let BodyParser = require('body-parser');
let jsonBodyParser = BodyParser.json();
let urlParser = BodyParser.urlencoded({ extended: true });
let validator = require('jsonschema').Validator;
let jsonValidator = new validator();
connectToMongoDB()


app.get('/song', urlParser, function(req, res){
	console.log(req.query)
	let query = req.query
	if(req.query.name){
		let name = query.name
		console.log(name)
		readSongs('name', name, res)
	}
	else if(req.query.artist)
	{
		let artist = query.artist
		readSongs('artist', artist, res)
	}
	else if(req.query.genre)
	{
		let genre = query.genre
		readSongs('genre', genre, res)
	}
	else
	{
		res.sendStatus(400)	
	}
});


app.post('/addSong', jsonBodyParser, function(req, res){
	if (!req.body)
	{
		return res.sendStatus(400)
	}
	else
	{
		let body = req.body
		let inputValidation = jsonValidator.validate(body, addSongSchema)
		console.log(inputValidation)
		if (inputValidation.valid){
			addSong(body, res)
		}
		else
		{
			res.send(inputValidation.errors)
		}
	}

});


app.put('/updateSong', jsonBodyParser, function(req, res){
	if(!req.body)
	{
		return res.sendStatus(400)
	}
	else
	{
		let body = req.body
		let inputValidation = jsonValidator.validate({body: updateSongSchema}, [addSongSchema])
		console.log(inputValidation)
		if(inputValidation.valid){
			let id = req.body.id
			let song = req.body.song
			console.log(song)
			console.log('update song - %s %s', id, song)
			updateSong(id, song, res)	
		}
		 
	}
});


app.delete('/delete', jsonBodyParser, function(req, res){
	if(!req.body)
	{
		return res.sendStatus(400)
	}
	else
	{
		let body = req.body
		let inputValidation = jsonValidator.validate(body, deleteSongSchema)
		console.log(inputValidation)
		if(inputValidation.valid){
			let id = req.body.id
			console.log('delete song id - %s', id)
			deleteSong(id, res)	
		}
		 
	}
});

app.listen(port, function() {
	console.log('The app is running on http://localhost:' + port);
});


function connectToMongoDB(){
	MongoClient.connect(mongoUrl, (err, database) => {
	  if (err) 
	    return console.log(err)
	  console.log("Successfully Connected to Mongodb server");
	  db = database.db("exercise11")
	  // Ensure indexs exists in the db  
	  db.collection('songsCollection').ensureIndex({ name: 1 });
	  db.collection('songsCollection').ensureIndex({ artist: 1 });
	  db.collection('songsCollection').ensureIndex({ album: 1 });
	  db.collection('songsCollection').ensureIndex({ genre: 1 });


	});
}


function addSong(song, res) {
  db.collection('songsCollection').insert([song], function(err, database) {
        if (err){
         res.send("Can not add the song")
        }	
        else{
         res.send("Added song successfully")
        } 
    });
}


function readSongs(propertie ,param, res){
	let dbQuery = {}
	dbQuery[propertie] = param
	console.log('DB query contant is:'+ dbQuery)
	db.collection('songsCollection').find(dbQuery).toArray((err, results) => {
		if(err || results.length < 1 )
		{
			res.send("Could not find a song")	
		} 
		else
		{
			res.send(results)
		}
	})
}


function updateSong(id, update, res){
	let updateQuery = {"_id": ObjectId(id)}
	let updateData = {$set: update}
	db.collection('songsCollection').updateOne(
		updateQuery, updateData, function(err, numberOfRecoredsModified){
			if(err)
			{
				throw err
			}
			else if (numberOfRecoredsModified == 0)
			{
				res.send("Could not find a song with id %s ", id)
			}else{
				res.send("The song was updated!")
				console.log('A song was updated - ' + id + ' ' + updateData )
			}
		})
}


function deleteSong(id, res){
	deleteQuery = {"_id": ObjectId(id)}
	db.collection("songsCollection").deleteOne(
		deleteQuery, function(err, result){
			if(err){
				throw err
			}
			else
			{
				let numberOfDeletedRecoreds = result.result.n
				if (numberOfDeletedRecoreds == 0)
				{
					res.send("Song not found!")	
				}
				else
				{
					res.send("Song was deleted")
				}
			}
		})
}


// Song delete schema
let deleteSongSchema = {
	"id": "/SongUpdate",
	"type": "object",
	"properties": {
		"id": {"type": "string"},
	},
	"required": ["id"]
}


// Song addition schema
let addSongSchema = {
    "id": "/Song",
    "type": "object",
    "properties": {
      "name": {"type": "string"},
      "artist": {"type": "string"},
      "genre": {"type": "string"},
      "album":{"type": "string"}
    },
    "required": ["name", "artist", "genre"]
  };


// Song update schema
let updateSongSchema = {
	"id": "/SongUpdate",
	"type": "object",
	"properties": {
		"id": {"type": "string"},
		"song": {"$ref": "/Song"}
	},
	"required": ["id", "song"]
}