'use client';

import { useEffect, useState } from 'react';
import { useParams } from 'next/navigation';

import CardComponent from './Card.component';
import AddCardDialog from './AddCard.component';
import Button from '@/components/ui/Button.component';
import { Card, getCards } from '@/lib/api/card.service';
import CardsList from './CardsList.component';
import ChangeCardButton from './ChangeCardButton.component';
import { Input } from '@headlessui/react';

function Cards() {
    const params = useParams();
    const id = Number(params?.id);
    const [cards, setCards] = useState<Card[]>([]);
    const [isAdding, setIsAdding] = useState(false);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);

    const fetchCards = async () => {
        if (!id || isNaN(id)) {
            setError('Invalid deck ID');
            setLoading(false);
            return;
        }

        try {
            setLoading(true);
            setError(null);
            const cards = await getCards(id);
            setCards(cards);
        } catch (err) {
            console.error('Failed to fetch cards:', err);
            setError('Failed to fetch cards.');
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchCards();
    }, [id]);

    const [cardIndex, setCardIndex] = useState<number>(0);

    const [searchInput, setSearchInput] = useState('');

    const onSearch = (e: React.ChangeEvent<HTMLInputElement>) => {
        setSearchInput(e.target.value);
    };

    return (
        <div className="h-full">
            <section className="flex flex-col h-screen w-full">
                <header className="flex p-6 border-b border-gray-200 justify-center w-full">
                    <div className="container flex justify-between">
                        <h1 className="text-4xl font-bold mb-4">Deck #{id}</h1>
                        <Button onClick={() => setIsAdding(true)}>
                            Add Card
                        </Button>
                    </div>
                </header>
                <div className="flex flex-grow justify-center">
                    <div className="container p-6 flex justify-between items-center h-full">
                        <ChangeCardButton
                            direction="l"
                            changeCardIndex={setCardIndex}
                            isDisabled={cardIndex === 0}
                        />
                        {cards.at(cardIndex) !== undefined ? (
                            <CardComponent
                                id={cards.at(cardIndex)!.id}
                                phrase={cards.at(cardIndex)!.phrase}
                                translation={cards.at(cardIndex)!.translation}
                            />
                        ) : (
                            <div>No cards</div>
                        )}
                        <ChangeCardButton
                            direction="r"
                            changeCardIndex={setCardIndex}
                            isDisabled={cardIndex === cards.length - 1}
                        />
                    </div>
                </div>
                <div className="flex justify-center m-8">
                    <div className="container">
                        <h2 className="text-2xl text-center">To all cards</h2>
                    </div>
                </div>
            </section>

            <section className="h-screen flex justify-center">
                <div className="container">
                    <Input
                        className="m-4 py-4 px-6 text-stone-900 border-b-2 border-stone-400 bg-red focus:outline-0 text-xl border-0 w-1/3 mr-20"
                        placeholder="Search"
                        value={searchInput}
                        onChange={onSearch}
                    />
                    <div className="w-full">
                        <CardsList
                            filter={searchInput}
                            loading={loading}
                            error={error}
                            cards={cards}
                        ></CardsList>
                    </div>
                </div>
            </section>

            <AddCardDialog
                isOpen={isAdding}
                deckId={id}
                onCardAdded={fetchCards}
                onClose={() => setIsAdding(false)}
            />
        </div>
    );
}

export default Cards;
