# 一、普通用户
## 活动相关
## 兼职相关

### 1、查看兼职分类

v1.1 更新说明：去掉了分类下兼职数量，即categoryNum字段

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
			"createTime":1560308075000,
			"updateTime":1563299669000
		},
		{
			"categoryId":2,
			"categoryName":"代课/会议",
			"categoryNum":1,
			"createTime":1563310081000,
			"updateTime":1563310112000
		}
	],
	"msg":"成功"
}
```



### 2、分类兼职列表

注意此列表是审核通过的兼职

v1.1 更新说明：去掉了partRemark（兼职信息备注）、creatorPhone（兼职发布者电话）等字段

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
				"employSex":2,
				"partAddress":"西安工程大学临潼区",
				"partCategory":2,
				"partCreator":"oxrwq0zPbgTB-gV9Y4Q-hN4g25Fk",
				"partId":"1566039929001477652",
				"partMoney":0.01,
				"partMoneyShow":0.00,
				"partName":"兼职名称",
				"partOverview":"兼职描述",
				"partStart":1566039796582,
				"partStatus":3,
				"partTime":"点名完毕就可以走了，立马确认！"
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
    NO_PAY(0, "未付款"),
    NEW_PART(1, "未审核"),
    NOT_PASS(2, "审核失败(已退款)"),
    PASS_PAY(3, "已发布"),
    TAKE_ORDER(4, "已经接单"),
    FINISH_ORDER(5, "已经完成"),
    FINISH_CREATE(6, "发布方确认已完成"),
    CANCEL_CREATE(7, "取消订单 未付款"),
    CANCEL_PAY(8, "取消订单 已付款/接单"),
    OTHER(9, "其他情况")
}
```

### 3、发布兼职信息

v1.1 更新，无需参数partRemark（兼职信息备注）、creatorPhone（兼职发布者电话）

**另外，汇报一个来自前端的BUG，结束时间字段partEnd无法传到后台**

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
employSex 接受条件 Integer 1男、2女、3男女不限
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

### 6、查看发布兼职 (不分状态)

v1.1更新：去除updateTime字段、partRemark字段

```
GET http://tim.natapp1.cc/buckmoo/user/part/all_created
```

参数

```
pageindex 分页参数
```

返回值 `4、查看发布兼职 (分状态) 一样`

### 7、查看接手兼职  (不分状态)

v1.1更新：去除updateTime字段、partRemark字段

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



### 9、兼职退款



用户主动退款（尚未设置接口）

审核未通过退款（后台设定接口）



### 10、接手一个兼职

v1.1更新：同样，去除updateTime字段、partRemark字段

```
POST http://tim.natapp1.cc/buckmoo/user/part/accept
```

参数

```
partId : String要接受的兼职信息Id 
```

返回值

```json
{
	"code":1,
	"msg":"请先绑定手机号"
}

{
	"code":0,
	"data":{
		"createTime":1563395610000,
		"creatorPhone":"12321313123",
		"employSex":3,
		"partAddress":"西安芷阳新苑19栋1单元301",
		"partCategory":6,
		"partCreator":"oxrwq0zPbgTB-gV9Y4Q-hN4g25Fk",
		"partEmploy":"oxrwq0xrKKyqiAGE8O9TM3L1yaQY",
		"partEnd":1547864070000,
		"partId":"1963348810541158999",
		"partMoney":0.02,
		"partMoneyShow":0.00,
		"partName":"周六家教-辅导数学-测试",
		"partOverview":"周六家教-辅导数学，周六整天，孩子数学差，需要辅导可长期联系",
		"partRemark":"备注信息：无",
		"partStart":1547864070000,
		"partStatus":4,
		"partTime":"时间最多两个月，三个月也行",
		"updateTime":1563746701000
	},
	"msg":"成功"
}
```



### 11、接手方确认完成

```
POST http://tim.natapp1.cc/buckmoo/user/part/accepter_finish
```

参数

```
partId : String 兼职信息Id
```

返回值

