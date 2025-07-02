import React, { MouseEventHandler } from 'react';

interface ButtonProps {
    children: React.ReactNode;
    className?: string;
    type?: 'submit' | 'button' | 'reset';
    onClick?: MouseEventHandler<HTMLButtonElement>;
    color?: 'lime' | 'red' | 'blue' | 'gray' | 'yellow'; // Add more as needed
    size?: 'sm' | 'md' | 'lg';
}

const colorClasses: Record<string, string> = {
    lime: 'bg-lime-600 hover:bg-lime-500 text-white',
    red: 'bg-red-600 hover:bg-red-500 text-white',
    blue: 'bg-blue-600 hover:bg-blue-500 text-white',
    gray: 'bg-gray-600 hover:bg-gray-500 text-white',
    yellow: 'bg-yellow-500 hover:bg-yellow-400 text-white',
};

const sizeClasses: Record<string, string> = {
    sm: 'px-2 py-1 text-sm',
    md: 'px-3 py-1.5 text-base',
    lg: 'px-4 py-2 text-xl',
};

const Button = ({ type = 'button', children, className = '', onClick, color = 'lime', size = 'md' }: ButtonProps) => {
    return (
        <button
            type={type}
            onClick={onClick}
            className={`flex justify-center items-center gap-2 rounded-md font-semibold shadow-inner shadow-white/10 focus:outline-none focus:ring-2 focus:ring-white transition-colors duration-150 ${colorClasses[color]} ${sizeClasses[size]} ${className}`}
        >
            {children}
        </button>
    );
};

export default Button;
