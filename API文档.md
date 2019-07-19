# 一、普通用户
## 活动相关
## 兼职相关

### 1、查看兼职分类

```
GET http://tim.natapp1.cc/buckmoo/user/part/category_list
```

参数

```
无
```

返回值

```json
{
	"code":0,
	"data":[
		{
			"categoryId":1, //分类Id
			"categoryName":"其他", //名称
			"categoryNum":0, //此分类下的兼职数目
			"createTime":1560308075000,
			"updateTime":1563299669000
		},
		{
			"categoryId":2,
			"categoryName":"代课/会议",
			"categoryNum":1,
			"createTime":1563310081000,
			"updateTime":1563310112000
		},
		{
			"categoryId":3,
			"categoryName":"传单",
			"categoryNum":0,
			"createTime":1563310103000,
			"updateTime":1563310103000
		},
		{
			"categoryId":4,
			"categoryName":"配送",
			"categoryNum":0,
			"createTime":1563310126000,
			"updateTime":1563310126000
		},
		{
			"categoryId":5,
			"categoryName":"抄写",
			"categoryNum":0,
			"createTime":1563310148000,
			"updateTime":1563310148000
		},
		{
			"categoryId":6,
			"categoryName":"辅导",
			"categoryNum":0,
			"createTime":1563310162000,
			"updateTime":1563310162000
		}
	],
	"msg":"成功"
}
```



### 2、分类兼职列表

此列表是审核通过的兼职

请求地址
```
GET http://tim.natapp1.cc/buckmoo/user/part/part_list
```
参数
```
pageindex : Integer 分页索引（从0开始）
category : Integer 分类Id
```
返回值示例
```json
{
	"code":0,
	"data":{
		"pageCount":1,
		"partInfoList":[
			{
				"createTime":1560307214000,  //创建时间
				"creatorPhone":"13772812803", //联系方式
				"employSex":3,	//1男、2女、3不限
				"partAddress":"西安工程大学临潼校区A326", //地点
				"partCategory":2, //分类Id
				"partCreator":"oxrwq0xrKKyqiAGE8O9TM3L1yaQY", //发布者openid
				"partEnd":1563255614000, //结束时间
				"partId":"1560260414182640052", //兼职Id
				"partMoney":30, //兼职金额
        "partMoneyShow", //抽成后兼职支付金额
				"partName":"周四大学英语代课", //标题
				"partOverview":"周四早上34节大学英语代课，要求女生，临潼校区A326", //描述
				"partStart":1563457214000, //兼职开始时间
				"partStatus":2, //兼职状态
				"partTime":"只要点名了就可以走，记住时间是周四34节", //时间备注
				"updateTime":1563394788000 //最后更新信息时间
			},
			{
				"createTime":1560307214000,
				"creatorPhone":"13772812803",
				"employSex":3,
				"partAddress":"西安工程大学临潼校区A326",
				"partCategory":2,
				"partCreator":"oxrwq0zPbgTB-gV9Y4Q-hN4g25Fk",
				"partEnd":1563255614000,
				"partId":"1560260414182640053",
				"partMoney":30,
				"partName":"周四大学英语代课",
				"partOverview":"周四早上34节大学英语代课，要求女生，临潼校区A326",
				"partStart":1563457214000,
				"partStatus":2,
				"partTime":"只要点名了就可以走，记住时间是周四34节",
				"updateTime":1563394789000
			}
		]
	},
	"msg":"成功"
}
```

兼职状态参考值:

```java
@Getter
public enum  PartTimeStatusEnum implements CodeEnum{
    NO_PAY(0, "未付款 & 未审核"),
    NEW_PART(1, "必须付款 & 未审核"),
    NOT_PASS(2, "审核失败 & 已退款"),
    PASS_PAY(3, "审核通过 & 已发布"),
    TAKE_ORDER(4, "已经接单"),
    FINISH_ORDER(5, "已经完成"),
    FINISH_CREATE(6, "发布方确认已完成"),
    CANCEL_CREATE(7, "取消订单 未付款"),
    CANCEL_PAY(8, "取消订单 已付款/接单"),
    OTHER(9, "其他情况")
}
```

### 3、发布兼职信息

```
POST http://tim.natapp1.cc/buckmoo/user/part/create
```

参数

