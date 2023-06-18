import { GetUserProfile } from '@/internal/apis';
import type { Response } from '@/internal/types';
import { isNull } from 'lodash';
import { ElMessage } from 'element-plus';
import type { VueCookies } from 'vue-cookies';
import type { NavigationGuardWithThis, RouteLocationNormalized } from 'vue-router';

const cookies: VueCookies = $cookies

const guard: NavigationGuardWithThis<any> = 
  async (to: RouteLocationNormalized, from: RouteLocationNormalized) => {
  let token: string = cookies.get('token');
  
  if (to.path === '' || to.path === '/') {
    return true;
  }

  if (token === '' || token === null) {
    ElMessage.error("登陆已过期，请重新登陆。")
    cookies.remove("token")
    cookies.remove("username")
    return to.path === '' || to.path === '/' 
      ? false
      : '/'
  }

  const resp = await GetUserProfile()
  if (isNull(resp)) {
    ElMessage.error("服务器连接失败。")
    cookies.remove("token")
    cookies.remove("username")
    return to.path === '' || to.path === '/'
      ? false
      : '/'
  }
  if ((resp as Response).code >= 400 && (resp as Response).code < 500) {
    ElMessage.error("登陆已过期，请重新登陆。")
    cookies.remove("token")
    cookies.remove("username")
    return '/'
  }
  if ((resp as Response).code >= 500 && (resp as Response).code < 600) {
    ElMessage.error("服务器内部错误。")
    cookies.remove("token")
    cookies.remove("username")
    return '/'
  }
  return true
}

export default guard