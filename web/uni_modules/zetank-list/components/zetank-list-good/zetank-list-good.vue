<template>
	<view class="u-skeleton">
		 <!-- 宫格类样式 -->
		<view class="recommend_goods" v-if="modes == true">
			<view v-for="(item, index) in newList" :key="index" class="goods">
				<view class="top" @tap="jumpDetails(item)" >
					<image class="cover u-skeleton-fillet" :lazy-load="true" :src="item.img" mode="scaleToFill"></image>
				</view>
				<view class="bottom">
					<view class="goods_name u-skeleton-fillet" @tap="jumpDetails(item)">
						{{item.title}}
					</view>
					<view class="price u-skeleton-fillet">
						<text class="text1" :style="'color: '+color">￥ {{item.money}}</text>
						<text class="text2">￥ {{item.hmoney}}</text>
					</view>
					<view class="goumai">
						<view class="g_left u-skeleton-fillet">
							<text v-if="item.youhui == true" class="u-skeleton-fillet">优惠券</text>
							<text v-if="item.baoyou == true" class="u-skeleton-fillet">包邮</text>
						</view>
						<view class="g_right u-skeleton-fillet" @tap="addCart(item)">
							<u-icon name="shopping-cart-fill" :style="'color: '+color" size="43"></u-icon>
						</view>
					</view>
		      </view>
		  </view>
		  <view class="place">
		  </view>
		</view>
		
		<!-- 列表类样式 -->
		<view class="list_mode" v-if="modes == false">
			<view class="goods_list" v-if="newList.length !== 0">
				<view v-for="(item, index) in newList" :key="index" class="goods_item ">
					<view>
						<image :src="item.img" lazy-load="true" @tap="jumpDetails(item)" class="u-skeleton-fillet"></image>
					</view>
					<view class="goods_right">
						<view class="goods_name u-skeleton-fillet" @tap="jumpDetails(item)">{{item.title}}</view>
						<view class="numbers">
							<text v-if="item.youhui == true" class="u-skeleton-fillet">优惠券</text>
							<text v-if="item.baoyou == true" class="u-skeleton-fillet">包邮</text>
						</view>
						<view class="price">
							<text class="money u-skeleton-fillet" :style="'color: '+color">￥{{item.money}}</text>
							<text class="hx_money u-skeleton-fillet">￥{{item.hmoney}}</text>
							<view class="gouwuche u-skeleton-fillet" @tap="addCart(item)">
								<u-icon name="shopping-cart-fill" size="43" :style="'color: '+color"></u-icon>
							</view>
						</view>
					</view>
				</view>
			</view>
		</view>
		<u-skeleton  :loading="loading" :animation="true"></u-skeleton>
	</view>
</template>

<script>
	export default {
	  data() {
	    return {
		  nowList: {},
	      newList: [],
		  
	    };
	  },
	  props: {
	    color: {
	      type: String,
		  default:'#55aaff'
	    },
	    list: {
	      type: Array,
		  default:null
		  /*
		  			[
		  				{
		  					title: '标题',
		  					type: 1,
		  					goods_id: 201,
		  					money: '价格',
		  					hmoney: '划线价格',
		  					img: '主图',
		  					youhui: true,//是否优惠
		  					baoyou: false,//是否包邮
		  					status: 1, //商品过期状态  0正常  1已失效
		  					stock: 600,//库存
		  					
		  								}
		  			]
		  */
	    },
	    modes: {
	      //控制显示宫格类样式或者列表类样式
	      type: Boolean,
	      default: true
	    },
		loading:{
			style:Boolean,
			default:true
		}

	  },
	  created() {
		this.setDataList(this.list)
	  },
	  watch:{
		list(value){
			this.setDataList(value)
		}
	  },
	  methods: {
		setDataList(newVal){
			this.newList = this.list
		},
	    addCart(item) {
	      //加入购物车弹出对话框
		  console.log('点击了',item)
			this.$emit('addCart',item)

	    },
	    jumpDetails(e) {
	      this.$emit('clicklist',e)
	    }
	  }
	};
</script>

