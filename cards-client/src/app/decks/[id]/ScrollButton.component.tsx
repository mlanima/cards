import React from 'react';
import { ChevronDownIcon } from '@heroicons/react/24/solid';

export default function ScrollButton() {
    const scrollDown = () => {
        window.scrollBy({ top: window.innerHeight, behavior: 'smooth' });
    };

    return (
        <button
            onClick={scrollDown}
            className="text-stone-900 hover:scale-110 cursor-pointer flex gap-2 items-center transition-all duration-300"
        >
            <ChevronDownIcon className="size-12" />
            <h2 className="text-xl">To cards</h2>
        </button>
    );
}
