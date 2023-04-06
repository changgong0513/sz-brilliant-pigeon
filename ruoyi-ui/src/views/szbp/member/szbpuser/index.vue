<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="100px">
      <el-form-item label="用户名" prop="userName">
        <el-input
          v-model="queryParams.userName"
          placeholder="请输入用户名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="会员编号" prop="memberId">
        <el-input
          v-model="queryParams.memberId"
          placeholder="请输入会员编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="电话" prop="userMobile">
        <el-input
          v-model="queryParams.userMobile"
          placeholder="请输入电话"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="隶属协会编号" prop="affiliationAssociationId">
        <el-input
          v-model="queryParams.affiliationAssociationId"
          placeholder="请输入隶属协会编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="隶属协会名称" prop="affiliationAssociationName">
        <el-input
          v-model="queryParams.affiliationAssociationName"
          placeholder="请输入隶属协会名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="交鸽羽数" prop="featherNumber">
        <el-input
          v-model="queryParams.featherNumber"
          placeholder="请输入交鸽羽数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="代理姓名" prop="proxyName">
        <el-input
          v-model="queryParams.proxyName"
          placeholder="请输入代理姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="代理电话" prop="proxyMobile">
        <el-input
          v-model="queryParams.proxyMobile"
          placeholder="请输入代理电话"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['member:szbpuser:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['member:szbpuser:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['member:szbpuser:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['member:szbpuser:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="szbpuserList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编号" align="center" prop="id" />
      <el-table-column label="用户名" align="center" prop="userName" />
      <el-table-column label="会员编号" align="center" prop="memberId" />
      <el-table-column label="电话" align="center" prop="userMobile" />
      <el-table-column label="隶属协会编号" align="center" prop="affiliationAssociationId" />
      <el-table-column label="隶属协会名称" align="center" prop="affiliationAssociationName" />
      <el-table-column label="交鸽羽数" align="center" prop="featherNumber" />
      <el-table-column label="代理姓名" align="center" prop="proxyName" />
      <el-table-column label="代理电话" align="center" prop="proxyMobile" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['member:szbpuser:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['member:szbpuser:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改会员信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="150px">
        <el-form-item label="用户名" prop="userName">
          <el-input v-model="form.userName" placeholder="请输入用户名" />
        </el-form-item>
        <el-form-item label="会员编号" prop="memberId">
          <el-input v-model="form.memberId" placeholder="请输入会员编号" />
        </el-form-item>
        <el-form-item label="电话" prop="userMobile">
          <el-input v-model="form.userMobile" placeholder="请输入电话" />
        </el-form-item>
        <el-form-item label="隶属协会编号" prop="affiliationAssociationId">
          <el-input v-model="form.affiliationAssociationId" placeholder="请输入隶属协会编号" />
        </el-form-item>
        <el-form-item label="隶属协会名称" prop="affiliationAssociationName">
          <el-input v-model="form.affiliationAssociationName" placeholder="请输入隶属协会名称" />
        </el-form-item>
        <el-form-item label="交鸽羽数" prop="featherNumber">
          <el-input v-model.number="form.featherNumber" placeholder="请输入交鸽羽数" />
        </el-form-item>
        <el-form-item label="代理姓名" prop="proxyName">
          <el-input v-model="form.proxyName" placeholder="请输入代理姓名" />
        </el-form-item>
        <el-form-item label="代理电话" prop="proxyMobile">
          <el-input v-model="form.proxyMobile" placeholder="请输入代理电话" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listSzbpuser, getSzbpuser, delSzbpuser, addSzbpuser, updateSzbpuser } from "@/api/szbp/member/szbpuser";

export default {
  name: "Szbpuser",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 会员信息表格数据
      szbpuserList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userName: null,
        memberId: null,
        userMobile: null,
        affiliationAssociationId: null,
        affiliationAssociationName: null,
        featherNumber: null,
        proxyName: null,
        proxyMobile: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        userName: [
          { required: true, message: "用户名不能为空", trigger: "blur" },
          { pattern: /[^%&',;=?$\x22]+/, message: "用户名包含^%&',;=?$\" 等特殊字符，请重新输入", trigger: "blur" }
          
        ],
        memberId: [
          { required: true, message: "会员编号不能为空", trigger: "blur" },
          { pattern: /^[A-Za-z0-9]+$/, message: "会员编号只能包含数字和英文字母", trigger: "blur" }
        ],
        userMobile: [
          { required: true, message: "电话不能为空", trigger: "blur" },
          { pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/, message: "请输入正确的电话号码", trigger: "blur" }
        ],
        affiliationAssociationId: [
          { required: true, message: "隶属协会编号不能为空", trigger: "blur" }
        ],
        affiliationAssociationName: [
          { required: true, message: "隶属协会名称不能为空", trigger: "blur" }
        ],
        featherNumber: [
          { required: true, message: "交鸽羽数不能为空", trigger: "blur" },
          { type: 'number', message: '交鸽羽数必须为数字值' }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询会员信息列表 */
    getList() {
      this.loading = true;
      listSzbpuser(this.queryParams).then(response => {
        this.szbpuserList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        userName: null,
        memberId: null,
        userMobile: null,
        affiliationAssociationId: null,
        affiliationAssociationName: null,
        featherNumber: null,
        proxyName: null,
        proxyMobile: null,
        createTime: null,
        updateTime: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加会员信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getSzbpuser(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改会员信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateSzbpuser(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addSzbpuser(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除会员信息编号为"' + ids + '"的数据项？').then(function() {
        return delSzbpuser(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('member/szbpuser/export', {
        ...this.queryParams
      }, `szbpuser_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
