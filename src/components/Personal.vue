<template>

  <div id="personal"> 
    <el-backtop></el-backtop>  
<el-menu default-active="#" class="el-menu-demo"  mode="horizontal" @select="handleSelect" :router="true">
  <el-menu-item index="/" ><i class="el-icon-chat-line-square"></i></el-menu-item>
  <el-menu-item index="/Home" >首页</el-menu-item>
  <el-menu-item index="/Attention">关注</el-menu-item>
  <el-menu-item index="" @click="toHot">热搜</el-menu-item>
  <el-menu-item index="/WeiboIntroduce" >推荐</el-menu-item>
<el-input
  placeholder="请输入内容"
  v-model="input"
  clearable
  style="width:300px;margin-top:10px;margin-right:0px;margin-left:0px" @keyup.enter.native="search">
</el-input>
<el-button style="width:55px ;height:40px;margin-left:-6px;margin-right:550px" icon="el-icon-search" @click="search"></el-button>
  <!-- <el-avatar id='ava' icon="el-icon-user-solid"></el-avatar> -->
</el-menu>

    <div>
        <div style="height:30px"></div>  
        <el-card id="per"> 
          <div>
            <div id="le">
              <el-avatar :size="180" :src="Header" icon="el-icon-user-solid"></el-avatar>
            </div>
            <div id="le1">
              <div id="mid">
                <p style="font-size: 60px;font-weight:bold;">{{Name}}</p>
              </div>
              <el-divider></el-divider>
              <div id="mid1">
                <p>{{introduction}}</p>
              </div>
            </div>
            <div id="le2">
               <el-button type="" v-if="relation==0" @click="dochange">互相关注</el-button>
               <el-button type="" v-else-if="relation==1" @click="dochange">已关注</el-button>
               <el-button type="" v-else-if="relation==2" @click="dochange">被关注</el-button>
               <el-button type="" v-else-if="relation==3" @click="dochange">未关注</el-button>
            </div>
          </div>
        </el-card>
    
    </div>

</div>

</template>

<script>
export default {
    name:'ArticleComment',
    data(){
        return {
        
            input:'',
            Header:'',
            Name:'',
            Id:'',
            relation:'',
            introduction:'',
            }
    },
    beforeRouteEnter(to, from, next){//后来改的
        next(vm=>{
            var id=vm.$route.params.id
            vm.getData(id);
        })
    },
    methods: {
      changeswitch()
      {
          if(this.test==1)
          {
            this.test=0
          }
          else
          {
            this.test=1
          }
      },
      getaddress(response, file, fileList)
      {
          this.$refs.upload2.clearFiles()
          this.areaData.myHeader=response 
          for(var i=0;i<this.areaData.comments.length;i++)  
          {
              this.areaData.comments[i].headImg=response
          }
      },
       handleSelect(key, keyPath) {
         console.log(key, keyPath);
       },
       getData(i)
        {
           
            this.$axios({
            method: "post",
            url: "http://121.89.166.24:8080/friend/info", // 接口地址
            withCredentials: "true",
             params: {
               id: i ,  // 传接口参
            }
          })
          .then(response => {//后来改的
                this.Header=response.data.headImg
                this.Id=response.data.id
                this.Name=response.data.name
                this.relation=response.data.relationship
                this.introduction=response.data.introduction
                    console.log(response.data)
                }); 
        },
    
           link()
        {
            var id=this.Id
            this.$axios({
            method: "post",
            url: "http://121.89.166.24:8080/link", // 接口地址
            withCredentials: "true",
            params: {
               linked: id ,  // 传接口参
            }
          })
          .then(response => {//
                 
                }); 

        },
        deleteLink()
        {
            var id=this.Id
            this.$axios({
            method: "post",
            url: "http://121.89.166.24:8080/cancel/link", // 接口地址
            withCredentials: "true",
             params: {
               id: id ,  // 传接口参
            }
          })
          .then(response => {//        
                }); 
          
        },
        dochange()
        {
          if(this.relation==0)//互相关注
          {
            this.relation=2//被关注
            this.deleteLink()
          }
          else if(this.relation==1)//已关注
          {
            this.relation=3//未关注
            this.deleteLink() 
          }
          else if(this.relation==2)//被关注
          {
            this.relation=0//互相关注
            this.link() 
          }
           else if(this.relation==3)//未关注
          {
            this.relation=1//已关注
            this.link() 
          }


        },
         search()
        {
            if(this.input=="")
            {
                  this.$alert('亲，搜索不能为空！', '', {
                        confirmButtonText: '确定',
                        center:true,
                      });
            }
            else{
                this.$router.push({
                name: 'search',
                params: {
                    input: this.input
                } })
            }
        },
         toHot()
        {
             this.$router.push({
                name: 'HotWei',
                params: {
                    dat: this.Id
                } })
        },
    },    
  }
</script scoped>


<style lang="stylus" scoped>

  #personal{
    background-image: url("..\assets\star.jpg");
    background-repeat: no-repeat;
    background-size: cover;
    margin-top: 0px;
    height: 500px;
    
  }
  #per
  {
       height: 300px;
       width: 1000px;
       margin-left: 150px;
  }
     

   #ava
   {
      margin-left: 600px;
      margin-right: 10px;
   }
   
   #le
   {
       height: 200px;
       width: 200px;
       margin-left: 10px;
       margin-top: 30px;
       float:left; display:inline;
   }
   #le1
   {
       height: 250px;
       width: 600px;
       margin-left: 10px;
       float:left; 
       display:inline;
       text-align: left;
   }
   #le2
   {
     float:left; display:inline;
   }
   #mid
   {
     height: 60px;
   }
   .author_div
   {
        margin-top: 2px;
   }
   
    .header-img
    {
      margin-left: 10px;
      margin-right: 10px;
      width: 80px;
      height: 100%;
       float:left; display:inline;
    }
    .author-name
    {
      margin-left: 10px;
      margin-right: 10px;
      margin-top: 18px;
      width: 80px;
      height: 100%;
       float:left; display:inline;
    }
    
    .switch
    {
      margin-left: 10px;
      margin-right: 10px;
      margin-top: 8px;
      width: 120px;
      height: 100%;
     float:left; display:inline;
    }
    
</style>
 