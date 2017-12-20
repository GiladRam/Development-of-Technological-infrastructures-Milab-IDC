const express = require('express')
const app = express()
const fs = require('fs');
const port = process.env.PORT || 8080;

app.get('/', (req, res) => res.send('Hello World!'))


app.get('/gettime', (req,res) => {
	let date = new Date();	
	let currentTime = date.toTimeString();
	res.send("The time current Time on the server: " + currentTime);
})

app.get('/getFile', (req,res) => {
	let reqFileName = req.query.filename || "No filename"
	let fullFileName = reqFileName + '.txt' 
	fs.readFile( fullFileName ,(err, content) => {
	if (err) {
 		console.error(err);
 		res.send("Could not find file name: " + reqFileName)
 		return;
		}
 	console.log("Got content with length - " + content.length);
 	res.send(content.toString())
	});
})


app.listen(port, () => console.log('Example app listening on port' + port + '!'))