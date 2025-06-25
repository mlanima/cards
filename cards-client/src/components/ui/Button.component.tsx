import React, { MouseEventHandler } from "react";
import { Button as HButton } from "@headlessui/react";

interface ButtonProps {
  children: React.ReactNode;
  className?: string;
  type?: "submit" | "button" | "reset";
  onClick?: MouseEventHandler<HTMLButtonElement>;
}

const Button = ({ type, children, className, onClick }: ButtonProps) => {
  return (
    <HButton
      type={type}
      onClick={onClick}
      className={`flex justify-center items-center gap-2 rounded-md bg-lime-600 px-3 py-1.5 text-xl font-semibold text-white shadow-inner shadow-white/10 focus:not-data-focus:outline-none data-focus:outline data-focus:outline-white data-hover:bg-lime-500 data-open:bg-gray-700 ${className}`}
    >
      {children}
    </HButton>
  );
};

export default Button;
