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
  let stockName = null;
  socket.on('sendStockName', (data) => {
    stockName = data;
    console.log(`New Stock : ${stock}`);
  });

  let postStockPrice = setInterval(() => {
    console.log(`Getting new stock price...`);
      if(!socket){
        console.log(`Error: No socket`);
      return;
      }
      if(!stockName){
        socket.emit('notFound');
        console.log(`Error: Could not find stock name`);
        return;
      }
      alphavantage.crypto.intraday('BTC').then((data) => {
      if(!data){
        socket.emit('notFound');
        console.log(`Error: Stock not found`);
        return;
      }
      let mostRecentStock = Object.keys(data['Time Series (1min)'])[0];
      const stockPrice = data['Time Series (1min)'][mostRecentStock]["1. open"];
      const stockData = {
        lastUpdated : mostRecentStock,
        price: stockPrice
      };
      socket.emit('postStockPrice', stockData);
  });
}, 15000);

  socket.on('disconnect',() => {
    clearInterval(postStockPrice);
  });
});