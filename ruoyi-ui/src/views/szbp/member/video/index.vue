<template>
  <!-- <div class="videoArea">
    <video ref="videoPlayer" class="video-js"></video>
  </div> -->
  <div class="app-container">
      教程视频：
      <div style="margin-top: 50px; text-align: center;">
        <video id="myVideo" class="video-js vjs-big-play-centered">
            <source src="https://vod.dps-pigeonloft.com/sv/466ed1db-186b4d00fa3/466ed1db-186b4d00fa3.mp4" type="video/mp4">
        </video>
      </div>
  </div>
</template>

<script>

export default {
  name: "member-video",
  data() {
    return {
      player: null
    };
  },
  created() {
  },
  mounted() {
    this.getVideo();
  },
  methods: {
    // 实例化播放器（另一种方法）
		// createVideo() {
    //   let options = {
    //     autoplay: false,
    //     controls: true,
    //     sources: [
    //         {
    //             src: "@/src/video/20211216/jppXUfSi/1100kb/hls/index.m3u8", // 本地路径
    //         }
    //     ]
    //   }
      
    //   this.player = videojs(this.$refs.videoPlayer, options, function onPlayerReady() {
    //     console.log('onPlayerReady', this);
    //   })
    // },
    getVideo() {
      let myPlayer = this.$video(myVideo, {
          controls: true,           
          autoplay: false,//自动播放属性,muted:静音播放
          //建议浏览器是否应在<video>加载元素后立即开始下载视频数据。
          preload: "auto",//none不预加载任何数据，直到用户开始播放才开始下载
          width: "500px",
          height: "400px",
          loop: false,//循环
      //poster:""//在视频开始播放之前显示的图像的URL
      });
    }
  }
};
</script>
