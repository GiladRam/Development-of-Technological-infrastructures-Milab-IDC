const alphavantage = require('alphavantage')({key: 'PXWQ8QSU96NBM8NJ'});
const PORT = process.env.PORT || 3000;
let app = require('express')();
let server = require('http').Server(app);
let io = require('socket.io')(server);

server.listen(PORT, () => console.log(`listening in port ${PORT}`));

app.get('/', function (req, res) {
  res.status(403).send('Forbidden');
});

io.on('connect', function (socket) {
  let stock = null;
  socket.on('setStockName', (data) => {
    stock = data;
    console.log(`New Stock : ${stock}`);
  });

  let postStockPrice = setInterval(() => {
    console.log(`Getting new stock price...`);
      if(!socket){
        console.log(`Error: No socket`);
      return;
      }
      if(!stock){
        socket.emit('notFound');
        console.log(`Error: Could not find stock name`);
        return;
      }
      alphavantage.data.intraday('FB').then((data) => {
      if(!data){
        socket.emit('notFound');
        console.log(`Error: Stock not found`);
        return;
      }
      let mostRecentStock = Object.keys(data['Time Series (1min)'])[0];
      const stockPrice = data['Time Series (1min)'][mostRecentStock]["1. open"];
      const stockData = {
        mostRecentStock : mostRecentStock,
        currentValue: stockPrice
      };
      socket.emit('postStockPrice', stockData);
  });
}, 15000);

  socket.on('disconnect',() => {
    clearInterval(postStockPrice);
  });
});