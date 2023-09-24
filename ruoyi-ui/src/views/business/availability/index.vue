<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="100px">
      <el-form-item label="咨询师姓名" prop="userName">
        <el-input
          v-model="queryParams.userName"
          placeholder="请输入咨询师姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="日期选择" prop="dateAvailable">
        <el-date-picker clearable
                        v-model="queryParams.dateAvailable"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择日期选择">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="可用时间" prop="timeAvailable">
        <el-input
          v-model="queryParams.timeAvailable"
          placeholder="请输入可用时间"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="任务状态" prop="taskStatus">
        <el-select v-model="queryParams.taskStatus" placeholder="请选择任务状态" clearable>
          <el-option
            v-for="dict in dict.type.task_status"
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
          v-hasPermi="['business:availability:add']"
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
          v-hasPermi="['business:availability:edit']"
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
          v-hasPermi="['business:availability:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['business:availability:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="availabilityList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="任务id" align="center" prop="taskId" />
      <el-table-column label="咨询师姓名" align="center" prop="userName" />
      <el-table-column label="咨询师手机号" align="center" prop="mobile" />
      <el-table-column label="留言" align="center" prop="message" />
      <el-table-column label="日期选择" align="center" prop="dateAvailable" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.dateAvailable, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="可用时间" align="center" prop="timeAvailable" />
      <el-table-column label="任务状态" align="center" prop="taskStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.task_status" :value="scope.row.taskStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['business:availability:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['business:availability:remove']"
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

    <!-- 添加或修改咨询师单日咨询时段安排对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="150px">
        <el-form-item label="咨询师姓名" prop="userName">
          <el-input v-model="form.userName" placeholder="请输入咨询师姓名" />
        </el-form-item>
        <el-form-item label="咨询师手机号" prop="mobile">
          <el-input v-model="form.mobile" placeholder="请输入咨询师手机号" />
        </el-form-item>
        <el-form-item label="留言" prop="message">
          <el-input v-model="form.message" placeholder="请输入留言" />
        </el-form-item>
        <el-form-item label="日期选择" prop="dateAvailable">
          <el-date-picker clearable
                          v-model="form.dateAvailable"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择日期选择">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="时间选择" prop="timeAvailable">
          <el-time-select
            v-model="form.timeAvailable"
            :picker-options="{
              start: '00:0',
              step: '00:15',
              end: '23:30'
            }"
            placeholder="选择时间">
          </el-time-select>
        </el-form-item>
        <el-form-item label="任务状态" prop="taskStatus">
          <el-radio-group v-model="form.taskStatus">
            <el-radio
              v-for="dict in dict.type.task_status"
              :key="dict.value"
              :label="parseInt(dict.value)"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
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
import { listAvailability, getAvailability, delAvailability, addAvailability, updateAvailability } from "@/api/business/availability";

export default {
  name: "Availability",
  dicts: ['task_status'],
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
      // 咨询师单日咨询时段安排表格数据
      availabilityList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userName: null,
        dateAvailable: null,
        timeAvailable: null,
        taskStatus: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        userName: [
          { required: true, message: "咨询师姓名不能为空", trigger: "blur" }
        ],
        mobile: [
          { required: true, message: "咨询师手机号不能为空", trigger: "blur" }
        ],
        dateAvailable: [
          { required: true, message: "日期选择不能为空", trigger: "blur" }
        ],
        timeAvailable: [
          { required: true, message: "可用时间不能为空", trigger: "blur" }
        ],
        taskStatus: [
          { required: true, message: "任务状态不能为空", trigger: "change" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询咨询师单日咨询时段安排列表 */
    getList() {
      this.loading = true;
      listAvailability(this.queryParams).then(response => {
        this.availabilityList = response.rows;
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
        taskId: null,
        userName: null,
        mobile: null,
        message: null,
        dateAvailable: null,
        timeAvailable: null,
        taskStatus: null,
        status: null,
        delFlag: null,
        loginIp: null,
        loginDate: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null
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
      this.ids = selection.map(item => item.taskId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加咨询师单日咨询时段安排";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const taskId = row.taskId || this.ids
      getAvailability(taskId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改咨询师单日咨询时段安排";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.taskId != null) {
            updateAvailability(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAvailability(this.form).then(response => {
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
      const taskIds = row.taskId || this.ids;
      this.$modal.confirm('是否确认删除咨询师单日咨询时段安排编号为"' + taskIds + '"的数据项？').then(function() {
        return delAvailability(taskIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('business/availability/export', {
        ...this.queryParams
      }, `availability_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
