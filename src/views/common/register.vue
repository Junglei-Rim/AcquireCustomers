<template>
  <div class="video-background">
    <video autoplay muted loop id="myVideo">
      <source src="../../assets/mp4/login.mp4" type="video/mp4" />
      Your browser does not support HTML5 video.
    </video>
    <div class="register-form">
      <el-form
        :model="dataForm"
        :rules="dataRule"
        ref="dataForm"
        @keyup.enter.native="dataFormSubmit()"
        label-width="80px"
      >
        <el-form-item label="账号" prop="userName">
          <el-input
            v-model="dataForm.userName"
            placeholder="登录账号"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="密码"
          prop="password"
          :class="{ 'is-required': !dataForm.id }"
        >
          <el-input
            v-model="dataForm.password"
            type="password"
            placeholder="密码"
          ></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="confirmPassword">
          <el-input
            v-model="dataForm.confirmPassword"
            type="password"
            placeholder="确认密码"
          ></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="dataForm.email" placeholder="邮箱"></el-input>
        </el-form-item>
        <el-form-item label="手机号" prop="mobile">
          <el-input v-model="dataForm.mobile" placeholder="手机号"></el-input>
        </el-form-item>
        <!--         <el-form-item label="角色" size="mini" prop="roleIdList">
          <el-checkbox-group v-model="dataForm.roleIdList">
            <el-checkbox v-for="role in roleList" :key="role.roleId" :label="role.roleId">{{ role.roleName }}</el-checkbox>
          </el-checkbox-group>
        </el-form-item> -->
        <!--         <el-form-item label="状态" size="mini" prop="status">
          <el-radio-group v-model="dataForm.status">
            <el-radio :label="0">禁用</el-radio>
            <el-radio :label="1">正常</el-radio>
          </el-radio-group>
        </el-form-item> -->
      </el-form>
      <div class="button-group">
        <el-button type="primary" @click="dataFormSubmit()">立即注册</el-button>
        <el-button type="success" @click="bakcLogin()"
          >已有账号？返回登录</el-button
        >
      </div>
    </div>

  </div>
</template>
  
  <script>
import { isEmail, isMobile } from "@/utils/validate";
export default {
  data() {
    var validatePassword = (rule, value, callback) => {
      if (!this.dataForm.id && !/\S/.test(value)) {
        callback(new Error("密码不能为空"));
      } else {
        callback();
      }
    };
    var validateConfirmPassword = (rule, value, callback) => {
      if (value !== this.dataForm.password) {
        callback(new Error("两次输入密码不一致"));
      } else {
        callback();
      }
    };
    var validateEmail = (rule, value, callback) => {
      if (!isEmail(value)) {
        callback(new Error("邮箱格式错误"));
      } else {
        callback();
      }
    };
    var validateMobile = (rule, value, callback) => {
      if (!isMobile(value)) {
        callback(new Error("手机号格式错误"));
      } else {
        callback();
      }
    };
    return {
      roleList: [],
      dataForm: {
        id: 0,
        userName: "",
        password: "",
        comfirmPassword: "",
        salt: "",
        email: "",
        mobile: "",
        roleIdList: [],
        status: 1,
      },
      dataRule: {
        userName: [
          { required: true, message: "账号不能为空", trigger: "blur" },
        ],
        password: [{ validator: validatePassword, trigger: "blur" }],
        confirmPassword: [
          { validator: validateConfirmPassword, trigger: "blur" },
        ],
        email: [
          { required: true, message: "邮箱不能为空", trigger: "blur" },
          { validator: validateEmail, trigger: "blur" },
        ],
        mobile: [
          { required: true, message: "手机号不能为空", trigger: "blur" },
          { validator: validateMobile, trigger: "blur" },
        ],
      },
    };
  },
  methods: {
    //返回登录
    bakcLogin() {
      this.$router.replace({ name: "login" });
    },
    // 表单提交
    dataFormSubmit() {
      this.$refs["dataForm"].validate((valid) => {
        if (valid) {
          this.$http({
            url: this.$http.adornUrl(`/sys/user/save`),
            method: "post",
            data: this.$http.adornData({
              userId: this.dataForm.id || undefined,
              username: this.dataForm.userName,
              password: this.dataForm.password,
              salt: this.dataForm.salt,
              email: this.dataForm.email,
              mobile: this.dataForm.mobile,
              status: this.dataForm.status,
              roleIdList: this.dataForm.roleIdList,
            }),
          }).then(({ data }) => {
            if (data && data.code === 0) {
              this.$message({
                message: "注册成功",
                type: "success",
                duration: 1500,
                onClose: () => {
                  this.$router.push("/login"); // 注册成功后跳转到登录页面
                },
              });
            } else {
              this.$message.error(data.msg);
            }
          });
        }
      });
    },
  },
};
</script>
  
  <style scoped>
  .video-background {
  position: relative;
  width: 100%;
  height: 100%;
}

#myVideo {
  position: fixed;
  right: 0;
  bottom: 0;
  min-width: 100%;
  min-height: 100%;
}
.register-form {
  position: relative;
  z-index: 1; /* 保证注册表单在视频上方显示 */
  width: 400px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ebeef5;
  border-radius: 4px;
  transform: translateY(50%);
  border-radius: 50px; /* 更大的圆角值 */
}

.button-group {
  text-align: center;
  margin-top: 20px;
}
</style>
  