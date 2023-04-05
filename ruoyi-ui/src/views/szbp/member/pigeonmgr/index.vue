<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="会员编号" prop="memberId">
        <el-input
          v-model="queryParams.memberId"
          placeholder="请输入会员编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="会员姓名" prop="memberName">
        <el-input
          v-model="queryParams.memberName"
          placeholder="请输入会员姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="赛鸽足环" prop="toeRing">
        <el-input
          v-model="queryParams.toeRing"
          placeholder="请输入赛鸽足环"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="赛鸽羽色" prop="featherColor">
        <el-select v-model="queryParams.featherColor" placeholder="请选择赛鸽羽色" clearable>
          <el-option
            v-for="dict in dict.type.member_pigeon_feather_color"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
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
          v-hasPermi="['member:pigeonmgr:add']"
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
          v-hasPermi="['member:pigeonmgr:edit']"
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
          v-hasPermi="['member:pigeonmgr:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['member:pigeonmgr:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="pigeonmgrList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编号" align="center" prop="id" />
      <el-table-column label="会员编号" align="center" prop="memberId" />
      <el-table-column label="会员姓名" align="center" prop="memberName" />
      <el-table-column label="赛鸽足环" align="center" prop="toeRing" />
      <el-table-column label="赛鸽羽色" align="center" prop="featherColor">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.member_pigeon_feather_color" :value="scope.row.featherColor"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['member:pigeonmgr:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['member:pigeonmgr:remove']"
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

    <!-- 添加或修改赛鸽管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="会员编号" prop="memberId">
          <el-input v-model="form.memberId" placeholder="请输入会员编号" />
        </el-form-item>
        <el-form-item label="会员姓名" prop="memberName">
          <el-input v-model="form.memberName" placeholder="请输入会员姓名" />
        </el-form-item>
        <el-form-item label="赛鸽足环" prop="toeRing">
          <el-input v-model="form.toeRing" placeholder="请输入赛鸽足环" />
        </el-form-item>
        <el-form-item label="赛鸽羽色" prop="featherColor">
          <el-select v-model="form.featherColor" placeholder="请选择赛鸽羽色">
            <el-option
              v-for="dict in dict.type.member_pigeon_feather_color"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
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
import { listPigeonmgr, getPigeonmgr, delPigeonmgr, addPigeonmgr, updatePigeonmgr } from "@/api/szbp/member/pigeonmgr";

export default {
  name: "Pigeonmgr",
  dicts: ['member_pigeon_feather_color'],
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
      // 赛鸽管理表格数据
      pigeonmgrList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        memberId: null,
        memberName: null,
        toeRing: null,
        featherColor: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        memberId: [
          { required: true, message: "会员编号不能为空", trigger: "blur" }
        ],
        memberName: [
          { required: true, message: "会员姓名不能为空", trigger: "blur" }
        ],
        toeRing: [
          { required: true, message: "赛鸽足环不能为空", trigger: "blur" }
        ],
        featherColor: [
          { required: true, message: "赛鸽羽色不能为空", trigger: "change" }
        ],
        createTime: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询赛鸽管理列表 */
    getList() {
      this.loading = true;
      listPigeonmgr(this.queryParams).then(response => {
        this.pigeonmgrList = response.rows;
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
        memberId: null,
        memberName: null,
        toeRing: null,
        featherColor: null,
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
      this.title = "添加赛鸽管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getPigeonmgr(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改赛鸽管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updatePigeonmgr(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPigeonmgr(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除赛鸽管理编号为"' + ids + '"的数据项？').then(function() {
        return delPigeonmgr(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('member/pigeonmgr/export', {
        ...this.queryParams
      }, `pigeonmgr_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
