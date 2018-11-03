# 考核系统

#### 项目介绍
中南云麓谷考核系统

#### Language
- Spring MVC
- Ajax
- BootStrap

#### Environment
- Maven
- IDEA
- Tomcat 7.0.4

#### Current Need To Do
- solve the problem that displays the assessment result and improve its structure view / done
- fix the bug of batch register in importing the excel which throws the exception of 'wrong type of file' /done
- modify the assessment table according to the staff position, especially for the instructor
- add the feature of uploading files
- update the data enum
- update the enum of position
- fix the bug of pagination with its previous and next
- Encrypted transmission
- export the assessment info and result
- improve the exception handler
- add the comment to interface method

#### 软件架构
软件架构说明

#### 流程及参数说明

1. 属性名采用驼峰命名规则

2. http的无状态属性要求每一次的通信都要包含完整描述这次通信的所有信息，所以每一次提交基本都要用户账号，用户账户将被保存在session中
—— 普通用户默认为学号

1. 登录

2. 填写考核表（工作内容与计划）

&emsp;其中姓名、中心名称、日期由前端自动填写且不可修改

| **登录 login**   |          |                      |
|------------------|----------|----------------------|
| 通用自然语言名称 | 属性名   | 类型（可以忽略这个） |
| 用户账号         | userId   | string               |
| 密码             | password | string               |

&emsp;在考核表中 每一块内容对应一块数据

| **填写考核表 updateWork** |                |                      |
|---------------------------|----------------|----------------------|
| 通用自然语言名称          | 属性名         | 类型（可以忽略这个） |
| 用户账号                  | userId         | string               |
| 常规工作                  | workRegular    | string               |
| 计划外工作                | workOutPlan    | string               |
| 其它加班                  | workOther      | string               |
| 其它开支                  | otherExpanse   | string               |
| 工作计划（简）            | workPlanSimple | string               |

3. 填写工作计划（详）（组长及以上）

| **填写工作计划 updateWorkPlan** |          |                      |
|---------------------------------|----------|----------------------|
| 通用自然语言名称                | 属性名   | 类型（可以忽略这个） |
| 用户账号                        | userId   | string               |
| 工作计划                        | workPlan | string               |

4. 上传文件

&emsp;上传文件的更新的请约束好命名，不然容易引起后台的IO错误

&emsp;如第一次上交了foo.zip

&emsp;那么第二次更新了一下就要上交foo_v2.zip


| **上传文件 uploadFile** |             |                      |
|-------------------------|-------------|----------------------|
| 通用自然语言名称        | 属性名      | 类型（可以忽略这个） |
| 用户账号                | userId      | string               |
| 任务完成文件            | missionFile |                      |
| 其他文件                | otherFile   |                      |

5. 部长、总监评价考核

&emsp;权限控制涉及到复杂的身份认证

&emsp;目前前端只做admin的权限检查，隐去查看的方法

&emsp;后端还会做拦截

&emsp;部门的类型为int 这里用enum做一个映射 之后补充一个部门的对应int值

&emsp;如果部门为全体则为null

&emsp;这里后台不做分页查询，直接返回所有的数据

| **进行考核submitExamination** |                      |                      |
|-------------------------------|----------------------|----------------------|
| 通用自然语言名称              | 属性名               | 类型（可以忽略这个） |
| 考核对象账号                  | userId               | string               |
| 部长评价                      | evaluationDepartment | string               |
| 总监评价                      | evaluationDirector   | string               |
| 部长打分                      | scoreDepartment      | float                |
| 总监打分                      | scoreDirector        | float                |

6. 查看考核结果

&emsp;这里通过方法名来约束

&emsp;不同的http请求访问不同的数据

&emsp;部门的类型为int 这里用enum做一个映射 之后补充一个部门的对应int值

&emsp;如果部门为全体则为null

| **查看考核结果viewResult** |            |                      |
|----------------------------|------------|----------------------|
| 通用自然语言名称           | 属性名     | 类型（可以忽略这个） |
| 部门                       | department | int                  |
| 月份                       | month      | int                  |


7. 管理员统一注册用户

&emsp;这里无论是批量还是单个 后台都默认是批量注册 将在后台被转成list进行批量注册

| **管理员统一注册 batchRegister** |            |                      |
|----------------------------------|------------|----------------------|
| 通用自然语言名称                 | 属性名     | 类型（可以忽略这个） |
| 用户账号                         | userId     | string               |
| 姓名                             | name       | string               |
| 部门                             | department | int                  |
| 职务                             | position   | int                  |


8. 用户修改密码

| **用户修改密码modifyPassword** |            |                      |
|--------------------------------|------------|----------------------|
| 通用自然语言名称               | 属性名     | 类型（可以忽略这个） |
| 用户账号                       | userId     | string               |
| 姓名                           | name       | string               |
| 原始密码                       | name       | string               |
| 新密码                         | department | string               |

9. 预览、编辑、发布考核结果

&emsp;保存与发布访问不同的方法

| **预览、编辑、发布考核结果 publicResult** |             |                      |
|-------------------------------------------|-------------|----------------------|
| 通用自然语言名称                          | 属性名      | 类型（可以忽略这个） |
| 用户账号                                  | userId      | string               |
| 部门                                      | department  | int                  |
| 月份                                      | month       | int                  |
| 编辑信息                                  | editMessage | string               |

&emsp;以上是表单提交的内容，接下来是响应的内容

&emsp;2、3、5 响应的内容的之前提交的内容一致

&emsp;6的响应内容与9提交的内容一致
