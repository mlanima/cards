import { ChevronLeftIcon, ChevronRightIcon } from '@heroicons/react/24/solid';
import React from 'react';

interface ChangeCardButtonProps {
    direction: 'r' | 'l';
    changeCardIndex: React.Dispatch<React.SetStateAction<number>>;
    isDisabled: boolean;
}

const ChangeCardButton = ({
    direction,
    changeCardIndex,
    isDisabled,
}: ChangeCardButtonProps) => {
    const handleClick = () => {
        changeCardIndex((prev) => (direction === 'l' ? prev - 1 : prev + 1));
    };

    return (
        <button
            onClick={handleClick}
            className="cursor-pointer"
            disabled={isDisabled}
        >
            {direction === 'l' ? (
                <ChevronLeftIcon className="hover:scale-110 size-24 text-stone-400 hover:text-stone-900 transition-all" />
            ) : (
                <ChevronRightIcon className="hover:scale-110 size-24 text-stone-400 hover:text-stone-900 transition-all" />
            )}
        </button>
    );
};

export default ChangeCardButton;