```
partName 兼职名称 String
partCategory 兼职分类 Integer
partAddress 兼职地点 String
partOverview 描述 String
partStart 开始时间 Long
partEnd 结束时间 Long
partTime 时间的附加描述 String
partMoney 支付金额 Double
partRemark 备注信息 String
employSex 接受条件 Integer 1男、2女、3男女不限
creatorPhone 联系方式(手机号码)
```

返回值

```json
{
	"code":2,
	"msg":"请先登录"
}

{
	"code":1,
	"msg":"网络繁忙"
}

{
	"code":0,
	"data":{
		"creatorPhone":"15291418231",
		"employSex":3,
		"partAddress":"不限",
		"partCategory":1,
		"partEnd":1563269870,
		"partId":"1563348836534761769",
		"partMoney":9900,
    "partMoneyShow": 9890,
		"partName":"SpringBoot商城毕业设计",
		"partOverview":"SpringBoot商城毕业设计，非常方便，待遇优厚！！",
		"partRemark":"备注信息：无",
		"partStart":1563269870,
		"partStatus":0,
		"partTime":"时间最多两个月，三个月也行"
	},
	"msg":"成功"
}
```

### 4、查看发布兼职 (分状态)

```
GET http://tim.natapp1.cc/buckmoo/user/part/created_list
```

参数

```
status Integer 参考(2、分类兼职列表)中兼职信息状态
pageindex Integer 分页索引
```

返回值

```json
{
	"code":0,
	"data":{
		"pageCount":2, //分页数量
		"partInfoList":[
			{
				"createTime":1563395610000,
				"creatorPhone":"12321313123",
				"employSex":3,
				"partAddress":"不限",
				"partCategory":1,
				"partCreator":"oxrwq0zPbgTB-gV9Y4Q-hN4g25Fk",
				"partEmploy":"",
				"partEnd":1563270000,
				"partId":"1563348810541158695",
				"partMoney":9900,
        "partMoneyShow": 9890,
				"partName":"SpringBoot商城毕业设计",
				"partOverview":"SpringBoot商城毕业设计，非常方便，待遇优厚！！",
				"partRemark":"备注信息：无",
				"partStart":1563270000,
				"partStatus":2,
				"partTime":"时间最多两个月，三个月也行",
				"updateTime":1563400055000
			},
			{
				"createTime":1563317467000,
				"creatorPhone":"13772812803",
				"employSex":1,
				"partAddress":"不限",
				"partCategory":5,
				"partCreator":"oxrwq0zPbgTB-gV9Y4Q-hN4g25Fk",
				"partEnd":1563270000,
				"partId":"1563270667200359519",
				"partMoney":75,
        "partMoneyShow": 9890,
				"partName":"抄写论文 -1000字50元",
				"partOverview":"1000字50元，抄完即可通知抄写结束，非常方便",
				"partRemark":"备注信息：无",
				"partStart":1563270000,
				"partStatus":2,
				"partTime":"时间最多两天，抄3000字左右",
				"updateTime":1563399913000
			},
			{
				"createTime":1563311583000,
				"creatorPhone":"13772812803",
				"employSex":1,
				"partAddress":"西安工程大学临潼校区",
				"partCategory":3,
				"partCreator":"oxrwq0zPbgTB-gV9Y4Q-hN4g25Fk",
				"partEmploy":"",
				"partEnd":1563264783000,
				"partId":"1563264783008222064",
				"partMoney":40,
				"partName":"周三代课",
				"partOverview":"这是详细描述信息",
				"partStart":1563264783000,
				"partStatus":2,
				"partTime":"对兼职时间的一个补充",
				"updateTime":1563400051000
			},
			{
				"createTime":1560307214000,
				"creatorPhone":"13772812803",
				"employSex":3,
				"partAddress":"西安工程大学临潼校区A326",
				"partCategory":2,
				"partCreator":"oxrwq0zPbgTB-gV9Y4Q-hN4g25Fk",
				"partEmploy":"",
				"partEnd":1563255614000,
				"partId":"1560260414182640053",
				"partMoney":30,
        "partMoneyShow": 9890,
				"partName":"周四大学英语代课",
				"partOverview":"周四早上34节大学英语代课，要求女生，临潼校区A326",
				"partStart":1563457214000,
				"partStatus":2,
				"partTime":"只要点名了就可以走，记住时间是周四34节",
				"updateTime":1563400041000
			}
		]
	},
	"msg":"成功"
}
```

