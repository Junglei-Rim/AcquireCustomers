<template>
  <div class="mod-config">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-input v-model="dataForm.key" placeholder="参数名" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="getDataList()">查询</el-button>
        <el-button  type="primary" @click="addOrUpdateHandle()">新增</el-button>
        <el-button  type="danger" @click="deleteHandle()" :disabled="dataListSelections.length <= 0">批量删除</el-button>
      </el-form-item>
    </el-form>
    <el-table
      :data="dataList"
      border
      v-loading="dataListLoading"
      @selection-change="selectionChangeHandle"
      style="width: 100%;">
      <el-table-column
        type="selection"
        header-align="center"
        align="center"
        width="50">
      </el-table-column>
      <el-table-column
        prop="id"
        header-align="center"
        align="center"
        label="id">
      </el-table-column>
      <el-table-column
        prop="userId"
        header-align="center"
        align="center"
        label="userId">
      </el-table-column>
      <el-table-column
        prop="secUid"
        header-align="center"
        align="center"
        label="secUid">
      </el-table-column>
      <el-table-column
        prop="shortUserId"
        header-align="center"
        align="center"
        label="shortUserId">
      </el-table-column>
      <el-table-column
        prop="userUniqueId"
        header-align="center"
        align="center"
        label="userUniqueId">
      </el-table-column>
      <el-table-column
        prop="nickname"
        header-align="center"
        align="center"
        label="nickname">
      </el-table-column>
      <el-table-column
        prop="avatar"
        header-align="center"
        align="center"
        label="avatar">
      </el-table-column>
      <el-table-column
        prop="userSignature"
        header-align="center"
        align="center"
        label="userSignature">
      </el-table-column>
      <el-table-column
        prop="ipLocation"
        header-align="center"
        align="center"
        label="ipLocation">
      </el-table-column>
      <el-table-column
        prop="addTs"
        header-align="center"
        align="center"
        label="addTs">
      </el-table-column>
      <el-table-column
        prop="lastModifyTs"
        header-align="center"
        align="center"
        label="lastModifyTs">
      </el-table-column>
      <el-table-column
        prop="awemeId"
        header-align="center"
        align="center"
        label="awemeId">
      </el-table-column>
      <el-table-column
        prop="awemeType"
        header-align="center"
        align="center"
        label="awemeType">
      </el-table-column>
      <el-table-column
        prop="title"
        header-align="center"
        align="center"
        label="title">
      </el-table-column>
      <el-table-column
        prop="desc"
        header-align="center"
        align="center"
        label="desc">
      </el-table-column>
      <el-table-column
        prop="createTime"
        header-align="center"
        align="center"
        label="createTime">
      </el-table-column>
      <el-table-column
        prop="likedCount"
        header-align="center"
        align="center"
        label="likedCount">
      </el-table-column>
      <el-table-column
        prop="commentCount"
        header-align="center"
        align="center"
        label="commentCount">
      </el-table-column>
      <el-table-column
        prop="shareCount"
        header-align="center"
        align="center"
        label="shareCount">
      </el-table-column>
      <el-table-column
        prop="collectedCount"
        header-align="center"
        align="center"
        label="collectedCount">
      </el-table-column>
      <el-table-column
        prop="awemeUrl"
        header-align="center"
        align="center"
        label="awemeUrl">
      </el-table-column>
      <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        width="150"
        label="操作">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="addOrUpdateHandle(scope.row.id)">修改</el-button>
          <el-button type="text" size="small" @click="deleteHandle(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="sizeChangeHandle"
      @current-change="currentChangeHandle"
      :current-page="pageIndex"
      :page-sizes="[10, 20, 50, 100]"
      :page-size="pageSize"
      :total="totalPage"
      layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>
    <!-- 弹窗, 新增 / 修改 -->
    <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList"></add-or-update>
  </div>
</template>

<script>
  import AddOrUpdate from './douyinaweme-add-or-update'
  export default {
    data () {
      return {
        dataForm: {
          key: ''
        },
        dataList: [],
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
        dataListLoading: false,
        dataListSelections: [],
        addOrUpdateVisible: false
      }
    },
    components: {
      AddOrUpdate
    },
    activated () {
      this.getDataList()
    },
    methods: {
      // 获取数据列表
      getDataList () {
        this.dataListLoading = true
        this.$http({
          url: this.$http.adornUrl('/data/douyinaweme/list'),
          method: 'get',
          params: this.$http.adornParams({
            'page': this.pageIndex,
            'limit': this.pageSize,
            'key': this.dataForm.key
          })
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.dataList = data.page.list
            this.totalPage = data.page.totalCount
          } else {
            this.dataList = []
            this.totalPage = 0
          }
          this.dataListLoading = false
        })
      },
      // 每页数
      sizeChangeHandle (val) {
        this.pageSize = val
        this.pageIndex = 1
        this.getDataList()
      },
      // 当前页
      currentChangeHandle (val) {
        this.pageIndex = val
        this.getDataList()
      },
      // 多选
      selectionChangeHandle (val) {
        this.dataListSelections = val
      },
      // 新增 / 修改
      addOrUpdateHandle (id) {
        this.addOrUpdateVisible = true
        this.$nextTick(() => {
          this.$refs.addOrUpdate.init(id)
        })
      },
      // 删除
      deleteHandle (id) {
        var ids = id ? [id] : this.dataListSelections.map(item => {
          return item.id
        })
        this.$confirm(`确定对[id=${ids.join(',')}]进行[${id ? '删除' : '批量删除'}]操作?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: this.$http.adornUrl('/data/douyinaweme/delete'),
            method: 'post',
            data: this.$http.adornData(ids, false)
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.$message({
                message: '操作成功',
                type: 'success',
                duration: 1500,
                onClose: () => {
                  this.getDataList()
                }
              })
            } else {
              this.$message.error(data.msg)
            }
          })
        })
      }
    }
  }
</script>
