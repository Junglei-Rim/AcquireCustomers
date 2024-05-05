<template>
  <div class="portal-container">
    <!-- 导航栏 -->
    <el-menu
      :default-active="activeIndex"
      mode="horizontal"
      class="nav-menu"
      active-text-color="#4E6EF2"
    >
      <el-menu-item index="1">首页</el-menu-item>
      <el-menu-item index="2">客户案例</el-menu-item>
      <el-menu-item index="3">企业动态</el-menu-item>
      <el-menu-item index="4">企业文化</el-menu-item>
      <el-menu-item index="5">关于我们</el-menu-item>
      <!-- 自定义内容放置在导航栏最右侧 -->
      <!-- 登录注册按钮 -->
      <div class="login-register">
        <el-button type="primary" round @click="handleLogin" >登录</el-button>
        <el-button type="primary" round @click="handleRegister">注册</el-button>
      </div>
    </el-menu>

    <!-- 宣传图 -->
    <el-row class="promo">
      <img
        src="../../assets/img/banner-text.jpg"
        alt="宣传图"
        class="promo-image"
      />
      <img
        src="../../assets/img/banner_right_image.png"
        alt="宣传图2"
        class="promo-image"
      />
    </el-row>

    <!-- 轮播图展示 -->
    <el-carousel indicator-position="outside" class="carousel">
      <el-carousel-item>
        <img
          src="../../assets/img/lunbo1.jpg"
          class="carousel-image"
          alt="轮播图1"
        />
      </el-carousel-item>
      <el-carousel-item>
        <img
          src="../../assets/img/lunbo2.jpg"
          class="carousel-image"
          alt="轮播图2"
        />
      </el-carousel-item>
      <el-carousel-item>
        <img
          src="../../assets/img/lunbo3.jpg"
          class="carousel-image"
          alt="轮播图3"
        />
      </el-carousel-item>
    </el-carousel>

    <div>
      <!-- 关于公司 -->
      <el-row class="section">
        <el-col :span="12">
          <h2 class="section-title">关于我们</h2>
          <p class="company-description">
            我们是一家致力于创新科技的公司，专注于为商家提供获客软件和服务。通过技术创新和用户体验的不断提升，我们帮助客户实现销售业绩的快速增长，成为行业领先的获客解决方案提供商。
          </p>
          <p class="company-vision">
            我们的愿景是成为全球最具影响力的获客软件企业，为商家创造持续增长的价值，让他们在竞争激烈的市场中脱颖而出，实现商业成功和持续发展。
          </p>
        </el-col>

        <el-col :span="12">
          <video class="vdeio_show" controls>
            <source src="../../assets/mp4/company_video.mp4" type="video/mp4" />
            您的浏览器不支持 video 标签。
          </video>
        </el-col>
      </el-row>
    </div>

    <div>
      <!-- 产品介绍 -->
      <el-row class="section">
        <el-col :span="12">
          <video class="vdeio_show" controls>
            <source src="../../assets/mp4/company_video.mp4" type="video/mp4" />
            您的浏览器不支持 video 标签。
          </video>
        </el-col>

        <el-col :span="12">
          <h2 class="section-title">产品介绍</h2>
          <p class="product-description">
            我们的产品是一款专业的获客软件，致力于帮助商家通过短视频搜索和评论分析等方式吸引客户，并提升业务效益。我们提供多种获客功能，包括抓取评论区客户、精准线索分析、AI超级销售等，帮助商家快速发现潜在客户和提升销售效率。
          </p>
          <ul class="product-features">
            <li>抓取评论区客户，实时分析用户评论，快速定位潜在客户。</li>
            <li>精准线索分析，根据客户行为和评论内容，精准推送产品和服务。</li>
            <li>AI超级销售，智能化管理客户关系，提高销售效率。</li>
          </ul>
        </el-col>
      </el-row>
    </div>

    <div>
      <!-- 共享店铺 -->
      <el-row class="section">
        <el-col :span="12">
          <h2 class="section-title">共享店铺</h2>
          <p class="store-description">
            我们的共享店铺是一个创新的商业模式，为商家提供共享店铺空间，让他们可以共同利用店铺资源，降低成本，提高经营效率。我们提供灵活的租赁方案和一站式服务，帮助商家轻松打造独特的店铺体验，吸引更多顾客。
          </p>
          <ul class="store-features">
            <li>共享店铺空间，节省租金成本。</li>
            <li>提供一站式服务，包括装修、客流管理等。</li>
            <li>灵活的租赁方案，满足不同商家的需求。</li>
          </ul>
        </el-col>

        <el-col :span="12">
          <video class="vdeio_show" controls>
            <source src="../../assets/mp4/company_video.mp4" type="video/mp4" />
            您的浏览器不支持 video 标签。
          </video>
        </el-col>
      </el-row>
    </div>

    <!-- AI超级销售 -->
    <div>
      <!-- AI超级销售 -->
      <el-row class="section">
        <el-col :span="12">
          <video class="vdeio_show" controls>
            <source src="../../assets/mp4/company_video.mp4" type="video/mp4" />
            您的浏览器不支持 video 标签。
          </video>
        </el-col>

        <el-col :span="12">
          <h2 class="section-title">AI超级销售</h2>
          <p class="ai-sales-description">
            我们的AI超级销售是一种智能化的销售管理系统，通过人工智能技术和大数据分析，帮助商家更好地管理客户关系、提高销售效率。我们提供自动化的客户跟进、智能推荐产品、个性化服务等功能，让商家轻松实现销售目标。
          </p>
          <ul class="ai-sales-features">
            <li>自动化的客户跟进，提高销售效率。</li>
            <li>智能推荐产品，根据客户需求进行个性化推荐。</li>
            <li>个性化服务，根据客户行为和偏好进行定制化服务。</li>
          </ul>
        </el-col>
      </el-row>
    </div>

    <!-- 整合网红 -->
    <el-row class="section">
      <el-col :span="12">
        <h2 class="section-title">整合网红</h2>
        <p class="internet-celebrities-description">
          我们整合了一批具有影响力的网红资源，为商家提供广告推广和产品曝光服务。通过网红的传播力和影响力，帮助商家扩大品牌知名度，吸引更多顾客关注和购买。
        </p>
        <p class="internet-celebrities-services">
          我们提供的服务包括网红推广、品牌合作、内容创作等多种形式，满足不同商家的需求。
        </p>
      </el-col>

      <el-col :span="12">
        <video class="vdeio_show" controls>
          <source src="../../assets/mp4/company_video.mp4" type="video/mp4" />
          您的浏览器不支持 video 标签。
        </video>
      </el-col>
    </el-row>

    <!-- 员工合伙 -->
    <el-row class="section">
      <el-col :span="12">
        <video class="vdeio_show" controls>
          <source src="../../assets/mp4/company_video.mp4" type="video/mp4" />
          您的浏览器不支持 video 标签。
        </video>
      </el-col>

      <el-col :span="12">
        <h2 class="section-title">员工合伙</h2>
        <p class="employee-partnership-description">
          我们鼓励员工参与公司的经营和发展，提供员工合伙计划，让员工成为公司的合伙人，共享公司的成长和利润。
        </p>
        <p class="employee-partnership-benefits">
          员工合伙计划包括股权激励、利润分红等多种形式，激励员工积极参与公司的经营活动，实现共赢。
        </p>
      </el-col>
    </el-row>

    <!-- 共享商圈 -->
    <el-row class="section">
      <el-col :span="12">
        <h2 class="section-title">共享商圈</h2>
        <p class="shared-business-circle-description">
          我们打造了一个共享商圈平台，让商家可以共享经营资源和客户资源，实现合作共赢。通过共享商圈，商家可以相互合作，互通有无，共同发展。
        </p>
        <p class="shared-business-circle-platform">
          我们提供的共享商圈平台包括店铺合作、资源共享、客户引流等多种功能，让商家轻松实现共享经济。
        </p>
      </el-col>

      <el-col :span="12">
        <video class="vdeio_show" controls>
          <source src="../../assets/mp4/company_video.mp4" type="video/mp4" />
          您的浏览器不支持 video 标签。
        </video>
      </el-col>
    </el-row>

    <!-- 客源引爆系统 -->
    <el-row class="section">
      <el-col :span="12">
        <video class="vdeio_show" controls>
          <source src="../../assets/mp4/company_video.mp4" type="video/mp4" />
          您的浏览器不支持 video 标签。
        </video>
      </el-col>

      <el-col :span="12">
        <h2 class="section-title">客源引爆系统</h2>
        <p class="customer-source-explosion-system-description">
          我们的客源引爆系统是一种强大的客户引流工具，通过数据分析和精准营销，帮助商家快速吸引客户，实现销售业绩的快速增长。
        </p>
        <p class="customer-source-explosion-system-features">
          我们的客源引爆系统包括评论分析、精准推送、广告投放等多种功能，让商家轻松实现客户引爆，实现业务的快速增长。
        </p>
      </el-col>
    </el-row>

    <div>
      <!-- 申请免费使用 -->
      <el-row class="section">
        <el-col :span="12">
          <h2 class="section-title">申请免费使用</h2>
          <p class="apply-description">
            现在申请免费使用我们的获客软件，立即提升你的业务效率！
          </p>
          <p class="contact-info">请填写下方表单，我们将尽快与您联系：</p>
          <el-form
            :model="formData"
            :rules="rules"
            ref="form"
            label-width="200px"
          >
            <el-form-item label="电话号码" prop="phone">
              <el-input
                v-model="formData.phone"
                placeholder="请输入电话号码"
              ></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="submitForm"
                >申请免费使用</el-button
              >
            </el-form-item>
          </el-form>
          <p class="contact-info">
            联系方式：电话：010-12345678 邮箱：info@example.com
          </p>
        </el-col>

        <el-col :span="12">
          <img
            src="../../assets/img/free-trial.jpg"
            alt="申请免费使用图片"
            class="apply-free-image"
          />
        </el-col>
      </el-row>
    </div>

    <!-- 底部备案栏 -->
    <el-footer class="footer">
      <p>西柚网工科技有限公司 地址：四川省南充市顺庆区西南石油大学</p>
      <p>
        川ICP备15278290号-1
        <img src="../../assets/img/police.jpg" alt="图标" class="police" />
        川公网安备173920923987号
      </p>
      <p>Copyright 2024,ALL Rights Reserved www.swpu.com</p>
    </el-footer>
  </div>
  
