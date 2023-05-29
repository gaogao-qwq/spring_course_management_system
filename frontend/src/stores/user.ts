import { defineStore } from "pinia"
import { LoginApi } from "@/internal/apis"
import type { Resp } from "@/internal/resp"
import { isNull } from "lodash"



export const useUserStore = defineStore('user', {
  state: () => ({
    username: '',
    token: ''
  }),
  actions: {
    login(username: string, password: string): void | string {
      LoginApi
        .post('', {
          'username': username,
          'password': password
      })
        .then((response) => {
          console.log(response)
          let r = response as Resp
          if (!r.success || isNull(r.data)) {
            console.log(r.message)
            return r.message
          }
          this.username = r.data.username
          this.token = r.data.token
      })
        .catch((reason) => {
          return reason as string
      })
    }
  }
})
