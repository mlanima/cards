import { create } from 'zustand';
import { persist } from "zustand/middleware";

interface AuthState {
    isLoggedIn: boolean;
    logIn: () => void;
    logOut: () => void;
}

const useAuthStore = create<AuthState>()(
    persist(
      (set) => ({
        isLoggedIn: false,
        logIn: () => set({ isLoggedIn: true }),
        logOut: () => set({ isLoggedIn: false }),
      }),
      {
        name: 'auth-storage', // key in localStorage
      }
    )
  );

export type { AuthState };
export { useAuthStore };