</template>

<script>
export default {
  data() {
    return {
      activeIndex: "1", // 导航栏默认选中项
      formData: {
        phone: "",
      },
      rules: {
        phone: [
          { required: true, message: "请输入电话号码", trigger: "blur" },
          {
            pattern: /^[1][3,4,5,7,8][0-9]{9}$/,
            message: "请输入正确的电话号码",
            trigger: "blur",
          },
        ],
      },
    };
  },
  methods: {
    handleLogin() {
      // 跳转到登录页面
      this.$router.replace({ name: 'login' })  
    },
    handleRegister() {
      // 跳转到注册页面
      this.$router.replace({ name: 'register' })
    },
    submitForm() {
      this.$refs.form.validate((valid) => {
        if (valid) {
          // 提交表单逻辑
          console.log("提交表单成功");
        } else {
          console.log("表单校验失败");
          return false;
        }
      });
    },
  },
};
</script>

<style scoped>
.police {
  width: 15px; /* 设置图片宽度 */
  height: auto; /* 根据比例自动调整高度 */
  margin-left: 5px; /* 设置图片与文本之间的间距 */
}

.vdeio_show {
  width: 100%;
}

.login-register {
  position: absolute; /* 绝对定位 */
  top: 0;
  right: 100px; /* 放置在导航栏最右侧 */
  display: flex;
  align-items: center; /* 垂直居中 */
}

.apply-free-image {
  width: 100%;
}

.section {
  padding: 50px 0;
}

.section-title {
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 20px;
  text-align: center; /* 将文本水平居中对齐 */
}

.portal-container {
  width: 100%;
  max-width: 1500px;
  margin: 0 auto;
}

.nav-menu {
  background-color: #fff;
  color: #fff;
}

.promo {
  background-color: #000;
  padding: 30px;
}

.promo-image {
  width: 40%;
  margin: 10px auto;
}

.apply-free-image {
  width: 80%;
}

.carousel {
  width: 60%;
  height: 100%;
  margin: 20px auto;
}

.carousel-image {
  width: 100%;
  height: 100%;
}

.footer {
  background-color: #303030;
  color: #a1a1a1;
  text-align: center;
  padding: 60px;
  display: flex;
  flex-direction: column;
  justify-content: center; /* 在垂直方向上居中对齐 */
  line-height: 1; /* 调整每行文字的行高 */
}

.footer p {
  margin-bottom: 0px; /* 设置每个段落的底部间距 */
}
</style>
