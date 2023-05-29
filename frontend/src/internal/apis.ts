import { mande } from "mande"
import { BaseURL } from "@/internal/urls"

export const LoginApi = mande(BaseURL + "/auth/login", {}, fetch)


