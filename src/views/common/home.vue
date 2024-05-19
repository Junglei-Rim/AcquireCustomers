<template>
  <div class="mod-home">
    <h3>项目介绍</h3>
      <p>随着短视频平台的快速发展，越来越多的企业意识到在这些平台上进行客户获得的重要性。然而，由于短视频平台上信息庞杂且分散，传统的客户获得方法已经不能满足商家的需求。因此，本项目旨在设计并实现一个基于人工智能的短视频获客平台，帮助商家更加方便、快捷、高效地发现目标客户，并进行精准的市场推广和营销活动。</p>
    <h3>目的及意义</h3>
    <p>
本项目的目的是利用人工智能技术，对短视频平台上的海量数据进行分析和挖掘，识别潜在的目标客户群体，并为商家提供个性化的推广方案。通过该系统，商家可以更加精准地锁定潜在客户，提高营销效率和客户转化率。同时，本项目也具有一定的商业价值，能够为商家提供一种新的客户获得和营销方式。</p>
      <div>
    <!-- 轮播图 -->
    <el-carousel :interval="4000" type="card" height="400px" @change="handleCarouselChange">
      <el-carousel-item v-for="(video, index) in videos" :key="index">
        <video ref="videos" :src="video" muted loop playsinline class="carousel-video"></video>
      </el-carousel-item>
    </el-carousel>
  </div>
  </div>
</template>

<script>
  export default {
    data() {
    return {
      videos: [
        require('@/assets/mp4/home1.mp4'),
        require('@/assets/mp4/home2.mp4'),
        require('@/assets/mp4/home3.mp4'),
        require('@/assets/mp4/home4.mp4'),
        require('@/assets/mp4/home5.mp4'),
        require('@/assets/mp4/home6.mp4')
      ]
    };
  },
  methods: {
    handleCarouselChange(index) {
      const videoElements = this.$refs.videos;
      videoElements.forEach((video, idx) => {
        if (idx === index) {
          video.play();
        } else {
          video.pause();
          video.currentTime = 0; // Reset video to start
        }
      });
    }
  },
  mounted() {
    this.$nextTick(() => {
      // Play the first video initially
      if (this.$refs.videos.length > 0) {
        this.$refs.videos[0].play();
      }
    });
  }
  }
</script>

<style>
  .mod-home {
    line-height: 1.5;
  }
  .carousel-video {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
</style>

