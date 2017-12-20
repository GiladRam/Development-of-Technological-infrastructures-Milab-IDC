
process.stdin.setEncoding('utf8');

process.stdout.write("Enter a text to rotate: ");

process.stdin.on('data', function (text) {
	pureText = text.replace('\n','');
	process.stdout.write(pureText);
    for(var i = 0; i < pureText.length - 1; i++){
    	currentLastLetter = pureText[pureText.length -1];
    	pureText = currentLastLetter + pureText.slice(0, pureText.length - 1 );
    	process.stdout.write(pureText)
    }
    process.exit();
});
