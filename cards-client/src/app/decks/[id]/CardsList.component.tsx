import Button from '@/components/ui/Button.component';
import { Card, deleteCard } from '@/lib/api/card.service';
import { TrashIcon } from '@heroicons/react/24/outline';
import React, { useState } from 'react';

interface ListItemProps {
    phrase: string;
    translation: string;
    isExpanded: boolean;
    onToggle: () => void;
    onDelete: () => void;
}

const ListItem = ({
    phrase,
    translation,
    isExpanded,
    onToggle,
    onDelete,
}: ListItemProps) => {
    return (
        <div
            className={`p-5 bg-stone-100 rounded-3xl overflow-hidden transition-all duration-300 w-full text-2xl
            ${isExpanded ? 'max-h-112' : 'max-h-20'}
        `}
        >
            <div className="flex justify-between">
                <div className="font-bold">{phrase}</div>
                <div className="flex gap-4 px-4">
                    <Button
                        onClick={onToggle}
                        color="gray"
                        className={`
                        cursor-pointer transition-all duration-300 hover:scale-110
                    `}
                    >
                        See translation
                    </Button>
                    <Button onClick={onDelete} color="red">
                        <TrashIcon className="size-8" />
                    </Button>
                </div>
            </div>
            <div
                className={`
                overflow-hidden transition-all duration-300
                text-lime-600
                ${isExpanded ? 'opacity-100' : 'opacity-0'}
                `}
            >
                <div>{translation}</div>
            </div>
        </div>
    );
};

interface CardsListProps {
    cards: Card[];
    loading: boolean;
    error: string | null;
    filter: string;
    setCards: React.Dispatch<React.SetStateAction<Card[]>>;
    deckId: number;
}

const handleSetCards = (
    setCards: React.Dispatch<React.SetStateAction<Card[]>>,
    id: number
) => {
    setCards((prev) => prev.filter((card) => card.id !== id));
};

const CardsList = ({
    deckId,
    cards,
    loading,
    error,
    filter,
    setCards,
}: CardsListProps) => {
    const [expandedIndex, setExpandedIndex] = useState<number | null>(null);

    const toggleExpand = (id: number) => {
        setExpandedIndex(expandedIndex === id ? null : id);
    };

    const handleDeleteCard = (id: number) => {
        deleteCard(deckId, id).then(() => handleSetCards(setCards, id));
    };

    return (
        <>
            {loading && <p className="text-gray-500">Loading cards...</p>}
            {error && <p className="text-red-500">{error}</p>}

            {!loading && !error && cards.length === 0 && (
                <p className="text-gray-500">
                    No cards available. Try adding one!
                </p>
            )}

            {!loading && cards.length > 0 && (
                <div className="flex flex-col gap-3 w-full">
                    {cards
                        .filter(
                            (card) =>
                                card.phrase.includes(filter) ||
                                card.translation.includes(filter)
                        )
                        .map((card) => (
                            <ListItem
                                key={card.id}
                                phrase={card.phrase}
                                translation={card.translation}
                                isExpanded={expandedIndex === card.id}
                                onToggle={() => toggleExpand(card.id)}
                                onDelete={() => handleDeleteCard(card.id)}
                            />
                        ))}
                </div>
            )}
        </>
    );
};

export default CardsList;
