export interface AuthResponse {
    token: string;
}

const API_URL = process.env.NEXT_PUBLIC_API_URL ?? 'http://localhost:8080';

export async function register(email: string, password: string) {
    const res = await fetch(`${API_URL}/auth/users`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ email, password }),
    });
    if (!res.ok) {
        throw new Error('Registration failed');
    }
}

export async function login(email: string, password: string): Promise<string> {
    const res = await fetch(`${API_URL}/auth/token`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ email, password }),
    });
    if (!res.ok) {
        throw new Error('Login failed');
    }
    const data: AuthResponse = await res.json();
    setToken(data.token);
    return data.token;
}

export function setToken(token: string) {
    if (typeof window !== 'undefined') {
        localStorage.setItem('token', token);
    }
}

export function getToken(): string | null {
    if (typeof window !== 'undefined') {
        return localStorage.getItem('token');
    }
    return null;
}

export function removeToken() {
    localStorage.removeItem('token');
}
