import React from "react";
import { Button as HButton } from "@headlessui/react";

interface ButtonProps {
  children: React.ReactNode;
  className?: string;
  type?: "submit" | "button" | "reset" | undefined;
}

const Button = ({ type, children, className }: ButtonProps) => {
  return (
    <HButton
      type={type}
      className={`flex justify-center items-center gap-2 rounded-md bg-purple-500 px-3 py-1.5 text-xl font-semibold text-white shadow-inner shadow-white/10 focus:not-data-focus:outline-none data-focus:outline data-focus:outline-white data-hover:bg-gray-600 data-open:bg-gray-700 ${className}`}
    >
      {children}
    </HButton>
  );
};

export default Button;