```json
{
	"code":0,
	"data":{
		"createTime":1563395610000,
		"creatorPhone":"12321313123",
		"employSex":3,
		"partAddress":"西安芷阳新苑19栋1单元301",
		"partCategory":6,
		"partCreator":"oxrwq0xrKKyqiAGE8O9TM3L1yaQY",
		"partEmploy":"oxrwq0xrKKyqiAGE8O9TM3L1yaQY",
		"partEnd":1547864070000,
		"partId":"1963348810541158999",
		"partMoney":0.02,
		"partMoneyShow":0.00,
		"partName":"周六家教-辅导数学-测试",
		"partOverview":"周六家教-辅导数学，周六整天，孩子数学差，需要辅导可长期联系",
		"partRemark":"备注信息：无",
		"partStart":1547864070000,
		"partStatus":5,
		"partTime":"时间最多两个月，三个月也行",
		"updateTime":1563916861000
	},
	"msg":"成功"
}
```

### 12、发布方确认完成

```
POST http://tim.natapp1.cc/buckmoo/user/part/affirm_finish
```

参数

```
partId : String 兼职信息Id
```

返回值

```json
{
	"code":0,
	"data":{
		"createTime":1563395610000,
		"creatorPhone":"12321313123",
		"employSex":3,
		"partAddress":"西安芷阳新苑19栋1单元301",
		"partCategory":6,
		"partCreator":"oxrwq0xrKKyqiAGE8O9TM3L1yaQY",
		"partEmploy":"oxrwq0xrKKyqiAGE8O9TM3L1yaQY",
		"partEnd":1547864070000,
		"partId":"1963348810541158999",
		"partMoney":0.02,
		"partMoneyShow":0.00,
		"partName":"周六家教-辅导数学-测试",
		"partOverview":"周六家教-辅导数学，周六整天，孩子数学差，需要辅导可长期联系",
		"partRemark":"备注信息：无",
		"partStart":1547864070000,
		"partStatus":5,
		"partTime":"时间最多两个月，三个月也行",
		"updateTime":1563916861000
	},
	"msg":"成功"
}
```



## 账户相关

### 1、用户基本信息获取

v1.1 更新：如果没有用户电话信息说明用户并未绑定手机号码，后面会提供接口绑定号码！

```
GET http://tim.natapp1.cc/buckmoo/user/info/show
```
参数
```
无
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
        "userPhone":"127893712894"
		"userIcon":"http://thirdwx.qlogo.cn/mmopen/vi_32/bxVEQxwmO32", 	//用户头像
		"userMember":0,	//会员级别
		"userName":"Tim",	//用户昵称
		"userSex":1	//性别
	},
	"msg":"成功"
} 
```

会员等级参考

```java
COMMON(0, "非会员"),
ONE_LEVEL(1, "白银会员"),
TWO_LEVEL(2, "黄金会员"),
THREE_LEVEL(3, "钻石会员")
```



### 2、获取验证码

```
GET http://tim.natapp1.cc/buckmoo/user/info/verifykey
```

参数

```
phone:String 用户输入的手机号码
```

返回值

```
{
	"code":0,
	"msg":"成功"
}
{
	"code":1,
	"msg":"请填写正确格式的手机号"
}
{
	"code":2,
	"msg":"请填写手机号"
}
```

### 3、提交获取手机号码的表单

```
GET http://tim.natapp1.cc/buckmoo/user/info/bindphone
```

参数

```
phone:String 用户输入的手机号码
verifykey: String 用户输入的验证码
```

返回值

```
{
	"code":0,
	"msg":"成功"
}
{
	"code":1,
	"msg":"验证码错误"
}
```



# 二、活动发布方

## 账户相关

### 1、企业注册

v1.2更新：取消输入手机号

```
POST http://tim.natapp1.cc/buckmoo/company/info/register
```

参数

```
companyId : String 社会统一信用吗
companyName 企业名称
companyLicense 营业执照照片路径
```



返回值

