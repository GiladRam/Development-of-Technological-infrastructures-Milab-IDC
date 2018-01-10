const express = require('express')
const app = express()
const fs = require('fs');
const port = process.env.PORT || 8080;
let http = require('http');
let server = http.createServer(app); 
let io = require('socket.io')(server);
let mainClient = null;

app.get('/', (req, res) => res.send('Welcome!!! MOLO API'))

app.get('/gettime', (req,res) => {
	let date = new Date();	
	let jsonCurrentTime = { currentTime: date.toJSON()};
	res.send(jsonurCrentTime);
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

io.on('connection', function(client) {  
    console.log('Client connected...');
    mainClient = client;
    console.log(mainClient);

    client.on('join', function(data) {
        console.log(data);
    });

});


var options = {
  url: 'http://52.214.115.168:9200',
  path: '/pi-log*/_search',
  method: 'POST',
  headers: {
      'Content-Type': 'application/json',
  }
};


setInterval(function () { 
	var req = http.request(options, function(res) {
  		console.log('Status: ' + res.statusCode);
  		console.log('Headers: ' + JSON.stringify(res.headers));
  		res.setEncoding('utf8');
  		res.on('data', function (body) {
    	console.log('Body: ' + body);
  			});
		});  
		req.on('error', function(e) {
  			console.log('problem with request: ' + e.message);
		});   
}, 1000); 

app.listen(port, () => console.log('Example app listening on port' + port + '!'))
