function playMedia(){
    jQuery(document).ready(function(){
            flowplayer("audio","/resources/swf/flowplayer-3.2.18.swf",
            {
               plugins: {
                   controls: {
                       fullscreen: false,
                       autoHide: false
                   },
                   audio: {
                       url: '/resources/swf/flowplayer.audio-3.2.10.swf'
                   }
               }, 
            clip:  {
              autoPlay: true,
              autoBuffering: true,
              provider:"audio"
                }
            });
        });
    }