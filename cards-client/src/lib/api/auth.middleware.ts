import { getToken } from './auth.service';

export function withAuth(fetcher: (input: RequestInfo, init?: RequestInit) => Promise<Response>) {
  return async (input: RequestInfo, init: RequestInit = {}) => {
    const token = getToken();
    const headers = new Headers(init.headers || {});
    if (token) {
      headers.set('Authorization', `Bearer ${token}`);
    }
    return fetcher(input, { ...init, headers });
  };
} 