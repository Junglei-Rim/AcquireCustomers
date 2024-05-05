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
    <el-form-item label="" prop="awemeId">
      <el-input v-model="dataForm.awemeId" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="awemeType">
      <el-input v-model="dataForm.awemeType" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="title">
      <el-input v-model="dataForm.title" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="desc">
      <el-input v-model="dataForm.desc" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="likedCount">
      <el-input v-model="dataForm.likedCount" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="commentCount">
      <el-input v-model="dataForm.commentCount" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="shareCount">
      <el-input v-model="dataForm.shareCount" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="collectedCount">
      <el-input v-model="dataForm.collectedCount" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="awemeUrl">
      <el-input v-model="dataForm.awemeUrl" placeholder=""></el-input>
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
          awemeId: '',
          awemeType: '',
          title: '',
          desc: '',
          createTime: '',
          likedCount: '',
          commentCount: '',
          shareCount: '',
          collectedCount: '',
          awemeUrl: ''
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
          awemeId: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          awemeType: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          title: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          desc: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          likedCount: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          commentCount: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          shareCount: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          collectedCount: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          awemeUrl: [
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
              url: this.$http.adornUrl(`/data/douyinaweme/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.userId = data.douyinAweme.userId
                this.dataForm.secUid = data.douyinAweme.secUid
                this.dataForm.shortUserId = data.douyinAweme.shortUserId
                this.dataForm.userUniqueId = data.douyinAweme.userUniqueId
                this.dataForm.nickname = data.douyinAweme.nickname
                this.dataForm.avatar = data.douyinAweme.avatar
                this.dataForm.userSignature = data.douyinAweme.userSignature
                this.dataForm.ipLocation = data.douyinAweme.ipLocation
                this.dataForm.addTs = data.douyinAweme.addTs
                this.dataForm.lastModifyTs = data.douyinAweme.lastModifyTs
                this.dataForm.awemeId = data.douyinAweme.awemeId
                this.dataForm.awemeType = data.douyinAweme.awemeType
                this.dataForm.title = data.douyinAweme.title
                this.dataForm.desc = data.douyinAweme.desc
                this.dataForm.createTime = data.douyinAweme.createTime
                this.dataForm.likedCount = data.douyinAweme.likedCount
                this.dataForm.commentCount = data.douyinAweme.commentCount
                this.dataForm.shareCount = data.douyinAweme.shareCount
                this.dataForm.collectedCount = data.douyinAweme.collectedCount
                this.dataForm.awemeUrl = data.douyinAweme.awemeUrl
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
              url: this.$http.adornUrl(`/data/douyinaweme/${!this.dataForm.id ? 'save' : 'update'}`),
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
                'awemeId': this.dataForm.awemeId,
                'awemeType': this.dataForm.awemeType,
                'title': this.dataForm.title,
                'desc': this.dataForm.desc,
                'createTime': this.dataForm.createTime,
                'likedCount': this.dataForm.likedCount,
                'commentCount': this.dataForm.commentCount,
                'shareCount': this.dataForm.shareCount,
                'collectedCount': this.dataForm.collectedCount,
                'awemeUrl': this.dataForm.awemeUrl
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
