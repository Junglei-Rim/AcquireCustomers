<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="热词" prop="topic">
      <el-input v-model="dataForm.topic" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="视频数" prop="videoCount">
      <el-input v-model="dataForm.videoCount" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="评论数" prop="commentCount">
      <el-input v-model="dataForm.commentCount" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="更新时间" prop="lastUpdateTs">
      <el-input v-model="dataForm.lastUpdateTs" placeholder=""></el-input>
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
          topic: '',
          videoCount: '',
          commentCount: '',
          lastUpdateTs: ''
        },
        dataRule: {
          topic: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          videoCount: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          commentCount: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          lastUpdateTs: [
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
              url: this.$http.adornUrl(`/data/hottopics/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.topic = data.hotTopics.topic
                this.dataForm.videoCount = data.hotTopics.videoCount
                this.dataForm.commentCount = data.hotTopics.commentCount
                this.dataForm.lastUpdateTs = data.hotTopics.lastUpdateTs
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
              url: this.$http.adornUrl(`/data/hottopics/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'topic': this.dataForm.topic,
                'videoCount': this.dataForm.videoCount,
                'commentCount': this.dataForm.commentCount,
                'lastUpdateTs': this.dataForm.lastUpdateTs
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
