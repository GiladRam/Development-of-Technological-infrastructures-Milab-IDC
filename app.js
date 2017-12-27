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

app.get('/getFile/:filename', (req,res) => {
	let reqFileName = req.params.filename || "No filename"
	let filePath = __dirname + "/" + reqFileName
	if (fs.existsSync(filePath)) {
    	fs.createReadStream(reqFileName).pipe(res);
	}else{
		res.send("Could not find file " + filePath);		
	}	
})

app.listen(port, () => console.log('Example app listening on port' + port + '!'))