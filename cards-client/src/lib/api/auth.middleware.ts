import { useAuthStore } from '@/stores/authStore';
import { getToken, removeToken} from './auth.service';

export function withAuth(
  fetcher: (input: RequestInfo, init?: RequestInit) => Promise<Response>
) {
  return async (input: RequestInfo, init: RequestInit = {}) => {
    const token = getToken();
    const headers = new Headers(init.headers || {});
    if (token) {
      headers.set('Authorization', `Bearer ${token}`);
    }
    console.log("Im here: " + localStorage.getItem('token'));
    const response = await fetcher(input, { ...init, headers });
    if (response.status === 401) {
      const logout = useAuthStore(state => state.logOut)
      removeToken();
      localStorage.removeItem('token')
      logout();
      window.location.href = '/login';
      return Promise.reject(new Error('Unauthorized'));
    }

    return response;
  };
}
