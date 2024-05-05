<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="planName">
      <el-input v-model="dataForm.planName" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="targetUserCriteria">
      <el-input v-model="dataForm.targetUserCriteria" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="promotionStrategy">
      <el-input v-model="dataForm.promotionStrategy" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="startDate">
      <el-input v-model="dataForm.startDate" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="endDate">
      <el-input v-model="dataForm.endDate" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="status">
      <el-input v-model="dataForm.status" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="createdBy">
      <el-input v-model="dataForm.createdBy" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="createdTs">
      <el-input v-model="dataForm.createdTs" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="lastModifyTs">
      <el-input v-model="dataForm.lastModifyTs" placeholder=""></el-input>
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
          planName: '',
          targetUserCriteria: '',
          promotionStrategy: '',
          startDate: '',
          endDate: '',
          status: '',
          createdBy: '',
          createdTs: '',
          lastModifyTs: ''
        },
        dataRule: {
          planName: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          targetUserCriteria: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          promotionStrategy: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          startDate: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          endDate: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          createdBy: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          createdTs: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          lastModifyTs: [
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
              url: this.$http.adornUrl(`/strategy/promotionplan/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.planName = data.promotionPlan.planName
                this.dataForm.targetUserCriteria = data.promotionPlan.targetUserCriteria
                this.dataForm.promotionStrategy = data.promotionPlan.promotionStrategy
                this.dataForm.startDate = data.promotionPlan.startDate
                this.dataForm.endDate = data.promotionPlan.endDate
                this.dataForm.status = data.promotionPlan.status
                this.dataForm.createdBy = data.promotionPlan.createdBy
                this.dataForm.createdTs = data.promotionPlan.createdTs
                this.dataForm.lastModifyTs = data.promotionPlan.lastModifyTs
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
              url: this.$http.adornUrl(`/strategy/promotionplan/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'planName': this.dataForm.planName,
                'targetUserCriteria': this.dataForm.targetUserCriteria,
                'promotionStrategy': this.dataForm.promotionStrategy,
                'startDate': this.dataForm.startDate,
                'endDate': this.dataForm.endDate,
                'status': this.dataForm.status,
                'createdBy': this.dataForm.createdBy,
                'createdTs': this.dataForm.createdTs,
                'lastModifyTs': this.dataForm.lastModifyTs
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