### 5、查看接手兼职 (分状态)

和`4、查看我发布的兼职信息`一模一样

```
http://tim.natapp1.cc/buckmoo/user/part/accepted_list?status=3&pageindex=0
```

参数

```
status Integer 参考(2、分类兼职列表)中兼职信息状态
pageindex Integer 分页索引
```

返回值

```json
{
	"code":0,
	"data":{
		"pageCount":1,
		"partInfoList":[
			{
				"createTime":1560307214000,
				"creatorPhone":"13772812803",
				"employSex":2,
				"partAddress":"西安工程大学临潼校区A326",
				"partCategory":3,
				"partCreator":"oxrwq0zPbgTB-gV9Y4Q-hN4g25Fk",
				"partEmploy":"oxrwq0zPbgTB-gV9Y4Q-hN4g25Fk",
				"partEnd":1563255614000,
				"partId":"1560260414182640055",
				"partMoney":30,
        "partMoneyShow": 9890,
				"partName":"周四大学英语代课",
				"partOverview":"周四早上34节大学英语代课，要求女生，临潼校区A326",
				"partStart":1563457214000,
				"partStatus":3,
				"partTime":"只要点名了就可以走，记住时间是周四34节",
				"updateTime":1563400666000
			},
			{
				"createTime":1560307214000,
				"creatorPhone":"13772812803",
				"employSex":2,
				"partAddress":"西安工程大学临潼校区A326",
				"partCategory":3,
				"partCreator":"oxrwq0xrKKyqiAGE8O9TM3L1yaQY",
				"partEmploy":"oxrwq0zPbgTB-gV9Y4Q-hN4g25Fk",
				"partEnd":1563255614000,
				"partId":"1560260414182640054",
				"partMoney":30,
        "partMoneyShow": 9890,
				"partName":"周四大学英语代课",
				"partOverview":"周四早上34节大学英语代课，要求女生，临潼校区A326",
				"partStart":1563457214000,
				"partStatus":3,
				"partTime":"只要点名了就可以走，记住时间是周四34节",
				"updateTime":1563400656000
			}
		]
	},
	"msg":"成功"
}
```



### 6、查看发布兼职 (不分状态)

```
GET http://tim.natapp1.cc/buckmoo/user/part/all_created
```

参数

```
pageindex 分页参数
```

返回值 `4、查看发布兼职 (分状态) 一样`

### 7、查看接手兼职  (不分状态)

```
GET http://tim.natapp1.cc/buckmoo/user/part/all_accepted 
```

参数

```
pageindex 分页参数
```

返回值 `5、查看接手兼职 (分状态) 一样`



### 8、发布兼职付款

```
GET http://tim.natapp1.cc/buckmoo/user/pay/create
```

参数

```
partId : String 兼职Id
returnUrl : String 支付完成后跳转地址
```

返回值

H5 支付页面



## 账户相关

### 1、用户基本信息获取
```
GET http://tim.natapp1.cc/buckmoo/user/info/show
```
参数
```
openid : String
```
返回值
```json
{
	"code":1,
	"msg":"用户信息为空"
}

{
	"code":0,
	"data":{
		"createTime":1563307248000, //用户注册时间
		"openId":"oxrwq0zPbgTB-gV9Y4Q-hN4g25Fk", //用户openid
		"updateTime":1563307248000,	//最后更新时间
		"userAddress":"泽西岛",	//地点
		"userGrade":0, //用户积分
		"userIcon":"http://thirdwx.qlogo.cn/mmopen/vi_32/bxVEQxwmOLibgHtYurJxvW0yicXLVcTCUiaDQDqibEyoIKwS7ZRdOsZL02RibF79vdNt6cFEYU1v53r1plygOAL60hw/132", 	//用户头像
		"userMember":0,	//会员级别
		"userName":"Tim",	//用户昵称
		"userSex":1	//性别
	},
	"msg":"成功"
}
```

### 



### 

# 二、活动发布方

## 活动相关
## 账户相关
## 支付相关

# 三、超级管理员
## 活动相关
## 兼职相关
## 账户相关
## 支付相关