<style lang="scss" scoped>
	.recommend_goods {
	  padding: 20upx;
	  column-count: 2;  /*分为两列  用于瀑布流*/
	  column-gap: 20upx;
	  
	}

	.goods {
	  height: 100%;
	  overflow: auto;
	  margin-bottom: 20upx;
	  break-inside: avoid; /*用于瀑布流*/
	  border-radius: 10upx;
	  box-sizing: content-box;
	  box-shadow: 0upx 0upx 10upx  #eee;
	  &:first-child{
		  margin-top: 0;
	  }
	  
	}
	.goods .top {
	  height: 45vw;
	  overflow: hidden;
	  // position: relative;
	  background-color: #ffffff;
	}
	
	.top .cover {
	  width: 100%;
	  height: 100%;
	  display: flex;
	  align-items: center;
	}
	
	.top .tags {
	  width: 65upx;
	  height: 65upx;
	  position: absolute;
	  top: 0;
	  left: 0;
	}
	
	.bottom {
	  padding: 15upx;
	  background-color: #ffffff;
	  overflow: hidden;
	}
	
	.goods_name {
	  /* height: 66upx; */
	  font-size: 28upx;
	  word-break: break-all;
	  overflow: hidden;
	  text-overflow: ellipsis;
	  display: -webkit-box;
	  -webkit-box-orient: vertical;
	  -webkit-line-clamp: 2;
	  font-weight: bold;
	}
	.bottom .price{
	  margin-top: 15upx;
	}
	.bottom .price .text1{
	  font-size: 28upx;
	  font-weight: bold;
	  color: #55aaff;
	}
	.bottom .price .text2{
	  font-size: 20upx;
	  color: #a0a0a0;
	  text-decoration: line-through;
	  padding-left: 15upx;
	}
	.goumai{
	  margin-top: 10upx;
	}
	.g_left{
	  font-size: 24upx;
	  float: left;
	  align-items: center;
	  width: 250rpx;
	  height: 50rpx;
	}
	.g_left text{
	  display: inline-block;
	  height: 35upx;
	  line-height: 35upx;
	  padding: 0 10upx;
	  background-color: #FAEFF7;
	  border-radius: 10upx;
	  margin-right: 20upx;
	  color: #000000;
	}
	.g_right{
	  float: right;
	  font-size: 28upx;
	  color: #55aaff;
	}
	.mask{
	  width: 100%;
	  height: 100%;
	  position: fixed;
	  top: 0;
	  left: 0;
	  background: #000;
	  z-index: 900;
	  opacity: 0.7;
	}
/* 列表类样式 */
.list_mode{
  padding: 20upx 4% 0upx;
  z-index: 10;
  background-color: #FFFFFF;
}
.goods_list {
  overflow: hidden;
}

.goods_list .goods_item {
  align-items: center;
  border-bottom: 1upx solid #eee;
  padding-bottom: 10upx;
  margin-bottom: 15upx;
  overflow: hidden;
  position: relative;
  box-shadow: 0upx 0upx 10upx  #eee;
  display: flex;
  &:last-of-type{
	  border-bottom: none;
  }
}

.goods_item image {
  width: 200upx;
  height: 200upx;
  float: left;
  border-radius: 10upx;
  margin-right: 5upx;
}
.goods_item .tags{
  width: 60upx;
  height: 60upx;
  position: absolute;
  top: 0;
  left: 0;
}
.goods_right {
  /* float: left; */
  padding:  10upx;
  overflow: hidden;
  flex: 1;
}

.goods_right .goods_name {
  font-size: 28upx;
  overflow: hidden;
  font-weight: bold;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 2;
  color: #333;
  min-height: 50upx;
    height: 90rpx;
}
.goods_right .numbers{
  font-size: 20upx;
  line-height: 30upx;
  overflow: hidden;
  margin-top: 30upx;
  display: flex;
  align-content: center;


}
.goods_right .numbers text{
 display: inline-block;
 height: 35upx;
 line-height: 35upx;
 padding: 0 10upx;
 background-color: #FAEFF7;
 border-radius: 10upx;
 margin-right: 20upx;
 color: #000000;
}
.goods_right .price{
  line-height: 40upx;
  font-size: 24upx;
  overflow: hidden;
  margin-top: 20upx;
}
.price .money{
  margin-right: 20upx;
  font-size: 32upx;
  font-weight: bold;
  color: #55aaff;

  
}
.hx_money{
  text-decoration: line-through;
  color: #999;
  font-size: 22upx;
  
}
.gouwuche{
  font-size: 32upx;
  float: right;
  margin-right: 20upx;
  color: #55aaff;
}
</style>
