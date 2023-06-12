# 基于 Spring & Vue3 的简单教务管理系统

## 许可证信息

本项目前端使用 GPL-3.0 开源许可证开放源代码，后端使用 AGPL-3.0 开源许可证开放源代码，
前端许可证位于 `frontend` 目录，后端许可证位于 `backend` 目录。

## 如何使用？

### Clone 仓库

```sh
git clone https://github.com/gaogao-qwq/spring_course_management_system.git
```

### 后端

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
