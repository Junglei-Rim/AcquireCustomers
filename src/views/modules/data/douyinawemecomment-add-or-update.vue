<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="userId">
      <el-input v-model="dataForm.userId" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="secUid">
      <el-input v-model="dataForm.secUid" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="shortUserId">
      <el-input v-model="dataForm.shortUserId" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="userUniqueId">
      <el-input v-model="dataForm.userUniqueId" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="nickname">
      <el-input v-model="dataForm.nickname" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="avatar">
      <el-input v-model="dataForm.avatar" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="userSignature">
      <el-input v-model="dataForm.userSignature" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="ipLocation">
      <el-input v-model="dataForm.ipLocation" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="addTs">
      <el-input v-model="dataForm.addTs" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="lastModifyTs">
      <el-input v-model="dataForm.lastModifyTs" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="commentId">
      <el-input v-model="dataForm.commentId" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="awemeId">
      <el-input v-model="dataForm.awemeId" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="content">
      <el-input v-model="dataForm.content" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="subCommentCount">
      <el-input v-model="dataForm.subCommentCount" placeholder=""></el-input>
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
          secUid: '',
          shortUserId: '',
          userUniqueId: '',
          nickname: '',
          avatar: '',
          userSignature: '',
          ipLocation: '',
          addTs: '',
          lastModifyTs: '',
          commentId: '',
          awemeId: '',
          content: '',
          createTime: '',
          subCommentCount: ''
        },
        dataRule: {
          userId: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          secUid: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          shortUserId: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          userUniqueId: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          nickname: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          avatar: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          userSignature: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          ipLocation: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          addTs: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          lastModifyTs: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          commentId: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          awemeId: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          content: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          subCommentCount: [
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
              url: this.$http.adornUrl(`/data/douyinawemecomment/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.userId = data.douyinAwemeComment.userId
                this.dataForm.secUid = data.douyinAwemeComment.secUid
                this.dataForm.shortUserId = data.douyinAwemeComment.shortUserId
                this.dataForm.userUniqueId = data.douyinAwemeComment.userUniqueId
                this.dataForm.nickname = data.douyinAwemeComment.nickname
                this.dataForm.avatar = data.douyinAwemeComment.avatar
                this.dataForm.userSignature = data.douyinAwemeComment.userSignature
                this.dataForm.ipLocation = data.douyinAwemeComment.ipLocation
                this.dataForm.addTs = data.douyinAwemeComment.addTs
                this.dataForm.lastModifyTs = data.douyinAwemeComment.lastModifyTs
                this.dataForm.commentId = data.douyinAwemeComment.commentId
                this.dataForm.awemeId = data.douyinAwemeComment.awemeId
                this.dataForm.content = data.douyinAwemeComment.content
                this.dataForm.createTime = data.douyinAwemeComment.createTime
                this.dataForm.subCommentCount = data.douyinAwemeComment.subCommentCount
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
              url: this.$http.adornUrl(`/data/douyinawemecomment/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'userId': this.dataForm.userId,
                'secUid': this.dataForm.secUid,
                'shortUserId': this.dataForm.shortUserId,
                'userUniqueId': this.dataForm.userUniqueId,
                'nickname': this.dataForm.nickname,
                'avatar': this.dataForm.avatar,
                'userSignature': this.dataForm.userSignature,
                'ipLocation': this.dataForm.ipLocation,
                'addTs': this.dataForm.addTs,
                'lastModifyTs': this.dataForm.lastModifyTs,
                'commentId': this.dataForm.commentId,
                'awemeId': this.dataForm.awemeId,
                'content': this.dataForm.content,
                'createTime': this.dataForm.createTime,
                'subCommentCount': this.dataForm.subCommentCount
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
