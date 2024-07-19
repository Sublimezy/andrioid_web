<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="科目类型" prop="subject">
        <el-select v-model="queryParams.subject" placeholder="科目类型" clearable>
          <el-option
            v-for="dict in dict.type.app_subject_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="驾照类型" prop="model">
        <el-select v-model="queryParams.model" placeholder="驾照类型" clearable>
          <el-option
            v-for="dict in dict.type.app_modal_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="题目类型" prop="model">
        <el-select v-model="queryParams.questionType" placeholder="题目类型" clearable>
          <el-option
            v-for="dict in dict.type.app_question_type"
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
          v-hasPermi="['system:book:add']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:book:edit']"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:book:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:book:export']"
        >导出
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="drivingBook"
          v-hasPermi="['system:book:export']"
        >拉取题库
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>


    <el-table v-loading="loading" :data="bookList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="id" align="center" prop="id"/>
      <el-table-column label="科目类型" align="center" prop="subject">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.app_subject_type" :value="scope.row.subject"/>
        </template>
      </el-table-column>
      <el-table-column label="驾照类型" align="center" prop="model">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.app_modal_type" :value="scope.row.model"/>
        </template>
      </el-table-column>
      <el-table-column label="题目类型" align="center" prop="questionType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.app_question_type" :value="scope.row.questionType"/>
        </template>
      </el-table-column>
      <el-table-column label="选项一" align="center" prop="item1"/>
      <el-table-column label="选项二" align="center" prop="item2"/>
      <el-table-column label="选项三" align="center" prop="item3"/>
      <el-table-column label="选项四" align="center" prop="item4"/>
      <el-table-column label="答案" align="center" prop="answer"/>
      <el-table-column label="问题" align="center" prop="question"/>
      <el-table-column label="解释" align="center" prop="explains"/>
      <el-table-column label="资源地址" align="center" prop="url">
        <template slot-scope="scope">
          <el-image
            style="width: 50px; height: 50px"
            :src="scope.row.url">
          </el-image>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['app:book:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['app:book:remove']"
          >删除
          </el-button>
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

    <!-- 添加或修改驾考题目对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="科目类型" prop="subject">
          <el-input v-model="form.subject" placeholder="请输入科目类型"/>
        </el-form-item>
        <el-form-item label="驾照类型" prop="model">
          <el-input v-model="form.model" placeholder="请输入驾照类型"/>
        </el-form-item>
        <el-form-item label="考题内容">
          <editor v-model="form.content" :min-height="192"/>
        </el-form-item>
        <el-form-item label="选项一" prop="item1">
          <el-input v-model="form.item1" placeholder="请输入选项一"/>
        </el-form-item>
        <el-form-item label="选项二" prop="item2">
          <el-input v-model="form.item2" placeholder="请输入选项二"/>
        </el-form-item>
        <el-form-item label="选项三" prop="item3">
          <el-input v-model="form.item3" placeholder="请输入选项三"/>
        </el-form-item>
        <el-form-item label="选项四" prop="item4">
          <el-input v-model="form.item4" placeholder="请输入选项四"/>
        </el-form-item>
        <el-form-item label="答案" prop="answer">
          <el-input v-model="form.answer" placeholder="请输入答案"/>
        </el-form-item>
        <el-form-item label="问题" prop="question">
          <el-input v-model="form.question" placeholder="请输入问题"/>
        </el-form-item>
        <el-form-item label="解释" prop="explains">
          <el-input v-model="form.explains" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
        <el-form-item label="资源地址" prop="url">
          <el-input v-model="form.url" placeholder="请输入资源地址"/>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注"/>
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
import {listBook, getBook, delBook, addBook, updateBook, listDrivingBook} from "@/api/app/drivingBook/drivingBook";
import axios from "axios";

export default {
  name: "Book",
  dicts: ['app_subject_type', 'app_modal_type', 'app_question_type'],
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
      // 驾考题目表格数据
      bookList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        subject: null,
        model: null,
        questionType: null,
        content: null,
        item1: null,
        item2: null,
        item3: null,
        item4: null,
        answer: null,
        question: null,
        explains: null,
        url: null,
      },
      // 表单参数
      form: {
        subject: null,
        model: "",
        content: [],
        key: '',  // 替换为你的API密钥
        testType: '',
      },
      // 表单校验
      rules: {}
    };
  },
  async created() {
    await this.getList();
  },
  methods: {
    /** 查询驾考题目列表 */
    async getList() {
      this.loading = true;
      const response = await listBook(this.queryParams);
      this.bookList = response.rows;
      this.total = response.total;
      this.loading = false;
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
        subject: null,
        model: null,
        questionType: null,
        content: null,
        item1: null,
        item2: null,
        item3: null,
        item4: null,
        answer: null,
        question: null,
        explains: null,
        url: null,
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
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加驾考题目";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getBook(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改驾考题目";
      });
    },
    async drivingBook() {

      for (let i = 0; i < 2; i++) {
        for (let j = 0; j < 6; j++) {
          this.form.key = "107b6449246a0103330b9f19808d9316";
          this.form.testType = "order";

          switch (i) {
            case 0:
              this.form.subject = 1;
              break;
            case 1:
              this.form.subject = 4;
              break;
          }

          switch (j) {
            case 0:
              this.form.model = "a1";
              break;
            case 1:
              this.form.model = "a2";
              break;
            case 2:
              this.form.model = "b1";
              break;
            case 3:
              this.form.model = "b2";
              break;
            case 4:
              this.form.model = "c1";
              break;
            case 5:
              this.form.model = "c2";
              break;
          }

          this.form.content = null;


          try {
            this.$modal.msgSuccess(`开始查询科目${this.form.subject}-驾照类型为${this.form.model}的题目`);
            console.log("查询")
            const listResponse = await listDrivingBook(this.form);
            this.form.content = listResponse.result;

            this.$modal.msgSuccess(`执行导入科目${this.form.subject}-驾照类型为${this.form.model}的题目`);
            console.log("导入")
            await addBook(this.form);
            console.log("查询所有")
            await this.getList();


          } catch (error) {
            console.error('Error fetching items:', error);
          }
        }
      }

    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateBook(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addBook(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除驾考题目编号为"' + ids + '"的数据项？').then(function () {
        return delBook(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/book/export', {
        ...this.queryParams
      }, `book_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
