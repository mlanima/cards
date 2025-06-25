import { create } from "zustand";

interface DialogState {
    dialog?: {
        message: string;
        isPositive: boolean;
    };
    show: (message: string, isPositive: boolean) => void;
    resolve: () => void;
}

const useDialogStore = create<DialogState>()((set) => ({
    dialog: undefined,
    show: (message: string, isPositive: boolean) =>
        set(() => ({
            dialog: {
                message: message,
                isPositive: isPositive
            }
        })),
    resolve: () => set(() => ({ dialog: undefined }))
}));

export type { DialogState };
export { useDialogStore };