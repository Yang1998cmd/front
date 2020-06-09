<template>
  <div> 
    <el-backtop></el-backtop>
<el-menu default-active="/Attention" class="el-menu-demo"  mode="horizontal" @select="handleSelect" :router="true">
  <el-menu-item index="" ><i class="el-icon-chat-line-square"></i></el-menu-item>
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
<el-container>
  <el-aside  width="1000px">
    <div>
        <div style="height:30px"></div>      
        <div v-for="(item,i) in areaData.comments" :key="i" class="author-title reply-father">
            <el-avatar class="header-img" :size="40" :src="item.headImg" style="cursor: pointer" @click.native="TurnToPer(item.id)"></el-avatar>
            <div class="author-info">
                <span class="author-name">{{item.name}}</span>
                <span class="author-time">{{item.time}}</span>
            </div>
            <div class="icon-btn">
                <span @click="showReplyInput(i,item.name,item.id)"><i class="iconfont el-icon-s-comment"></i>{{item.commentNum}}</span>
                <span @click="addCommentLike(i,item.weiboId,item.like)"><i class="iconfont el-icon-thumb" ></i>{{item.like}}</span>
            </div>
            <div class="talk-box">
                <p>
                    <span class="reply">{{item.comment}}</span>
                </p>
                <el-row :gutter="6">
                    <el-col :span="6" v-for="(it,i) in item.img" :key="i">
                    <div style="width: 205px; height: 205px" > 
                    <el-image :src="it" :preview-src-list="item.img" style="width: 200px; height: 200px" :lazy="true"></el-image>
                    </div>
                    </el-col> 
                </el-row>
               
            </div>
            <div class="reply-box">
                <div v-for="(reply,j) in item.reply" :key="j" class="author-title">
                    <el-avatar class="header-img" :size="40" :src="reply.headImg" style="cursor: pointer" @click.native="TurnToPer(reply.fromId)"></el-avatar>
                    <div class="author-info">
                        <span class="author-name">{{reply.from}}</span>
                        <span class="author-time">{{reply.time}}</span>
                    </div>
                    <div class="icon-btn">
                        <span @click="showReplyInput(i,reply.from,reply.fromId)"><i class="iconfont el-icon-s-comment"></i>{{reply.commentNum}}</span>
                        <span @click="addReplyLike(i,j,reply.commentId,reply.like)"><i class="iconfont el-icon-thumb"></i>{{reply.like}}</span>
                    </div>
                    <div class="talk-box">
                        <p>
                            <span v-if="areaData.myName!=reply.to">回复 {{reply.to}}:</span>
                            <span class="reply">{{reply.comment}}</span>
                        </p>
                    </div>
                    <div class="reply-box">

                    </div>
                </div>
            </div>
            <div  v-show="_inputShow(i)" class="my-reply my-comment-reply">
                <el-avatar class="header-img" :size="40" :src="areaData.myHeader"></el-avatar>
                <div class="reply-info" >
                    <div tabindex="0" contenteditable="true" spellcheck="false" placeholder="输入评论..."   @input="onDivInput($event)"  class="reply-input reply-comment-input"></div>
                </div>
                <div class=" reply-btn-box">
                    <el-button class="reply-btn" size="medium" @click="sendCommentReply(i)" type="primary">发表评论</el-button>
                </div>
             </div>
        </div>
    </div>
  </el-aside>
  <el-main id='main'>
    <el-col >
      <el-card id='card'>
          <el-avatar :size="80" :src="areaData.myHeader" style="cursor: pointer" @click.native="TurnToOwn(areaData.myId)"></el-avatar>
          <div class='author_div'>{{areaData.myName}}</div>
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
               <div v-if="i<5" style="width:200px; height:50px;line-height:50px;cursor: pointer" @click="search2(it)">{{it}}</div>
           </el-row>  
           </el-col> 
      </el-card> 
    </el-col>
  </el-main>
</el-container>
</div>

</template>