```json

{
	"code":1,
	"msg":"请先绑定手机"
}

{
	"code":0,
	"data":{
		"companyId":"916101215MA6UAC9Q21",
		"companyLicense":"http://a.png",
		"companyMember":0,
		"companyName":"IG",
		"companyPhone":"15291418231",
		"companyStatus":0,
		"openid":"oxrwq0zPbgTB-gV9Y4Q-hN4g25Fk"
	},
	"msg":"成功"
}


```

### 2、企业信息获取

v1.2 更新： 增加字段描述信息

```
GET http://tim.natapp1.cc/buckmoo/company/info/show
```

参数

```
无，需要openid即可
```

返回值

```json
{
	"code":0,
	"data":{
		"companyId":"1566022871029249125",
		"companyLicense":"https://s2.ax1x.com/2019/08/21/mNZj2V.jpg",
		"companyMember":1,
		"companyMemberStr":"白银会员",
		"companyName":"骊山鹿鸣教育科技有限公司",
		"companyPhone":"15291418231",
		"companyStatus":1,
		"companyStatusStr":"审核通过",
		"memberOverdue":1599609600000,
		"memberOverdueStr":"2020-09-09",
		"openid":"oxrwq0zPbgTB-gV9Y4Q-hN4g25Fk"
	},
	"msg":"成功"
}
```

枚举代码参考

```java
//公司信息状态
@Getter
public enum CompanyStatusEnum implements CodeEnum {
    NEW(0, "未审核"),
    PASS(1, "审核通过"),
    NOT_PASS(2, "审核未通过"),
    ;
    ...
}

//会员等级
@Getter
public enum MemberLevelEnum implements CodeEnum{
    COMMON(0, "非会员"),
    ONE_LEVEL(1, "白银会员"),
    TWO_LEVEL(2, "黄金会员"),
    THREE_LEVEL(3, "钻石会员")
    ;
    ...
}
```

## 支付相关

### 1、企业成为会员

```
GET http://tim.natapp1.cc/buckmoo/company/pay/member
```

参数

```
returnUrl : String 支付完成后需要跳转的地址！
memberLevel： Integer 要要成为的会员等级（1、白银会员 2、黄金会员 3、钻石会员）
```

返回值

微信H5支付页面

### 2、发布活动支付费用

会员需要支付推广费用，但是非会员发布活动需要支付费用

```
http://tim.natapp1.cc/buckmoo/company/pay/activity
```

参数

```
activityId:String 支付活动的Id
returnUrl:String 支付成功后的返回地址
```

返回值

微信H5支付页面

## 活动相关

### 1、发布活动

```
POST http://tim.natapp1.cc/buckmoo/company/activity/create
```

参数

```
activityName： String活动名称
activityMode： Integer活动模式（见参考枚举）
activityGeneralize： Integer活动推广力度
activityLink：活动链接
activityAbstract：活动说明与简介
activityLogo：活动Logo
```

返回值

```json
{
	"code":0,
	"data":{
		"activityAbstract":"高校总决赛",
		"activityAudit":0,
		"activityGeneralize":2000,
		"activityId":"1566212848123840773",
		"activityLink":"http://lol.game.pc...",
		"activityLogo":"http://lol.png",
		"activityMode":1,
		"activityName":"周五LOL（艾欧尼亚钻石段来）",
		"activityOpenid":"oxrwq0zPbgTB-gV9Y4Q-hN4g25Fk"
	},
	"msg":"成功"
}

{
	"code":1,
	"msg":"请先登录"
}

{
	"code":2,
	"msg":"尚未注册公司"
}
```

活动模式(类型)的枚举参考

```java
public enum ActivityModeEnum implements CodeEnum {
    COMPANY(0, "企业组织活动"),
    STUDENT(1, "学生社团活动"),
    OTHER(2, "其他类型")
}
```



### 2、删除活动

```
GET http://tim.natapp1.cc/buckmoo/company/activity/delete
```

参数

```
activityId : String活动Id
```

返回值

