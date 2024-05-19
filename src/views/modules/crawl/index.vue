<template>
  <div class="crawler">
     <!-- 自动播放的视频背景 -->
     <video autoplay muted loop playsinline class="background-video">
      <source src="../../../assets/mp4/crawlbg.mp4" type="video/mp4">
      您的浏览器不支持HTML5视频。
    </video>

    <el-form :model="crawlerForm" label-width="100px">
      <el-form-item label="爬取平台">
        <el-select v-model="crawlerForm.platform" placeholder="请选择">
          <el-option label="抖音" value="dy"></el-option>
          <el-option label="小红书" value="xhs"></el-option>
          <el-option label="快手" value="ks"></el-option>
          <el-option label="哔哩哔哩" value="bili"></el-option>
          <el-option label="微博" value="wb"></el-option>
          <!-- Add more platforms as needed -->
        </el-select>
      </el-form-item>
      <el-form-item label="验证方式">
        <el-select v-model="crawlerForm.lt" placeholder="请选择">
          <el-option label="二维码" value="qrcode"></el-option>
          <el-option label="手机验证码" value="phone"></el-option>
          <el-option label="cookie验证" value="cookie"></el-option>
          <!-- Add more authentication methods as needed -->
        </el-select>
      </el-form-item>
      <el-form-item label="爬取类型">
        <el-select v-model="crawlerForm.type" placeholder="请选择">
          <el-option label="关键词搜索" value="search"></el-option>
          <el-option label="具体帖子" value="detail"></el-option>
          <el-option label="创作者主页" value="creator"></el-option>
          <!-- Add more crawl types as needed -->
        </el-select>
      </el-form-item>
      <el-form-item label="搜索关键词">
        <el-input v-model="crawlerForm.keywords" placeholder="请输入搜索关键词"></el-input>
      </el-form-item>
      <el-form-item label="爬取视频数量">
        <el-input v-model="crawlerForm.maxNotesCount" placeholder="请输入数量"></el-input>
      </el-form-item>
      <el-form-item label="是否爬取评论">
        <el-switch v-model="crawlerForm.enableGetComments" active-text="是" inactive-text="否"></el-switch>
      </el-form-item>
      <el-form-item label="视频ID列表">
        <el-input v-model="crawlerForm.dySpecifiedIdList" placeholder="多个ID请用逗号分隔"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" class="button" @click="submitCrawler">爬取</el-button>
      </el-form-item>
    </el-form>

    <el-divider></el-divider>

        <!-- 弹窗组件 -->
    <el-dialog :visible.sync="dialogVisible" title="提示" width="30%">
        <span>爬取成功！</span>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">关闭</el-button>
        </div>
    </el-dialog>

    <el-progress :percentage="progress" v-if="showProgress"></el-progress>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      crawlerForm: {
        platform: '',
        lt: '',
        type: '',
        keywords: '',
        maxNotesCount: '',
        enableGetComments: false,
        dySpecifiedIdList: ''
      },
      descriptions: [],
      progress: 0,
      showProgress: false,
      dialogVisible: false, // 控制弹窗的显示隐藏
      dialogMessage: '' // 弹窗中显示的消息
    };
  },
  methods: {
    submitCrawler() {
      // 显示进度条
      this.showProgress = true;
      let interval = setInterval(() => {
        if (this.progress < 100) {
          this.progress += 10;
        } else {
          clearInterval(interval);
          this.showProgress = false;
          this.progress = 0;
          // 发送爬取参数到后端
          this.sendCrawlerParams();
        }
      }, 1000);
    },
    sendCrawlerParams() {
      const url = 'http://localhost:9000/api/data/flask/crawl';
      const params = {
        platform: this.crawlerForm.platform,
        lt: this.crawlerForm.lt,
        type: this.crawlerForm.type,
        keywords: this.crawlerForm.keywords,
        maxNotesCount: this.crawlerForm.maxNotesCount,
        enableGetComments: this.crawlerForm.enableGetComments,
        dySpecifiedIdList: this.crawlerForm.dySpecifiedIdList.split(',')
      };
      // 发送HTTP请求（这里使用axios）
      axios.post(url, params)
        .then(response => {
          // 获取后端返回的code
          const code = response.data.code;
          if (code === 0) {
            // 收到后端响应后显示成功弹窗
            this.dialogMessage = '爬取成功！，请前往数据分析模块查看！';
          } else {
            // 收到后端响应后显示失败弹窗
            this.dialogMessage = '爬取失败，请稍后重试。';
          }
          this.dialogVisible = true;
          // 清空表单
          this.resetForm();
        })
        .catch(error => {
          console.error('发送爬取请求出错：', error);
          // 显示失败弹窗
          this.dialogMessage = '爬取失败，请稍后重试。';
          this.dialogVisible = true;
        });
    },
    resetForm() {
      // 重置表单数据
      this.crawlerForm = {
        platform: '',
        lt: '',
        type: '',
        keywords: '',
        maxNotesCount: '',
        enableGetComments: false,
        dySpecifiedIdList: ''
      };
    },
    handleCloseDialog() {
      // 关闭弹窗时重置消息
      this.dialogMessage = '';
    }
  }
};
</script>

export default {
  data() {
    return {
      crawlerForm: {
        platform: '',
        lt: '',
        type: '',
        keywords: '',
        maxNotesCount: '',
        enableGetComments: false,
        dySpecifiedIdList: ''
      },
      descriptions: [],
      progress: 0,
      showProgress: false
    };
  },
  methods: {
    submitCrawler() {
      // Show progress bar
      this.showProgress = true;
      // Simulate crawler process
      let interval = setInterval(() => {
        if (this.progress < 100) {
          this.progress += 10;
        } else {
          clearInterval(interval);
          this.showProgress = false;
          this.progress = 0;
          // Send crawler parameters to backend
          this.sendCrawlerParams();
        }
      }, 1000);
    },
    sendCrawlerParams() {
      // Send crawler parameters to backend
      // You can use axios or any other library for HTTP requests
      const url = 'http://localhost:9000/api/data/flask/crawl';
      const params = {
        platform: this.crawlerForm.platform,
        lt: this.crawlerForm.lt,
        type: this.crawlerForm.type,
        keywords: this.crawlerForm.keywords,
        maxNotesCount: this.crawlerForm.maxNotesCount,
        enableGetComments: this.crawlerForm.enableGetComments,
        dySpecifiedIdList: this.crawlerForm.dySpecifiedIdList.split(',')
      };
      // Simulate successful response from backend
      // Replace this with actual HTTP request
      setTimeout(() => {
        this.descriptions.push('爬取成功');
      }, 2000);
    }
  }
};
</script>

<style>
.crawler {
  max-width: 600px;
  margin: 20px auto;
  position: relative;
  z-index: 1; /* 确保内容在视频上方 */
}

.background-video {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  object-fit: cover;
  z-index: -1; /* 确保视频在内容下方 */
}

.el-form {
  position: relative;
  z-index: 2;
  background-color: rgba(255, 255, 255, 0.8); /* 半透明白色背景 */
  padding: 20px;
  border-radius: 8px;
}
</style>