<script>
const clickoutside = {
    // 初始化指令
    bind(el, binding, vnode) {
    function documentHandler(e) {
    // 这里判断点击的元素是否是本身，是本身，则返回
        if (el.contains(e.target)) {
            return false;
        }
    // 判断指令中是否绑定了函数
        if (binding.expression) {
            // 如果绑定了函数 则调用那个函数，此处binding.value就是handleClose方法
            binding.value(e);
        }
    }
    // 给当前元素绑定个私有变量，方便在unbind中可以解除事件监听
    el.vueClickOutside = documentHandler;
    document.addEventListener('click', documentHandler);
    },
    update() {},
    unbind(el, binding) {
    // 解除事件监听
    document.removeEventListener('click', el.vueClickOutside);
    delete el.vueClickOutside;
  },
};
export default {
    name:'ArticleComment',
    data(){
        return {
            replyComment:"",
            to:'',
            Toid: -1,

            weiboCount:'0',
            attention:'0',
            fans:'0',
            uploadData:{
                WeiBoContext:'测试',
                time:'2020-05-22 00:00:00'
            },
            input:'',
            dialogImageUrl: '',
            dialogVisible: false,
            disabled: false,

            filelistnum: 0,
            filelist:[],
            areaData:[],
             hot:[]
            }
    },
    directives: {clickoutside},
    beforeRouteEnter(to, from, next){//后来改的
        next(vm=>{
            vm.getData2();
            vm.firstGetData();
            vm.getHot();
        })
    },

    methods: {
       handleSelect(key, keyPath) {
         console.log(key, keyPath);
       },
       Success(response, file, fileList) {//文件上传成功钩子
        this.filelist=fileList
        this.$refs.upload.clearFiles()
        console.log(response,file,fileList)    //服务器返回的图片信息，可以提交到表格
        this.$message({
          message: '上传成功',
          type: 'success'
        })
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
        firstGetData() {
            this.$axios({
            method: "post",
            url: "http://121.89.166.24:8080/response/friend", // 接口地址
            withCredentials: "true",
          })
            .then(response => {//后来改的
                    console.log(response.data)
                    this.areaData=response.data
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
        getData2()
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
          TurnToPer(i)
        {
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
            this.$router.push('/AttentionFri');
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
                    dat: this.areaData.myId
                } })
        },
        showReplyInput(i,name,id){
           
            this.areaData.comments[this.areaData.index].inputShow = false
            this.areaData.index =i
            this.areaData.comments[i].inputShow = true
            this.to = name
            console.log(this.Toid,"好烦啊")
            this.Toid = id
            console.log(this.Toid)
        },
        _inputShow(i){
            return this.areaData.comments[i].inputShow  
        },
        sendComment(){
            if(!this.replyComment){
                 this.$message({
                    showClose: true,
                    type:'warning',
                    message:'微博内容不能为空'
                })
            }else if(this.filelistnum===0){
                this.$message({
                    showClose: true,
                    type:'warning',
                    message:'请添加图片'
                })
               
            }
            else{
                 this.uploadData.WeiBoContext=this.replyComment
                 this.uploadData.time=this.dateStr()
                 console.log(this.uploadData.time)
                 this.$refs.upload.submit()
                 var this_=this
                 setTimeout(function(){
                      this_.filelistnum=0//第一个参数为函数 你可以传入函数名 或一个匿名函数
                      let a ={}
                    let input =  document.getElementById('replyInput')
                    let time= this_.uploadData.time
                    a.name= this_.areaData.myName
                    a.comment =this_.replyComment
                    a.headImg = this_.areaData.myHeader
                    a.time = time
                    a.commentNum = 0
                    a.like = 0 
                    a.img=[]
                    // console.log(this_.filelist,"test0")
                    for(var i=0;i<this_.filelist.length;i++)
                    {
                        // console.log(this_.filelist[i].url,"test1")
                        a.img.push(this_.filelist[i].url)
                    }
                    // console.log(this_.filelist,"test2")
                    this_.areaData.comments.push(a)
                    this_.areaData.replyComment = ''
                    input.innerHTML = '';
                         },5000);
                
            }
        },
        addCommentLike(i,weiboId,like)
        {
              this.areaData.comments[i].like=like+1
              this.$axios({method: "post",
                        url: "http://121.89.166.24:8080/like/passage", // 接口地址
                        withCredentials: "true",
                         params: {
                            weiboid: weiboId ,  // 传接口参
                            }
                    })
                    .then(response => {//后来改的
                        }); 

        },
        addReplyLike(i,j,commentId,like)
        {
              this.areaData.comments[i].reply[j].like=like+1
              this.$axios({method: "post",
                        url: "http://121.89.166.24:8080/like/comment", // 接口地址
                        withCredentials: "true",
                         params: {
                            commentid:commentId ,  // 传接口参
                            }
                    })
                    .then(response => {//后来改的
                        }); 

        },
        sendCommentReply(i){
            if(!this.replyComment){
                 this.$message({
                    showClose: true,
                    type:'warning',
                    message:'评论不能为空'
                })
            }else{
                let a ={}
                let time= this.dateStr();
                a.from= this.areaData.myName
                a.to = this.to
                a.fromHeadImg = this.areaData.myHeader
                a.comment =this.replyComment
                var reply=this.replyComment
                a.time = time
                a.commentNum = 0
                a.like = 0
                this.areaData.comments[i].reply.push(a)
                this.areaData.comments[i].commentNum++
                this.replyComment = ''
                document.getElementsByClassName("reply-comment-input")[i].innerHTML = ""
                var _this=this
                console.log(this.Toid,_this.to,"??")
                this.$axios({method: "post",
                        url: "http://121.89.166.24:8080/upload/comment", // 接口地址
                        withCredentials: "true",
                         params: {
                            WeiboId:_this.areaData.comments[i].weiboId ,  // 传接口参
                            FromId:_this.areaData.myId,
                            ToId:_this.Toid,
                            CommentContext:reply,
                            Time:time
                            }
                    })
                    .then(response => {//后来改的
                        }); 
            }
            this.areaData.comments[i].inputShow = false
        },
        onDivInput: function(e) {
            this.replyComment = e.target.innerHTML;
        },
        
        dateStr(){
            //获取js 时间戳
           Date.prototype.Format = function (fmt) {  
                var o = {
                    "M+": this.getMonth() + 1, //月份 
                    "d+": this.getDate(), //日 
                    "h+": this.getHours(), //小时 
                    "m+": this.getMinutes(), //分 
                    "s+": this.getSeconds(), //秒 
                    "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
                    "S": this.getMilliseconds() //毫秒 
                };
                if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
                for (var k in o)
                if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
                return fmt;
            }
            var time=new Date().Format("yyyy-MM-dd hh:mm:ss");
            return time;
           
        }
    },    
  }
</script>


<style lang="stylus" scoped>
.my-reply
    padding 10px
    background-color #fafbfc
    .header-img
        display inline-block
        vertical-align top
    .reply-info    
        display inline-block
        margin-left 5px
        width 90%
        @media screen and (max-width:1200px) {
            width 80%
        }
        .reply-input
            min-height 20px
            line-height 22px
            padding 10px 10px
            color #ccc
            background-color #fff
            border-radius 5px
            &:empty:before
                content attr(placeholder)
            &:focus:before
                content none
            &:focus
                padding 8px 8px
                border 2px solid blue
                box-shadow none
                outline none
    .reply-btn-box
        height 25px
        margin 10px 0
        .reply-btn
            position relative
            float right
            margin-right 15px
.my-comment-reply
    margin-left 50px
    .reply-input
        width flex
.author-title:not(:last-child)
    border-bottom: 1px solid rgba(178,186,194,.3)
.author-title
    padding 10px
    .header-img
        display inline-block
        vertical-align top
    .author-info
        display inline-block
        margin-left 5px
        width 60%
        height 40px
        line-height 20px
        >span 
            display block
            cursor pointer
            overflow hidden
            white-space nowrap
            text-overflow ellipsis
        .author-name
            color #000
            font-size 18px
            font-weight bold
            text-align left
        .author-time
            font-size 14px
            text-align left
    .icon-btn
        width 30%
        padding 0 !important 
        float right
        @media screen and (max-width : 1200px){
            width 20%
            padding 7px
        }
        >span 
            cursor pointer
        .iconfont 
            margin 0 5px
    .talk-box
        margin 0 50px
        >p
           margin 0
           text-align left
        .reply
            font-size 16px
            color #000
    .reply-box
        margin 10px 0 0 50px
        background-color #efefef


  #aside
      margin-left 10px
      margin-right 10px
      margin-top 30px
      height 100%
     
  
   #main
      margin-right 10px
      margin-top 10px
      height 100%
      // width 400px

   #ava
    margin-left 600px
    margin-right 10px
  
   #card
    height 350px

   #_card
    margin-top 50px
    height 400px
 
   .author_div
    margin-top 2px
  
    
</style>
 