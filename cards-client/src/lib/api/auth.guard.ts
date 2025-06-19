import { useEffect } from 'react';
import { useRouter } from 'next/navigation';
import { getToken } from './auth.service';

export function useAuthGuard() {
  const router = useRouter();
  useEffect(() => {
    const token = getToken();
    if (!token) {
      router.replace('/login');
    }
  }, [router]);
} 