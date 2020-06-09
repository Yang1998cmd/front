<template>
  <div id="login">
<el-menu default-active="#" class="el-menu-demo"  mode="horizontal" @select="handleSelect" >
  <el-menu-item index="" ><i class="el-icon-chat-line-square"></i></el-menu-item>
  <el-menu-item index="/Home" >首页</el-menu-item>
  <el-menu-item index="/Attention">关注</el-menu-item>
  <el-menu-item index="/HotWei" >热搜</el-menu-item>
  <el-menu-item index="/WeiboIntroduce" >推荐</el-menu-item>
 <el-input
  placeholder="请输入内容"
  v-model="input"
  clearable
  style="width:300px;margin-top:10px;margin-right:0px;margin-left:0px" >
</el-input>
<el-button style="width:55px ;height:40px;margin-left:-6px;margin-right:550px" icon="el-icon-search" ></el-button>
  <el-avatar  icon="el-icon-user-solid"></el-avatar>
</el-menu>
      <el-card id='card2'>
          <el-form label-width='80px'>
              <el-form-item class='form_item' label='新密码'>
                      <el-input class='input' v-model='password' type="password" placeholder="请输入密码" suffix-icon="el-icon-edit"></el-input>
              </el-form-item>
               <el-form-item class='form_item' label='确认密码'>
                      <el-input class='input' v-model='se_password' type="password" placeholder="请确认密码密码" suffix-icon="el-icon-edit"></el-input>
              </el-form-item>
              <el-form-item class='form_item' label='邮箱' >
                <el-row>
                     <el-input class='input2' v-model='email' placeholder="请输入邮箱" suffix-icon="el-icon-edit"></el-input>
                      <el-button type='primary' @click='getyzm'>获取验证码</el-button>
                </el-row>
              </el-form-item>
               <el-form-item class='form_item' label='验证码'>
                      <el-input class='input' v-model='yzm'  placeholder="请及时输入验证码" suffix-icon="el-icon-edit"></el-input>
              </el-form-item>
              <el-form-item>
                      <el-button id='button1' type='primary' @click='change'>确认</el-button>
                      <el-button id='button2' @click='returnlogin'>返回</el-button>
              </el-form-item>
          </el-form>
      </el-card> 
      
   </div>
</template>

<script>
export default {
    data(){
       return{
         activeIndex: '0',
         password: '',
         se_password:'',
         email:'',
         yzm:''
       }
    },
    methods:{
       handleSelect(key, keyPath) {
         console.log(key, keyPath);
       },
       getyzm()
       {
          var this_email=this.email;
          var reg=/^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
          //正则表达式过滤一下邮箱
          if(this_email=='')
          {
             this.$message({
                    showClose: true,
                    type:'warning',
                    message:'邮箱不能为空！'
                })
          }
          else if(!reg.test(this_email))
          {
              this.$alert('无效邮箱！', '', {
                        confirmButtonText: '确定',
                        center:true,
                      });
          }
          else{
             this.$axios({
             method: "post",
             url: "http://121.89.166.24:8080/change", // 接口地址
             withCredentials: "true",
             params: {
               email: this_email ,  // 传接口参数
            }
          })
          .then(response => {
            console.log(response.data, "success")
          })
          .catch(error => error => console.log(error, "error")); // 失败的返回
          }
       },
        change()
       {
          var this_password=this.password;
          var this_sepassword=this.se_password;
          var this_email=this.email;
          var this_yzm=this.yzm;
          var reg=/^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
         
          if(this_password==''||this_sepassword=='')
          {
             this.$alert('密码不能为空！', '', {
                        confirmButtonText: '确定',
                        center:true,
                      });
          }
           else if(this_password.length<6||this_password.length>13)
          {
             this.$alert('密码不能小于6位或者大于13位！！', '', {
                        confirmButtonText: '确定',
                        center:true,
                      });
          }
          else if(this_password!==this_sepassword)
          {
            this.$alert('两次密码不一致！', '', {
                        confirmButtonText: '确定',
                        center:true,
                      });
          }
          else if(this_email=='')
          {
             this.$message({
                    showClose: true,
                    type:'warning',
                    message:'邮箱不能为空！'
                })
          }
          else if(!reg.test(this_email))
          {
              this.$alert('无效邮箱！', '', {
                        confirmButtonText: '确定',
                        center:true,
                      });
          }
           else if(this_yzm=='')
          {
             this.$alert('验证码不能为空！', '', {
                        confirmButtonText: '确定',
                        center:true,
                      });
          }
          else{
                this.$axios({
            method: "post",
            // headers:{
            //    'Content-Type':'application/x-www-form-urlencoded'
            // },
            url: "http://121.89.166.24:8080/reset", // 接口地址
            withCredentials: "true",
            params: {
               yzm:this_yzm,
               email:this_email,
               pwd: this_password,
            }
          })
          .then(response => {
           if(response.data=="error"){
             this.$alert('验证码错误！', '', {
                        confirmButtonText: '确定',
                        center:true,
                      });
           }
           else if(response.data=="error:执行错误")
           {
             this.$alert('邮箱未注册！', '', {
                        confirmButtonText: '确定',
                        center:true,
                      });
           }
           else{
                this.$message({
                    showClose: true,
                    type:'success',
                    message:'密码修改成功！'
                })
             this.$router.push('/');
           }
          })
          .catch(error => error => console.log(error, "error")); // 失败的返回
          }
  
        },
        returnlogin()
        {
          this.$router.push('/');
          
        }
    }
}
</script>

<style  scoped>
  #card2 {
   margin-left: 400px;
   margin-right: 400px;
   margin-top: 60px;
   height:350px;
  }
  #login{
    background-image: url("..\assets\star.jpg");
    background-repeat: no-repeat;
    background-size: cover;
     margin-top: 0px;
    height: 550px;
    
  }
 
  .el-form-item {
   margin-top: 10px; 
   
  }
   #ava
  {
    margin-left: 1000px;
    margin-right: 10px;
  }
 
  .input{
    width:300px;
    margin-left: 0px;
    margin-right: 50px;
  }
    .input2{
    width:225px;
    margin-left: 0px;
    margin-right: 10px;
  }
  .form_item{
     margin-left: 40px;
     margin-right: 0px;
  }
  #button1
  {
    margin-left: 5px;
    margin-right: 20px;
  }
   #button2
  {
    margin-left: 20px;
    margin-right: 20px;
  }
 


</style>