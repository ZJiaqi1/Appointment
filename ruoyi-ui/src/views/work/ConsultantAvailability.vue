<template>
  <div>
    <el-dialog v-bind="$attrs" v-on="$listeners" @open="onOpen" @close="onClose" title="Dialog Title">
      <el-row :gutter="15">
        <el-form ref="咨询时段安排" :model="formData" :rules="rules" size="medium" label-width="100px">
          <el-col :span="12">
            <el-form-item label="姓名" prop="user_name">
              <el-input v-model="formData.user_name" placeholder="请输入姓名" clearable :style="{width: '100%'}">
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="手机号" prop="mobile">
              <el-input v-model="formData.mobile" placeholder="请输入手机号" :maxlength="11" show-word-limit
                clearable prefix-icon='el-icon-mobile' :style="{width: '100%'}"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="留言" prop="message">
              <el-input v-model="formData.message" placeholder="请输入留言" :maxlength="100" show-word-limit
                clearable :style="{width: '100%'}"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="日期选择" prop="date_available">
              <el-date-picker v-model="formData.date_available" format="yyyy-MM-dd" value-format="yyyy-MM-dd"
                :style="{width: '100%'}" placeholder="请选择日期选择" clearable></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="任务状态" prop="task_status" required>
              <el-switch v-model="formData.task_status"></el-switch>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="可用时间" prop="time_available">
              <el-time-picker v-model="formData.time_available" is-range format="HH:mm:ss"
                value-format="HH:mm:ss" :style="{width: '100%'}" start-placeholder="开始时间"
                end-placeholder="结束时间" range-separator="至" clearable></el-time-picker>
            </el-form-item>
          </el-col>
        </el-form>
      </el-row>
      <div slot="footer">
        <el-button @click="close">取消</el-button>
        <el-button type="primary" @click="handleConfirm">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
export default {
  inheritAttrs: false,
  components: {},
  props: [],
  data() {
    return {
      formData: {
        user_name: undefined,
        mobile: '',
        message: undefined,
        date_available: null,
        task_status: false,
        time_available: ["00:00:00", "00:00:00"],
      },
      rules: {
        user_name: [{
          required: true,
          message: '请输入姓名',
          trigger: 'blur'
        }],
        mobile: [{
          required: true,
          message: '请输入手机号',
          trigger: 'blur'
        }, {
          pattern: /^1(3|4|5|7|8|9)\d{9}$/,
          message: '手机号格式错误',
          trigger: 'blur'
        }],
        message: [{
          required: true,
          message: '请输入留言',
          trigger: 'blur'
        }],
        date_available: [{
          required: true,
          message: '请选择日期选择',
          trigger: 'change'
        }],
        time_available: [{
          required: true,
          type: 'array',
          message: '请至少选择一个time_available',
          trigger: 'change'
        }],
      },
    }
  },
  computed: {},
  watch: {},
  created() {},
  mounted() {},
  methods: {
    onOpen() {},
    onClose() {
      this.$refs['咨询时段安排'].resetFields()
    },
    close() {
      this.$emit('update:visible', false)
    },
    handleConfirm() {
      this.$refs['咨询时段安排'].validate(valid => {
        if (!valid) return
        this.close()
      })
    },
  }
}

</script>
<style>
</style>
