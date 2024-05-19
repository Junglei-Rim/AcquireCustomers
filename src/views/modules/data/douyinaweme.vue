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
        label="用户ID">
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
        label="用户昵称">
      </el-table-column>
      <el-table-column
        prop="avatar"
        header-align="center"
        align="center"
        label="头像地址">
        <template slot-scope="scope">
          <img :src="scope.row.avatar" style="width: 100%; height: 100%; object-fit: cover;" />
        </template>
      </el-table-column>
      <el-table-column
        prop="userSignature"
        header-align="center"
        align="center"
        label="用户签名">
      </el-table-column>
      <el-table-column
        prop="ipLocation"
        header-align="center"
        align="center"
        label="IP地址">
      </el-table-column>
      <el-table-column
        prop="addTs"
        header-align="center"
        align="center"
        label="添加时间戳">
        <template slot-scope="scope">
          {{ scope.row.addTs | timestampToDate }}
        </template>
      </el-table-column>
      <el-table-column
        prop="lastModifyTs"
        header-align="center"
        align="center"
        label="修改时间戳">
         <template slot-scope="scope">
          {{ scope.row.lastModifyTs | timestampToDate }}
        </template>
      </el-table-column>
      <el-table-column
        prop="awemeId"
        header-align="center"
        align="center"
        label="视频ID">
      </el-table-column>
      <el-table-column
        prop="awemeType"
        header-align="center"
        align="center"
        label="视频类型">
      </el-table-column>
      <el-table-column
        prop="title"
        header-align="center"
        align="center"
        label="视频标题">
      </el-table-column>
      <el-table-column
        prop="desc"
        header-align="center"
        align="center"
        label="视频描述">
      </el-table-column>
      <el-table-column
        prop="createTime"
        header-align="center"
        align="center"
        label="发布时间戳">
        <template slot-scope="scope">
          {{ scope.row.createTime | timestampToDate }}
        </template>
      </el-table-column>
      <el-table-column
        prop="likedCount"
        header-align="center"
        align="center"
        label="点赞数">
      </el-table-column>
      <el-table-column
        prop="commentCount"
        header-align="center"
        align="center"
        label="评论数">
      </el-table-column>
      <el-table-column
        prop="shareCount"
        header-align="center"
        align="center"
        label="分享数">
      </el-table-column>
      <el-table-column
        prop="collectedCount"
        header-align="center"
        align="center"
        label="收藏数">
      </el-table-column>
      <el-table-column
        prop="awemeUrl"
        header-align="center"
        align="center"
        label="详情页URL">
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
    },
    filters: {
    // 自定义过滤器将时间戳转换为日期
    timestampToDate(timestamp) {
      // 使用JavaScript内置的Date对象将时间戳转换为日期对象
      const date = new Date(timestamp);
      // 使用日期对象的方法获取年月日时分秒等信息，拼接成可读的日期格式
      const year = date.getFullYear();
      const month = String(date.getMonth() + 1).padStart(2, '0');
      const day = String(date.getDate()).padStart(2, '0');
      const hours = String(date.getHours()).padStart(2, '0');
      const minutes = String(date.getMinutes()).padStart(2, '0');
      const seconds = String(date.getSeconds()).padStart(2, '0');
      return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
    }
  }
  }
</script>