```
{
	"code":0,
	"msg":"成功"
}
{
	"code":1,
	"msg":"请先登录"
}
{
	"code":2,
	"msg":"参数不完整"
}
{
	"code":3,
	"msg":"权限错误"
}
```



### 3、修改活动

```
POST http://tim.natapp1.cc/buckmoo/company/activity/update
```

参数

```
activityId : 活动Id

activityName： String活动名称
activityMode： Integer活动模式（见参考枚举）
activityGeneralize： Integer活动推广力度
activityLink：活动链接
activityAbstract：活动说明与简介
activityLogo：活动Logo

```

返回值

```
{
	"code":0,
	"data":{
		"activityAbstract":"AAAAAAA",
		"activityAudit":0,
		"activityGeneralize":10,
		"activityId":"1566214957280388602",
		"activityLink":"AAAAAAA",
		"activityLogo":"AAAAAAAAAAA",
		"activityMode":1,
		"activityName":"AAAAAAA",
		"activityOpenid":"oxrwq0zPbgTB-gV9Y4Q-hN4g25Fk"
	},
	"msg":"成功"
}

{
	"code":1,
	"msg":"请先登录"
}
{
	"code":2,
	"msg":"参数不完整"
}
{
	"code":3,
	"msg":"权限错误"
}


```



### 4、活动列表

v1.2更新：更加pageCount、currentPage两个字段

展示自己发布的活动，审核通过的，未通过的

```
GET http://tim.natapp1.cc/buckmoo/company/activity/show
```

参数

```
pageindex: 分页索引，不传默认是0
```

返回值

```json
{
	"code":0,
	"data":{
		"count":3, //数据条数
        "pageCount":1, //总页数
        "currentPage": 0 //当前页数
		"list":[
			{
				"activityAbstract":"高校总决赛", //活动简介、描述
				"activityAudit":0, //审核状态
				"activityAuditStr":"未审核", //审核状态文字描述
				"activityGeneralize":2000, //推广人数
				"activityId":"1566214172673771593", //活动的Id
				"activityLink":"http://lol.game.pc...", //活动链接
				"activityLogo":"http://lol.png", //活动宣传图册
				"activityMode":1, //活动模式
				"activityModeStr":"学生社团活动", //活动模式文字说明
				"activityName":"周一LOL（艾欧尼亚钻石段来）", //活动名称
				"activityOpenid":"oxrwq0zPbgTB-gV9Y4Q-hN4g25Fk", //发布企业的管理员openid
				"activityOpenidStr":"骊山鹿鸣教育科技有限公司" //公司名称
			},
			{
				"activityAbstract":"高校总决赛",
				"activityAudit":0,
				"activityAuditStr":"未审核",
				"activityGeneralize":2000,
				"activityId":"1566214957280388602",
				"activityLink":"http://lol.game.pc...",
				"activityLogo":"http://lol.png",
				"activityMode":1,
				"activityModeStr":"学生社团活动",
				"activityName":"周二LOL（会员）",
				"activityOpenid":"oxrwq0zPbgTB-gV9Y4Q-hN4g25Fk",
				"activityOpenidStr":"骊山鹿鸣教育科技有限公司"
			},
			{
				"activityAbstract":"高校总决赛",
				"activityAudit":0,
				"activityAuditStr":"未审核",
				"activityGeneralize":30,
				"activityId":"1566217507597277422",
				"activityLink":"http://lol.game.pc...",
				"activityLogo":"http://lol.png",
				"activityMode":1,
				"activityModeStr":"学生社团活动",
				"activityName":"周二LOL（测试推广支付）",
				"activityOpenid":"oxrwq0zPbgTB-gV9Y4Q-hN4g25Fk",
				"activityOpenidStr":"骊山鹿鸣教育科技有限公司"
			}
		]
	},
	"msg":"成功"
}
```



# 三、超级管理员

### 1、查看企业会员

分状态查看，0为未审核、1是审核通过、2是审核未通过

