    var webSocket = 
      new WebSocket('ws://localhost:8888/shopweb/websocket');

    webSocket.onerror = function(event) {
      onError(event)
    };

    webSocket.onopen = function(event) {
      onOpen(event)
    };

    webSocket.onmessage = function(event) {
      onMessage(event)
    };

    function onMessage(event) {
//    	toastr8.bottom-right({
//    		message: '<a href=\'http://samuel.maispc.com\'><i class=\'fa fa-link\'></i>'+event.data+'</a>',
////          title: 'Personal Page',
//          imgURI: ['https://avatars0.githubusercontent.com/u/4276775?v=3&s=90'],
//          iconClass: 'glyphicon glyphicon-user text-warning',
//          timeOut: 5000
//      }).css('background-color', getRandomColor());
		toastr8.info({
        message: event.data,
//            title: 'Personal Page',
            imgURI: ['https://avatars0.githubusercontent.com/u/4276775?v=3&s=90'],
            iconClass: 'glyphicon glyphicon-user text-warning',
            positionClass: 'toast8-bottom-right',
            timeOut: 5000
        }).css('background-color', getRandomColor());
    	
//    	toastr8.windows(options); 
//      document.getElementById('messages').innerHTML 
//        += '<br />Received message: ' + event.data;
    }

    function onOpen(event) {
    }

    function onError(event) {
    }

    function send() {
      var txt = document.getElementById('inputmessage').value;
      webSocket.send(txt);
      return false;
    }