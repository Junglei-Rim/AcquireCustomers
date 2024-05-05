<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="userId">
      <el-input v-model="dataForm.userId" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="nickname">
      <el-input v-model="dataForm.nickname" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="avatar">
      <el-input v-model="dataForm.avatar" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="gender">
      <el-input v-model="dataForm.gender" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="age">
      <el-input v-model="dataForm.age" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="location">
      <el-input v-model="dataForm.location" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="bio">
      <el-input v-model="dataForm.bio" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="interestTags">
      <el-input v-model="dataForm.interestTags" placeholder=""></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          id: 0,
          userId: '',
          nickname: '',
          avatar: '',
          gender: '',
          age: '',
          location: '',
          bio: '',
          interestTags: ''
        },
        dataRule: {
          userId: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          nickname: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          avatar: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          gender: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          age: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          location: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          bio: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          interestTags: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/customer/userprofile/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.userId = data.userProfile.userId
                this.dataForm.nickname = data.userProfile.nickname
                this.dataForm.avatar = data.userProfile.avatar
                this.dataForm.gender = data.userProfile.gender
                this.dataForm.age = data.userProfile.age
                this.dataForm.location = data.userProfile.location
                this.dataForm.bio = data.userProfile.bio
                this.dataForm.interestTags = data.userProfile.interestTags
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/customer/userprofile/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'userId': this.dataForm.userId,
                'nickname': this.dataForm.nickname,
                'avatar': this.dataForm.avatar,
                'gender': this.dataForm.gender,
                'age': this.dataForm.age,
                'location': this.dataForm.location,
                'bio': this.dataForm.bio,
                'interestTags': this.dataForm.interestTags
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
