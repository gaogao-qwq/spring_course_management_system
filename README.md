# 基于 Spring & Vue3 的简单教务管理系统
[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2Fgaogao-qwq%2Fspring_course_management_system.svg?type=shield)](https://app.fossa.com/projects/git%2Bgithub.com%2Fgaogao-qwq%2Fspring_course_management_system?ref=badge_shield)


## 前端页面结构图

### 登陆界面

```text
         ┌──────────────────────────────────────────────────┐                   
         │                                      Background  │
         │                                                  │
         │                                                  │
         │               ┌────────────────┐                 │
         │               │   Login Card   │                 │
         │               │                │                 │    ┌────────────────┐
         │               │                │                 ├───►│ Main Container │
         │               │                │                 │    └────────────────┘
         │               └────────────────┘                 │
         │                                                  │
         │                                                  │
         │                                                  │
         └──────────────────────────────────────────────────┘                   
```

### 管理系统界面

```text
                                                                
         ┌─────────┐ ┌─────────────────────────────────────┐    
         │  aside  │ │                           username* ├─┐
         │         │ └─────────────────────────────────────┘ │
         │         │ ┌─────────────────────────────────────┐ │
         │         │ │                                     │ │
         │         │ │                                     │ │
         │         │ │                                     │ │
         │         │ │                                     │┌┼┐  ┌──────────┐
         │         │ │            Router Content           ├┘│└─►│ 路由内容 │
         │         │ │                                     │ │   └──────────┘
         │         │ │                                     │ │
         │         │ │                                     │ │
         │         │ │                                     │ │
         │         │ └─────────────────────────────────────┘ │
         │         │ ┌─────────────────────────────────────┐ │   ┌────────────┐
         │         │ │                Footer               ├─┼──►│ 懒加载内容 │
         └────┬────┘ └─────────────────────────────────────┘ │   └────────────┘
              └──────────────────────────────────────────────┘

```

## 如何使用？

### Clone 仓库

```sh
git clone https://github.com/gaogao-qwq/spring_course_management_system.git
```

### 后端

本项目使用 `JPA` 作为 `ORM`。在运行后端前，清确保环境中至少存在一种数据库服务，并确保已经创建了相应数据库/架构
（不需要建立任何数据表，`JPA` 会根据相应的 `Entity` 自动建立数据表），并根据设备数据库具体配置到
`spring_course_management_system/backend/src/main/resource/application.properties`
中调整 `jdbc` 的连接配置。

确保配置完成后，可以执行：

```sh
cd spring_course_management_system/backend
mvn install # 下载依赖，请确保网络通畅
mvn spring-boot:run # 编译并启用调试，适用于开发环境
```

### 前端

```sh
cd spring_course_management_system/frontend
npm install # 下载依赖，请确保网络通畅
npm run dev # 编译并启用热重载，适用于开发环境
```

确保前端后端皆编译并运行后，使用任意浏览器访问 `localhost:5173` 即可。

## 许可证信息

本项目前端使用 GPL-3.0 开源许可证开放源代码，后端使用 AGPL-3.0 开源许可证开放源代码，
前端许可证位于 `frontend` 目录，后端许可证位于 `backend` 目录。


## License
[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2Fgaogao-qwq%2Fspring_course_management_system.svg?type=large)](https://app.fossa.com/projects/git%2Bgithub.com%2Fgaogao-qwq%2Fspring_course_management_system?ref=badge_large)