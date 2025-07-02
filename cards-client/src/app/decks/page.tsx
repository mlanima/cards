'use client';
import { useAuthGuard } from '@/lib/api/auth.guard';
import { createDeck, deleteDeck, getDecks } from '@/lib/api/deck.service';
import React, { ChangeEvent, useEffect, useState } from 'react';
import { Deck } from '@/lib/models/Deck.type';
import DeckComponent from '@/components/ui/Deck.component';
import { useDialogStore } from '@/stores/dialogStore';
import { Input } from '@headlessui/react';

const LearningPage = () => {
    useAuthGuard();
    const show = useDialogStore((state) => state.show);

    const [decks, setDecks] = useState<Deck[]>([]);

    useEffect(() => {
        getDecks()
            .then((decks) => setDecks(decks))
            .catch((reason) => console.log(reason));
    }, []);

    const onDeckCreate = () => {
        createDeck('New Deck')
            .then((newDeck) => setDecks((prev) => [...prev, newDeck]))
            .catch(() => show('Server error', false));
    };

    const handleDeleteDeck = (id: number) => {
        setDecks((prev) => prev.filter((deck) => deck.id !== id));
    };

    const onDeleteDeck = (id: number) => {
        deleteDeck(id) // припустимо, така функція є
            .then(() => handleDeleteDeck(id))
            .catch(() => show('Failed to delete deck', false));
    };

    const [searchInput, setSearchInput] = useState('');

    const onSearch = (event: ChangeEvent<HTMLInputElement>) => {
        setSearchInput(event.target.value);
    };

    const searchFilter = (deck: Deck) =>
        deck.name.toLowerCase().includes(searchInput.toLowerCase());

    return (
        <div className="flex justify-center min-h-full w-full p-10">
            <div className="container">
                <h2 className="text-6xl font-bold text-stone-800 mb-12">
                    Your decks
                </h2>
                <div className="search flex justify-between mb-12">
                    <Input
                        className={`m-4 py-4 px-6 text-stone-900 border-b-2 border-stone-400 focus:outline-0 text-xl border-0 w-1/3 mr-20`}
                        placeholder="Search"
                        value={searchInput}
                        onChange={onSearch}
                    />
                    <button
                        className=" rounded-xl items-center shrink-0 grow-0 px-8 py-6 w-52 h-fit  bg-green-700 text-xl text-stone-100 cursor-pointer hover:bg-stone-700 font-semibold transition-all"
                        onClick={onDeckCreate}
                    >
                        Create Deck
                    </button>
                </div>
                <div className="grid grid-cols-5 justify-items-center gap-6">
                    {decks.filter(searchFilter).map((deck) => (
                        <DeckComponent
                            key={deck.id}
                            id={deck.id}
                            name={deck.name}
                            amount={deck.size}
                            onDelete={() => onDeleteDeck(deck.id)}
                        />
                    ))}
                </div>
            </div>
        </div>
    );
};
export default LearningPage;
