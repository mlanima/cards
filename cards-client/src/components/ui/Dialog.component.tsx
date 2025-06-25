"use client";
import { useDialogStore } from "@/stores/dialogStore";
import { Dialog as HDialog, DialogPanel, DialogTitle } from "@headlessui/react";
import { useState } from "react";

interface DailogProps {
  message: string;
  isPositive: boolean;
}

const Dialog = ({ message, isPositive }: DailogProps) => {
  const [isOpen, setIsOpen] = useState(true);
  const { resolve } = useDialogStore();

  const onClose = () => {
    setIsOpen(false);
    resolve();
  };

  return (
    <HDialog open={isOpen} onClose={onClose} className="relative z-50">
      <div className="fixed inset-0 flex w-screen items-start justify-center p-4 text-stone-50">
        <DialogPanel
          className={`rounded-full items-center justify-center flex gap-4 border p-4 ${isPositive ? "bg-lime-600" : "bg-red-600"} hover:bg-stone-900`}
        >
          <DialogTitle className="font-bold my-auto"> {message} </DialogTitle>
          <div className="flex gap-4 cursor-pointer">
            <button onClick={onClose}>
              <svg
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 24 24"
                strokeWidth={1.5}
                stroke="currentColor"
                className="size-6"
              >
                <path
                  strokeLinecap="round"
                  strokeLinejoin="round"
                  d="M6 18 18 6M6 6l12 12"
                />
              </svg>
            </button>
          </div>
        </DialogPanel>
      </div>
    </HDialog>
  );
};

export default Dialog;
