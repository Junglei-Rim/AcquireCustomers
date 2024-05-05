<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="userId">
      <el-input v-model="dataForm.userId" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="totalLikes">
      <el-input v-model="dataForm.totalLikes" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="totalComments">
      <el-input v-model="dataForm.totalComments" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="totalShares">
      <el-input v-model="dataForm.totalShares" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="totalVideos">
      <el-input v-model="dataForm.totalVideos" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="totalCommentsOnOwnVideos">
      <el-input v-model="dataForm.totalCommentsOnOwnVideos" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="totalCommentsOnOtherVideos">
      <el-input v-model="dataForm.totalCommentsOnOtherVideos" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="lastInteractionTs">
      <el-input v-model="dataForm.lastInteractionTs" placeholder=""></el-input>
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
          totalLikes: '',
          totalComments: '',
          totalShares: '',
          totalVideos: '',
          totalCommentsOnOwnVideos: '',
          totalCommentsOnOtherVideos: '',
          lastInteractionTs: ''
        },
        dataRule: {
          userId: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          totalLikes: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          totalComments: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          totalShares: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          totalVideos: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          totalCommentsOnOwnVideos: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          totalCommentsOnOtherVideos: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          lastInteractionTs: [
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
              url: this.$http.adornUrl(`/data/userbehaviorstats/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.userId = data.userBehaviorStats.userId
                this.dataForm.totalLikes = data.userBehaviorStats.totalLikes
                this.dataForm.totalComments = data.userBehaviorStats.totalComments
                this.dataForm.totalShares = data.userBehaviorStats.totalShares
                this.dataForm.totalVideos = data.userBehaviorStats.totalVideos
                this.dataForm.totalCommentsOnOwnVideos = data.userBehaviorStats.totalCommentsOnOwnVideos
                this.dataForm.totalCommentsOnOtherVideos = data.userBehaviorStats.totalCommentsOnOtherVideos
                this.dataForm.lastInteractionTs = data.userBehaviorStats.lastInteractionTs
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
              url: this.$http.adornUrl(`/data/userbehaviorstats/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'userId': this.dataForm.userId,
                'totalLikes': this.dataForm.totalLikes,
                'totalComments': this.dataForm.totalComments,
                'totalShares': this.dataForm.totalShares,
                'totalVideos': this.dataForm.totalVideos,
                'totalCommentsOnOwnVideos': this.dataForm.totalCommentsOnOwnVideos,
                'totalCommentsOnOtherVideos': this.dataForm.totalCommentsOnOtherVideos,
                'lastInteractionTs': this.dataForm.lastInteractionTs
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
