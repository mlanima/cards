import React, { useState } from 'react';
import Link from 'next/link';
import { Menu, MenuButton, MenuItem, MenuItems } from '@headlessui/react';
import {
    ChevronDownIcon,
    PencilIcon,
    TrashIcon,
} from '@heroicons/react/16/solid';
import { updateDeck } from '@/lib/api/deck.service';

interface DeckProps {
    id: number;
    name: string;
    amount: number;
    onDelete: () => void;
}

const Deck = ({ id, name, amount, onDelete }: DeckProps) => {
    const [deckName, setDeckName] = useState(name);
    const [isEditing, setIsEditing] = useState(false);

    const handleNameChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setDeckName(e.target.value);
    };

    const handleNameSave = () => {
        updateDeck(id, deckName);
        setIsEditing(false);
    };

    const handleRenameClick = () => {
        setIsEditing(true); // активувати редагування
    };

    return (
        <div className="inline-block w-64 h-54 shrink-0 border-2 font-bold rounded-2xl text-stone-100 bg-stone-800 border-lime-<900> hover:scale-105 origin-center transition-all overflow-hidden">
            <Link
                className="flex justify-center items-center h-2/3"
                href={`/decks/${id}`}
            >
                {isEditing ? (
                    <input
                        name={`deck:${id}`}
                        autoFocus
                        className="text-xl text-center rounded-full border-2 w-5/6 inline-block px-4"
                        value={deckName}
                        onChange={handleNameChange}
                        onBlur={handleNameSave}
                    />
                ) : (
                    <p className="text-xl text-center">{deckName}</p>
                )}
            </Link>
            <div className="flex justify-between p-4 m-0 h-1/3 bg-stone-900">
                <p className="text-stone-500 text-lg"> {amount} </p>
                <Menu>
                    <MenuButton className="inline-flex items-center gap-2 rounded-md bg-stone-700 px-3 py-1.5 text-sm/6 font-semibold text-white shadow-inner shadow-white/10 focus:not-data-focus:outline-none data-focus:outline data-focus:outline-white data-hover:bg-gray-700 data-open:bg-gray-700">
                        Options
                        <ChevronDownIcon className="size-4 fill-white/60" />
                    </MenuButton>

                    <MenuItems
                        transition
                        anchor="bottom end"
                        className="w-52 origin-top-right rounded-xl border border-stone-700 bg-stone-700 p-1 text-sm/6 text-white transition duration-100 ease-out [--anchor-gap:--spacing(1)] focus:outline-none data-closed:scale-95 data-closed:opacity-0"
                    >
                        <MenuItem>
                            <button
                                onClick={handleRenameClick}
                                className="group flex w-full items-center gap-2 rounded-lg px-3 py-1.5 data-focus:bg-white/10"
                            >
                                <PencilIcon className="size-4 fill-white/30" />
                                Rename
                                <kbd className="ml-auto hidden font-sans text-xs text-white/50 group-data-focus:inline">
                                    ⌘E
                                </kbd>
                            </button>
                        </MenuItem>
                        <div className="my-1 h-px bg-white/5" />
                        <MenuItem>
                            <button
                                onClick={onDelete}
                                className="group flex w-full items-center gap-2 rounded-lg px-3 py-1.5 data-focus:bg-white/10"
                            >
                                <TrashIcon className="size-4 fill-white/30" />
                                Delete
                                <kbd className="ml-auto hidden font-sans text-xs text-white/50 group-data-focus:inline">
                                    ⌘D
                                </kbd>
                            </button>
                        </MenuItem>
                    </MenuItems>
                </Menu>
            </div>
        </div>
    );
};

export default Deck;
