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
          <el-form label-width='50px'>
              <el-form-item class='form_item' label='账号' >
                      <el-input class='input' v-model='id' placeholder="请输入账号" suffix-icon="el-icon-edit"></el-input>
              </el-form-item>
              <el-form-item class='form_item' label='密码'>
                      <el-input class='input' v-model='password' type="password" placeholder="请输入密码" suffix-icon="el-icon-edit"></el-input>
              </el-form-item>
              <el-form-item>
                      <el-button id='button1' type='primary' @click='login'>登录</el-button>
                      <el-button id='button2' @click='register'>注册</el-button>
                      <el-button id='button3' @click='change' type='text'>忘记密码？</el-button>
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
         id: '1091756452',
         password: '666666666',
       }
    },
    methods:{
       handleSelect(key, keyPath) {
         console.log(key, keyPath);
       },
        register(){
            this.$router.push('/register');
        },
        change(){
            this.$router.push('/changepassword');
        },
        login()
        {
          var this_id=this.id;
          var this_password=this.password;
          if(this_id==''||this_password=='')
          {
             this.$alert('账号或者密码不能为空！', '', {
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
            url: "http://121.89.166.24:8080/login", // 接口地址
            withCredentials: "true",
            params: {
               user: this_id ,  // 传接口参
               pwd: this_password
            }
          })
          .then(response => {
            if(response.data=="ok:登录ok")
            {
               this.$message({
                    showClose: true,
                    type:'success',
                    message:'"登录成功！'
                })
              this.$router.push('/home');
               const loading = this.$loading({
                lock: true,
                text: 'Loading',
                spinner: 'el-icon-loading',
                background: 'rgba(0, 0, 0, 0.7)'
              });
              setTimeout(() => {
                loading.close();
              }, 3000);
            }   
            else if(response.data=="error0:用户不存在"){
               this.$alert('用户不存在！', '', {
                        confirmButtonText: '确定',
                        center:true,
                      });
            }
            else{
              this.$alert('密码错误！', '', {
                        confirmButtonText: '确定',
                        center:true,
                      });
            }
            // console.log("开始了0")
            //  var str = '{"MyName":"帅气啊刘","myId":"1091756452","attention":0,"comment":[{"like":"0","comment":"女权出击","id":"1091756452","time":"2020-05-23 11:43:11.0","reply":[]},{"like":"0","comment":"女权出击","id":"1091756452","time":"2020-05-23 11:43:11.0","reply":[]},{"like":"0","comment":"女权出击","id":"1091756452","time":"2020-05-23 11:43:11.0","reply":[]},{"like":"0","comment":"女权出击","id":"1091756452","time":"2020-05-23 11:43:11.0","reply":[]},{"like":"0","comment":"女权出击","id":"1091756452","time":"2020-05-23 11:43:11.0","reply":[]},{"like":"0","comment":"女权出击","id":"1091756452","time":"2020-05-23 11:43:11.0","reply":[]},{"like":"0","comment":"上一轮，柏林赫塔客场3-0战胜霍芬海姆，现在4-0大胜柏林联合，两场全胜+净胜7球。\n\n克林斯曼下课后，54岁德国教练拉巴迪亚接班执起柏林赫塔的教鞭，前两场德甲比赛就给球迷带来了巨大的惊喜。","id":"1091756452","time":"2020-05-23 11:43:11.0","reply":[]},{"like":"0","comment":"中国女排队长朱婷是非常受球迷们喜爱的一位球员，朱婷作为中国女排的头号球星已经带领女排国家队取得了三次世界大赛的冠军，在欧洲职业联赛中，朱婷也取得了无比辉煌的成就。目前的朱婷可谓是中国排坛的一姐，作为中国体坛少有的超级巨星，朱婷在球迷们心目之中具备很高的地位。","id":"1091756452","time":"2020-05-23 11:43:11.0","reply":[{"toId":1,"like":"0","FromId":1091756452,"comment":"你的18n了？","from":"帅气啊刘","to":"ami","fromId":1091756452},{"toId":1091756452,"like":"0","FromId":1,"comment":"你的18n了？","from":"ami","to":"帅气啊刘","fromId":1},{"toId":1,"like":"0","FromId":2,"comment":"就你这5n？","from":"ami","to":"ami","fromId":2},{"toId":2,"like":"0","FromId":3,"comment":"别bb，你是傻子","from":"ami","to":"ami","fromId":3},{"toId":3,"like":"0","FromId":1,"comment":"???????","from":"ami","to":"ami","fromId":1}]},{"like":"0","comment":"最近，朱婷受到了不公正的待遇。中国女排代言的某火腿肠商家发了一个短视频，视频中他们对于包装上朱婷的肖像进行了多种方式的遮挡，引发了巨大争议，不少球迷认为这是厂家对朱婷的不尊重。让球迷们气愤的是，这个商家在微博上居然采用了指桑骂槐的做法，发布谐音猪皮，更引起了球迷们的怒火。","id":"1091756452","time":"2020-05-23 11:43:11.0","reply":[]}],"fans":0}';
            //  var json = JSON.parse(str);
            //  console.log("开始了1")
            //  console.log(json)
          })
          .catch(error => error => console.log(error, "error")); // 失败的返回
          }
          
  
        }
    }
}
</script>

<style scoped>
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
   margin-top: 50px;
   
  }
   #ava
  {
    margin-left: 600px;
    margin-right: 10px;
  }
 
  .input{
    width:300px;
    margin-left: 0px;
    margin-right: 50px;
  }
  .form_item{
     margin-left: 80px;
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
   #button3
  {
    margin-left: 20px;
    margin-right: 5px;
  }

</style>