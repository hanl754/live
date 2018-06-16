<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="/img/favicon.ico">

    <title>Jumbotron Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <script src="/js/jquery.min.js" type="text/javascript"></script>
    <link href="/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/css/style.css" rel="stylesheet">
    <link href="http://vjs.zencdn.net/5.5.3/video-js.css" rel="stylesheet">
    <!-- If you'd like to support IE8 -->
    <script src="http://vjs.zencdn.net/ie8/1.1.1/videojs-ie8.min.js"></script>
    <script src="/js/video.js"></script>
    <script src="/js/videojs-contrib-hls.min.js" type="text/javascript"></script>
    <script src="/js/jquery.danmu.min.js" type="text/javascript"></script>
    <script src="https://cdn.jsdelivr.net/sockjs/0.3.4/sockjs.min.js"></script>

</head>

<body>

<nav class="navbar navbar-toggleable-md navbar-inverse fixed-top bg-inverse">
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <a class="navbar-brand" href="#">Navbar</a>

    <div class="collapse navbar-collapse" id="navbarsExampleDefault">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Link</a>
            </li>
            <li class="nav-item">
                <a class="nav-link disabled" href="#">Disabled</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="http://example.com" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Dropdown</a>
                <div class="dropdown-menu" aria-labelledby="dropdown01">
                    <a class="dropdown-item" href="#">Action</a>
                    <a class="dropdown-item" href="#">Another action</a>
                    <a class="dropdown-item" href="#">Something else here</a>
                </div>
            </li>
        </ul>
        <form class="form-inline my-2 my-lg-0">
            <input class="form-control mr-sm-2" type="text" placeholder="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
    </div>
</nav>


<div class="video_main container">
   <div class="row">
       <div class="col-md-7">
           <div style="position:relative; background-color: black ; height: 400px; width: 640px;">
               <div id="danmu" style="width: 100% !important;"></div>
               <video id="my-video" class="video-js" controls autoplay preload="auto" width="640" height="400" data-setup="{}">
                   <!-- 替换成你的域名 -->
                   <source src="http://www.hdice.cn/hls/${roomId}.m3u8" type='application/x-mpegURL'>
               </video>
           </div>
       </div>
       <div class="col-md-5">
           <div class="row">
               <div class="col-lg-12">
                   <div id="console" style="border: 1px solid #EEEEEE;height: 363px;overflow: auto"></div>
               </div>
           </div>
           <div class="row">
               <div class="col-lg-12">
                   <div class="input-group">
                       <input id="msg_content" type="text" class="form-control" placeholder="hehe...">
                       <span class="input-group-btn">
                       <button id="msg_send" class="btn btn-secondary" type="button">发送</button>
                       </span>
                   </div>
               </div>
           </div><!-- /.row -->
       </div>
   </div>
<hr>

<footer>
    <p>&copy; Company 2018</p>
</footer>
</div> <!-- /container -->


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script>window.jQuery || document.write('<script src="/js/jquery.min.js"><\/script>')</script>
<script src="/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
<script src="/js/bootstrap.min.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="/js/ie10-viewport-bug-workaround.js"></script>
</body>

<script>
    $(document).ready(function() {
        var danmu = $("#danmu").danmu({
            right: 0,
            top: 0 ,
            height: 400,  //弹幕区高度
            zindex :1,   //弹幕区域z-index属性
            speed:7000,      //滚动弹幕的默认速度，这是数值值得是弹幕滚过每672像素所需要的时间（毫秒）
            sumTime:65535,   //弹幕流的总时间
            danmuLoop:true,   //是否循环播放弹幕
            defaultFontColor:"#FFFFFF",   //弹幕的默认颜色
            fontSizeSmall:16,     //小弹幕的字号大小
            FontSizeBig:24,       //大弹幕的字号大小
            opacity:"0.9",			//默认弹幕透明度
            topBottonDanmuTime:6000,   // 顶部底部弹幕持续时间（毫秒）
            SubtitleProtection:false,     //是否字幕保护
            positionOptimize:false,         //是否位置优化，位置优化是指像AB站那样弹幕主要漂浮于区域上半部分
            maxCountInScreen: 40,   //屏幕上的最大的显示弹幕数目,弹幕数量过多时,优先加载最新的。
            maxCountPerSec: 100      //每分秒钟最多的弹幕数目,弹幕数量过多时,优先加载最新的。
        });

        danmu.danmu('danmuStart');

        connect();

        //连接websocket
        function connect() {
            var target = "/chatRoom";
            ws = new SockJS(target);
            ws.onopen = function () {
                log('Info: WebSocket connection opened.');
            };
            ws.onmessage = function (event) {
                var msg = JSON.parse(event.data);
                log(msg.from + ": " + msg.content);
                var time = $('#danmu').data("nowTime")+1;
                danmu.danmu("addDanmu",[
                    { text:msg.content ,color:"white",size:1,position:0,time:time, isNew:1}
                ]);
            };
            ws.onclose = function () {
                log('Info: WebSocket connection closed.');
            };
        }

        function disconnect() {
            if (ws != null) {
                ws.close();
                ws = null;
            }
        }

        $("#msg_send").click(function() {
            var message = $("#msg_content").val();
            ws.send(message);
        });

        function log(message) {
            var console = document.getElementById('console');
            var p = document.createElement('p');
            p.style.wordWrap = 'break-word';
            p.appendChild(document.createTextNode(message));
            console.appendChild(p);
            while (console.childNodes.length > 100) {
                console.removeChild(console.firstChild);
            }
            console.scrollTop = console.scrollHeight;
        }
    });
</script>
</html>