```
GET http://tim.natapp1.cc/buckmoo/admin/company/show
```

参数

```
status : Integer  企业的审核状态
```

返回值

```json
{
	"code":0,
	"data":{
		"listPass":[
			{
				"companyId":"1566022871029249125",
				"companyLicense":"http://tim.natapp1.cc/buckmoo/file/fileDownload?fileUrl=f9664dc7-4351-4940-9e2a-62061549d538.jpeg",
				"companyMember":1,
				"companyMemberStr":"白银会员",
				"companyName":"骊山鹿鸣教育科技有限公司",
				"companyPhone":"15291418231",
				"companyStatus":1,
				"companyStatusStr":"审核通过",
				"memberOverdue":1569160800507,
				"memberOverdueStr":"2019-09-22",
				"openid":"oxrwq0zPbgTB-gV9Y4Q-hN4g25Fk"
			},
			{
				"companyId":"8566022871029249123",
				"companyLicense":"https://s2.ax1x.com/2019/08/21/mNZj2V.jpg",
				"companyMember":1,
				"companyMemberStr":"白银会员",
				"companyName":"北京创新乐知网络技术有限公司",
				"companyPhone":"15229720759",
				"companyStatus":1,
				"companyStatusStr":"审核通过",
				"memberOverdue":1569160587790,
				"memberOverdueStr":"2019-09-22",
				"openid":"oxrwq0xrKKyqiAGE8O9TM3L1yaQY"
			}
		],
		"pageCount":1,
		"pageIndex":0
	},
	"msg":"成功"
}
```

### 2、根据Id查看企业信息

```
GET http://tim.natapp1.cc/buckmoo/admin/company/show_detail
```

参数

```
companyId : String 企业ID（统一信用码）
```

返回值

```json
{
	"code":0,
	"data":{
		"companyId":"8566022871029249125",
		"companyLicense":"https://s2.ax1x.com/2019/08/21/mNZj2V.jpg",
		"companyMember":0,
		"companyMemberStr":"非会员",
		"companyName":"北京创新乐知网络技术有限公司B",
		"companyPhone":"15229720759",
		"companyStatus":0,
		"companyStatusStr":"未审核",
		"memberOverdue":0,
		"memberOverdueStr":"未注册会员",
		"openid":"oxrwq0xrKKyqiAGE8O9TM3L1yaQY2"
	},
	"msg":"成功"
}
```



### 3、删除某个企业

```
GET http://tim.natapp1.cc/buckmoo/admin/company/delete
```

参数

```
companyId : String 企业ID（统一信用码）
```

返回值

```json
{
	"code":0,
	"msg":"成功"
}
```

### 4、修改企业信息

```
POST http://tim.natapp1.cc/buckmoo/admin/company/update_info
```

参数

```
companyId : String 企业ID（统一信用码）
companyName ： 企业名称
companyLicense ： 营业执照路径
companyPhone ： 电话
companyMember ： 会员等级
memberOverdue ： 会员到期时间
```

返回值

返回修改成功后的信息

```json
{
	"code":0,
	"data":{
		"companyId":"8566022871029249126",
		"companyLicense":"a",
		"companyMember":1,
		"companyMemberStr":"白银会员",
		"companyName":"a",
		"companyPhone":"a",
		"companyStatus":0,
		"companyStatusStr":"未审核",
		"memberOverdue":21312312432,
		"memberOverdueStr":"1970-09-05",
		"openid":"oxrwq0xrKKyqiAGE8O9TM3L1yaQY2"
	},
	"msg":"成功"
}
```

### 5、分状态查看活动





## 四、文件上传

```
POST http://tim.natapp1.cc/buckmoo/file/fileUpload
```

参数

![](https://s2.ax1x.com/2019/08/20/mG6faj.png)

返回值

```json
{
    "code": 0,
    "msg": "成功",
    "data": "http://tim.natapp1.cc/buckmoo/file/fileDownload?fileUrl=2d23a14c-fe3c-46e3-83d2-18cf47007848.png"
}
```

