<template>
    <body id="paper">
        <img class="bg" src="@/assets/img/login.jpg">
        <el-form :model="rulesForm" :rules="rules" ref="rulesForm" class="login-form">
            <h1 class="h1">简历解析系统登录</h1>
            <el-form-item label="账号" prop="username">
                <el-input type="text" v-model="rulesForm.username"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password">
                <el-input type="password" v-model="rulesForm.password"></el-input>
            </el-form-item>
            <el-form-item label="" prop="role">
                <el-radio v-for="item in menus" v-bind:key="item.roleName" v-model="rulesForm.role"
                          :label="item.roleName">{{item.roleName}}
                </el-radio>
            </el-form-item>
            <el-form-item label="">
                <a style="margin-right:10px" href="javascript:void(0)" @click="register('yonghu')">注册用户</a>
                <!-- <a style="margin-right:10px" href="javascript:void(0)" @click="register('qiyexinxi')">注册企业信息</a> -->
            </el-form-item>
            <el-button @click="login()" class="btn-login" type="primary" style="background: #505458;border: none">登录</el-button>
        </el-form>
    </body>
</template>
<script>
    import menu from '@/utils/menu'

    export default {
        data() {
            return {
                rulesForm: {
                    username: "",
                    password: "",
                    role: ""
                },
                menus: [],
                tableName: "",
                rules: {
                    username: [{required: true, message: "请输入账号", trigger: "blur"}],
                    password: [{required: true, message: "请输入密码", trigger: "blur"}],
                    role: [{required: true, message: "请选择角色", trigger: "blur"}]
                }
            };
        },
        mounted() {
            let menus = menu.list();
            this.menus = menus;
        },
        methods: {
            register(tableName) {
                this.$storage.set("loginTable", tableName);
                this.$router.push({path: '/register'})
            },
            // 登录
            login() {
                this.$refs["rulesForm"].validate(valid => {
                    let menus = this.menus;
                    for (let i = 0; i < menus.length; i++) {
                        if (menus[i].roleName == this.rulesForm.role) {
                            this.tableName = menus[i].tableName
                        }
                    }
                    if (valid) {
                        this.$http({
                            // 访问后台
                            url: `${this.tableName}/login?username=${this.rulesForm.username}&password=${this.rulesForm.password}`,
                            method: "post"
                        }).then(({data}) => {
                            if (data && data.code === 0) {
                                this.$storage.set("Token", data.token);
                                this.$storage.set("role", this.rulesForm.role);
                                this.$storage.set("sessionTable", this.tableName);
                                this.$storage.set("adminName", this.rulesForm.username);
                                this.$router.replace({path: "/index/"});
                            } else {
                                this.$message.error(data.msg);
                            }
                        });
                    }
                });
            }
        }
    };
</script>
<style lang="scss" scoped>
    #paper {
        // background:url("../assets/login.jpg") no-repeat;
        background-position: center;
        height: 100%;
        width: 100%;
        background-size: cover;
        position: fixed;
    }
    body{
        margin: 0px;
    }
    .login-form {
        border-radius: 15px;
        background-clip: padding-box;
        border:1px solid  transparent;
        margin: 90px auto;
        width: 350px;
        padding: 35px 35px 15px 35px;
        /* 为其整体设置接近透明的效果*/
        background-color: rgba(255,255,255,0.1);
        /* background: #fff; */
        /* border: 1px solid #eaeaea; */
        /* box-shadow: 0 0 25px #cac6c6; */
        /* 设置box-shadow使其有立体感 */
        box-shadow: 5px 5px 0 0  rgba(0,0,0,0.2);
        position: relative;
        left: -15px;
    }
    .h1 {
        margin: 0px auto 40px auto;
        text-align: center;
        color: #505458;
    }
        .el-form-item__label{
            /* 给el-form组件的label标签颜色修改 */
            color: #FFFFFF;
        }

        .el-input__inner{
            /* 使input框的背景变透明 */
            background-color: transparent;
            /* 使边框也变透明 */
            border-color: transparent;
            /* 给边框加阴影能够使其有立体感 */
            box-shadow: 2px 2px 0 0 rgba(0,0,0,0.2);
            /* 改变获取焦点后的竖线颜色 */
            caret-color: rgba(0, 255, 255,0.8);
            color:#FFFFFF;
        }

        .el-input__inner:hover{
            border-color: rgb(0, 255, 255);
        }

        .el-input__inner:focus{
            border-color: aqua;
        }

</style>