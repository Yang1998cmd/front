<template>

  <div id="attenions"> 
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
<el-container  >
  <el-aside  width="1000px">
    <div>
        <div style="height:30px"></div>  
        <el-row >
            <el-col v-for="(item,i) in areaData" :key="i"   style="width: 400px;height: 100px; margin-left: 10px;margin-top: 10px;">
            <el-card class="col">
              
                <div class="header-img">
                    <el-avatar class="header-img" :size="60" :src="item.headImg" style="cursor: pointer" @click.native="TurnToPer(item.id)"></el-avatar>
                </div>
                <div class="author-name">
                    <span >{{item.name}}</span>
                </div>
                <div v-if="item.both_attention==0" class="switch">
                  <el-button @click="changeswitch(i)" class="switch">互相关注</el-button> 
                </div>
                 <div v-else-if="item.both_attention==1" class="switch">
                   <el-button @click="changeswitch(i)" class="switch">已关注</el-button> 
                </div>
            </el-card>
            </el-col>
           
        </el-row>   
    </div>
  </el-aside>
  <el-main id='main'>
    <el-col >
      <el-card id='card'>
            <el-avatar :size="80" :src="myHeader" style="cursor: pointer" @click.native="TurnToOwn(myId)"></el-avatar>
            <div class='author_div' >{{myName}}</div>
            <el-divider></el-divider>
          
          <div class='author_div'>
          <div style="width:100px; height:50px; text-align:right;line-height:50px;float:left; display:inline;cursor: pointer" @click="TurnToWeibo">微博</div>
          <div style="font-family: Hiragino Sans GB;width:100px; height:50px; text-align:center;line-height:50px; float:left; display:inline">{{weiboCount}}</div>
          </div>
          <div class='author_div'>
          <div style="width:100px; height:50px; text-align:right;line-height:50px;float:left; display:inline;cursor: pointer" @click="TurnToAttention">关注</div>
          <div style="font-family: Hiragino Sans GB;width:100px; height:50px; text-align:center;line-height:50px; float:left; display:inline">{{attention}}</div>
          </div>
          <div class='author_div'>
          <div style="width:100px; height:50px; text-align:right;line-height:50px;float:left; display:inline;cursor: pointer;" @click="TurnToFans">被关注</div>
          <div style="font-family: Hiragino Sans GB;width:100px; height:50px; text-align:center;line-height:50px; float:left; display:inline">{{fans}}</div>
          </div>
      </el-card> 
       <el-card id='_card'>
         <el-row style="width:200px; height:50px; text-align:left;line-height:50px; float:left; display:inline"><span style="color:red">热搜</span></el-row>
           <el-col>
               <el-divider></el-divider>
           <el-row v-for="(it,i) in hot.hot" :key="i">
             <div v-if="i<5" style="width:200px; height:50px;line-height:50px;cursor: pointer" @click="search2(it[0])">{{it[0]}}</div>
           </el-row>  
           </el-col> 
      </el-card> 
    </el-col>
  </el-main>
</el-container>
</div>

</template>

<script>
export default {
    name:'ArticleComment',
    data(){
        return {
            test:'1',
            value1: false,
            value2: true,
            text:'未关注',
            input:'',
            myHeader:'',
            myName:'',
            myId:'',
            weiboCount:'0',
            attention:'0',
            fans:'0',
            areaData:[],
            hot:[]
            }
    },
    beforeRouteEnter(to, from, next){//后来改的
        next(vm=>{
            vm.getData1();
            vm.getData2();
            vm.getHot();
        })
    },
 
    methods: {
      changeswitch(i)
      {

          // console.log(this.areaData[i].both_attention,"f")
          if(this.areaData[i].both_attention==0)//互相关注
          {
            // console.log(this.areaData[i].both_attention,"s")
            this.attention=this.attention-1
            this.deleteLink(i)
            this.areaData.splice(i,1);
            // this.getData2()
          }
          else if(this.areaData[i].both_attention==1)
          {
            // console.log(this.areaData[i].both_attention,"t")
            this.attention=this.attention-1
            this.deleteLink(i)
            this.areaData.splice(i,1);
            // this.getData2()
          
          }
          else
          {
            console.log(this.areaData[i].both_attention,"555")
          }
      },
       handleSelect(key, keyPath) {
         console.log(key, keyPath);
       },
       getData1()
        {
           
            this.$axios({
            method: "post",
            url: "http://121.89.166.24:8080/info/update", // 接口地址
            withCredentials: "true",
          })
          .then(response => {//后来改的
                    this.weiboCount=response.data.weiboCount
                    this.attention=response.data.attention
                    this.fans=response.data.fans
                }); 
        },
        getData2() {
            this.$axios(
                {method: "post",
            url: "http://121.89.166.24:8080//list/attention", // 接口地址
            withCredentials: "true",
          })
            .then(response => {//后来改的
                    console.log(response.data)
                    this.myId=response.data.myId
                    this.myHeader=response.data.myHeader
                    this.myName=response.data.myName
                    this.areaData=response.data.fans_or_attention
                }); 
        },
        getHot()
        {
            this.$axios({
            method: "post",
            url: "http://121.89.166.24:8080/hot", // 接口地址
            withCredentials: "true",
          })
          .then(response => {//后来改的
                    console.log(response.data)
                    this.hot=response.data
                    console.log(this.hot[0])
                }); 

        },
        TurnToWeibo()
        {
            this.$router.push('/home');
        },
         TurnToFans()
        {
            this.$router.push('/FansFri');
        },
         TurnToAttention()
        {
            // this.$router.push('/AttentionFri');
        },
        TurnToPer(i)
        {
          console.log("test")
          this.$router.push({
          name: 'personal',
          params: {
            id: i
          }
        })

        },
         TurnToOwn(i)
        {
          this.$router.push({
          name: 'own',
          params: {
            id: i
          }
        })

        },
        link(i)
        {
            var id=this.areaData[i].id
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
        deleteLink(i)
        {
            var id=this.areaData[i].id
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
         search2(i)
        {
            
            this.$router.push({
             name: 'search',
             params: {
                input: i
            } })
           
        },
        toHot()
        {
             this.$router.push({
                name: 'HotWei',
                params: {
                    dat: this.myId
                } })
        },
    },    
  }
</script scoped>


<style lang="stylus" scoped>
  #aside
  {
      margin-left: 10px;
      margin-right: 10px;
      margin-top: 30px;
      height: 100%;
  }
  #attenions{
    background-image: url("..\assets\star.jpg");
    background-repeat: no-repeat;
    background-size: cover;
    margin-top: 0px;
    height: 100%;
    
  }
 
     
  
   #main
   {
      margin-right: 10px;
      margin-top: 10px;
      height: 100%;
      // width 400px
   }
     

   #ava
   {
      margin-left: 600px;
      margin-right: 10px;
   }
   
   .col
   {
      width: 100%;
      height: 100%;
   }
  
   #card
   {
       height: 350px;
   }
    

   #_card
   {
       margin-top: 50px;
       height: 400px;
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
 