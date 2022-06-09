<script src="vue.js"></script>
<script src="axios-0.18.0.js"></script>
<script src="../element-ui/lib/index.js"></script>
<link rel="stylesheet" href="../element-ui/lib/theme-chalk/index.css?v=1.0.0"/>
export default {
    el: "#app",
    data() {
        return {
            //数据模型
            //默认为空
            brands: [],
            newBrand: {
                status: '',
                brandName: '',
                companyName: '',
                id: "",
                ordered: "",
                description: ""
            },
            selectedBrand: {
                status: '',
                brandName: '',
                companyName: '',
                id: "",
                ordered: "",
                description: ""
            },
            multipleSelection: [],
            selectedIds: [],
            selectedId: '',
            insertFormVisible: false,
            updateFormVisible: false,
            currentPage: 1,
            totalCount: 0,
            pageSize: 5
        }
    },
    mounted() {
        this.selectAll();
    },
    methods: {
        //定义行的颜色
        // tableRowClassName({ row, rowIndex }) {
        //     if (row.status === 1) {
        //         return 'success-row';
        //     } else {
        //         return '';
        //     }
        // },

        selectAll() {
            var vueObj = this;
            //调用axios把数据拿过来
            axios({
                method: "get",
                url: "http://localhost:8080/company/selectByPageServlet?currentPage="
                    + vueObj.currentPage + "&pageSize=" + vueObj.pageSize
            }).then(function (resp) {
                //给brands（数据模型）赋值resp数据
                vueObj.brands = resp.data.rows;
                vueObj.totalCount = resp.data.totalCount;
            })
        },

        //查询按钮对应的函数
        select() {
            console.log('select!');
        },

        //Add brand
        addBrand() {
            var vueObj = this;
            axios({
                method: "post",
                url: "http://localhost:8080/company/addBrandServlet",
                data: vueObj.newBrand
            }).then(function (resp) {
                if (resp.data == 'success') {
                    // location.href = 'http://localhost:8080/company/brand.html';
                    vueObj.selectAll();
                    vueObj.$message({
                        message: '恭喜你，添加成功',
                        type: 'success'
                    });
                    vueObj.insertFormVisible = false;
                }
            })
        },

        //Show data in the form
        updateForm(id, brandName, companyName, ordered, description, status) {
            this.selectedBrand.id = id;
            this.selectedBrand.brandName = brandName;
            this.selectedBrand.companyName = companyName;
            this.selectedBrand.ordered = ordered;
            this.selectedBrand.description = description;
            this.selectedBrand.status = status;
            this.updateFormVisible = true;
        },

        //Update
        update() {
            var vueObj = this;
            axios({
                method: "post",
                url: "http://localhost:8080/company/updateServlet",
                data: vueObj.selectedBrand
            }).then(function (resp) {
                if (resp.data == "success") {
                    // location.href = "http://localhost:8080/company/brand.html"
                    vueObj.selectAll();
                    vueObj.$message({
                        message: '恭喜你，数据更新成功',
                        type: 'success'
                    });
                    vueObj.updateFormVisible = false;
                }
            })
        },

        deleteById(id) {
            this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                var vueObj = this;
                axios({
                    method: "post",
                    url: "http://localhost:8080/company/deleteByIdServlet",
                    data: id
                }).then((resp) => {
                    if (resp.data == "success") {
                        // location.href = "http://localhost:8080/company/brand.html";
                        vueObj.selectAll();
                        vueObj.$message({
                            message: '恭喜你，删除成功',
                            type: 'success'
                        });
                    }
                })
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消删除'
                });
            });
        },

        //批量删除对应的函数：弹出确定框
        deleteByIds() {
            this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                //Parse through selected brands and store their ids into an array
                for (let i = 0; i < this.multipleSelection.length; ++i) {
                    this.selectedIds[i] = this.multipleSelection[i].id;
                }
                var vueObj = this;
                axios({
                    method: "post",
                    url: "http://localhost:8080/company/deleteByIdsServlet",
                    data: vueObj.selectedIds
                }).then((resp) => {
                    if (resp.data == "success") {
                        // location.href = "http://localhost:8080/company/brand.html";
                        vueObj.selectAll();
                        vueObj.$message({
                            message: '恭喜你，删除成功',
                            type: 'success'
                        });
                    }
                })
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消删除'
                });
            });
        },

        //多选框对应的函数
        //Add the selected brands to an array
        handleSelectionChange(val) {
            this.multipleSelection = val;
        },

        //Called when page size is changed
        handleSizeChange(val) {
            this.pageSize = val;
            this.selectAll();
        },

        //Called when current page is changed
        handleCurrentChange(val) {
            this.currentPage = val;
            this.selectAll();
        }
    }
}